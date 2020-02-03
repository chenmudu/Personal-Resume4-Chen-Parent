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
     * hash function for put node into current tree map .
     */
    private HashFunction hashFunction;

    /**
     * ring container for virtual node.
     */
    private final SortedMap<Long, VirtualNode<T>> ringContainer = new TreeMap<>();


    /**
     *
     * @param pNodes
     * @param vNodeCount
     */
    public ConsistentHashRouter(Collection<T> pNodes, int vNodeCount) {
        this(pNodes, vNodeCount, new MD5Hash());
    }

    /**
     *
     * @param pNodes
     * @param vNodeCount
     * @param hashFunction
     */
    public ConsistentHashRouter(Collection<T> pNodes, int vNodeCount, HashFunction hashFunction) {
        Objects.requireNonNull(hashFunction, "Current Hash Function is null!");
        initRouter(pNodes, vNodeCount, hashFunction);
    }

    /**
     * add node for ring container.
     * @param pNode
     * @param vNodeCount
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
     * @param pNode
     * @return
     */
    private int getExistingReplicas(T pNode) {
        int replicas = 0;
        //map.isEmpty == >   entrySet.size == 0
        if(CollectionUtils.mapIsEmpty(ringContainer)) {
            return replicas;
        }
        for(VirtualNode currentVNode : ringContainer.values()) {
            if(currentVNode.isVirtualNodeOf(pNode)) {
                ++replicas;
            }
        }
        return replicas;
    }

    /**
     * init router for my hash function and ring container.
     * @param pNodes
     * @param vNodeCount
     * @param hashFunction
     */
    private void initRouter(Collection<T> pNodes, int vNodeCount, HashFunction hashFunction) {
        this.hashFunction = hashFunction;
        if(CollectionUtils.isNotEmpty(pNodes)) {
            for(T currentNode : pNodes) {
                addNode(currentNode, vNodeCount);
            }
        }
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
            for(int i = 0; i < 4; i++) {
                hashValue <<= 8;
                hashValue |= ((int)digestByteData[i]) & 0XFF;
            }
            return hashValue;
        }
    }
}
