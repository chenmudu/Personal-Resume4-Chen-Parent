package org.chen.lb.iConsistentHashBalance.consistentHash;

import org.chen.lb.commonUtils.CollectionUtils;
import org.chen.lb.hashFunction.HashFunction;
import org.chen.lb.serverConfig.node.Node;
import org.chen.lb.serverConfig.node.impl.VirtualNode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * MIT License
 * <p>
 * Copyright (c) 2019 chenmudu (陈晨)
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * @Author chenchen6
 * @Date: 2020/2/3 15:00
 * @Description:
 */
public class ConsistentHashRouter<T extends Node> {

    /**
     * hash function for put node into current tree map . It's a hash ring container.
     */
    private HashFunction hashFunction;

    /**
     * ring container for virtual node.
     */
    private final SortedMap<Long, VirtualNode<T>> ringContainer = new TreeMap<>();


    //ConsistentHashRouter() {}

    /**
     *
     * @param pNodes        实际server结点列表。
     * @param vNodeCount    虚拟结点个数。
     */
    public ConsistentHashRouter(Collection<T> pNodes, int vNodeCount) {
        this(pNodes, vNodeCount, new MD5Hash());
    }

    /**
     *
     * @param pNodes        实际server结点列表。
     * @param vNodeCount    虚拟结点个数。
     * @param hashFunction
     */
    public ConsistentHashRouter(Collection<T> pNodes, int vNodeCount, HashFunction hashFunction) {
        Objects.requireNonNull(hashFunction, "Current Hash Function is null!");
        initRouter(pNodes, vNodeCount, hashFunction);
    }

    /**
     * add node for ring container. 虚拟节点放置容器内。
     * @param pNode         实际的server结点。
     * @param vNodeCount    虚拟结点个数。
     */
    public void addNode(T pNode, int vNodeCount) {
        if(vNodeCount < 0) {
            throw new IllegalArgumentException("Illegal virtual node counts :" + vNodeCount);
        }
        // exist replicas counter.
        int existingReplicas = getExistingReplicas(pNode);
        //put virtualNode into ringContainer.
        for(int i = 0; i < vNodeCount; i++) {
            VirtualNode<T> vNode  = new VirtualNode<>(pNode, i + existingReplicas);
            ringContainer.put(hashFunction.hashStringToLong(vNode.getKey()), vNode);
        }
    }

    /**
     *  获取存在节点复制后的序列号。
     * @param pNode     当前受访服务器
     * @return replicas  当前服务器存在虚拟机点的个数。
     */
    private int getExistingReplicas(T pNode) {
        int replicas = 0;
        //map.isEmpty == >   entrySet.size == 0
        if(CollectionUtils.mapIsEmpty(ringContainer)) {
            return replicas;
        }
        //
        for(VirtualNode currentVNode : ringContainer.values()) {
            //if current node is virtual node , replicas ++ .
            if(currentVNode.isVirtualNodeOf(pNode)) {
                ++replicas;
            }
        }
        return replicas;
    }

    /**
     * init router for my hash function and ring container.
     * @param pNodes          受访服务器集合。
     * @param vNodeCount     虚拟结点的个数
     * @param hashFunction  hash函数。
     */
    private void initRouter(Collection<T> pNodes, int vNodeCount, HashFunction hashFunction) {
        this.hashFunction = hashFunction;
        if(CollectionUtils.isNotEmpty(pNodes)) {
            for(T currentNode : pNodes) {
                addNode(currentNode, vNodeCount);
            }
        }
    }

    /**
     *  删除服务器虚拟结点。
     * @param pNode
     */
    public void removeNode(T pNode) {
        Iterator<Long> it = ringContainer.keySet().iterator();
        Long key = null;
        VirtualNode currentVirtualNode = null;
        while(it.hasNext()){
            key = it.next();
            currentVirtualNode = ringContainer.get(key);
            // 说明删除了一个不存在的结点. 所以在调用删除结点的时候必须先判断此结点是否存在于hash环。
            if(Objects.isNull(currentVirtualNode)) {
                continue;
            }
            if(currentVirtualNode.isVirtualNodeOf(pNode)) {
                it.remove();
            }
        }
    }

    /**
     *
     * @param requestKey request Ip.
     * @return  member of ringContainer.
     */
    public T routeNodeServer(String requestKey) {
        // key is long hash value
        if(CollectionUtils.mapIsEmpty(ringContainer)) {
            return null;
        }
        Long nodeHashValue = null;
        // 将请求Ip 转换成hash值。
        long keyHashValue = hashFunction.hashStringToLong(requestKey);
        //
        SortedMap<Long, VirtualNode<T>> tailMap = ringContainer.tailMap(keyHashValue);
        //
        if(CollectionUtils.mapIsEmpty(tailMap)) {
            nodeHashValue = ringContainer.firstKey();
        } else {
            nodeHashValue = tailMap.firstKey();
        }
        //ringContainer.tailMap()
        return ringContainer.get(nodeHashValue).getPhysicalNode();
    }

    /**
     * 测试hash函数的散列性。
     * @return
     */
    public static MD5Hash getMd5Hash() {
        return new MD5Hash();
    }


    private static class MD5Hash implements HashFunction {

        /**
         * current key must be node.toString.append("-").append("copyIndex")
         *  so we can md5 copy and move bit.
         * @see <a href="https://zh.wikipedia.org/wiki/MD5">MD5 维基百科</a>
         *
         * 应当保持hash的四个特性：
         * @param key
         * @return
         */

        private MessageDigest instance;

        /**
         * MD5 head.
         */
        private static final String MD5_HEAD = "MD5";

        public MD5Hash() {
            try {
                this.instance = MessageDigest.getInstance(MD5_HEAD);
            } catch (NoSuchAlgorithmException e) {
                //安全的单向哈希函数,输入不固定,输出为指定长度。
            }
        }

        /**
         * hash算法决定了落点是否更加的均衡。
         * 所以hash算法真几是让人头疼。
         * @param  key          String key
         * @return hashValue    long hash value from string.
         */
        @Override
        public long hashStringToLong(String key) {
            //reset digest.
            instance.reset();
            //update digest.
            instance.update(key.getBytes());
            byte[] digestByteData = instance.digest();
            // hash string to long.
            long hashValue = 0L;
            //信息摘要进行打乱顺序转换成long型。
            //本来是固定4位.发现大于1W后存在重复的hashValue值.改成动态的吧。
            for(int i = 0; i < digestByteData.length - 1; i++) {
                hashValue <<= 8;
                hashValue |= ((int)digestByteData[i]) & 0XFF;
            }
            return hashValue;
        }
    }

    public static void main(String[] args) {
        //10w个才会出现一个相同的.测试结果仅存参考.
        final int count = 10000000;
        MD5Hash hash = ConsistentHashRouter.getMd5Hash();
        String serverIp = "127.0.0.";
        //Map<String, Long> map = new HashMap();
        Set<Long> hashSet = new HashSet<>();
        for(int i = 0; i < count; i++) {
            hashSet.add(hash.hashStringToLong(serverIp + i));
        }
        System.out.println("current hash set size = " + hashSet.size());
    }
}
