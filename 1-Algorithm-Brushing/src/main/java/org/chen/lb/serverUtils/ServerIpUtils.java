package org.chen.lb.serverUtils;

import org.chen.lb.commonUtils.CollectionUtils;
import org.chen.lb.commonUtils.StringUtils;
import org.chen.lb.serverConfig.ServerIp;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
 * @Date: 2020/1/14 00:10
 * @Description:
 */
public class ServerIpUtils {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * ip前缀。
     */
    private transient static String PRE_SERVER_IP = "192.168.0.";

    /**
     * 给定服务器ip的个数。
     */
    private transient static Integer SIZE_SERVER_IP_LIST = 10;

    /**
     * 权重之和。其实应该的进行计算。做成application级的缓存。
     */
    public static Integer WEIGHT_SUM_ARRAY = 50;

    /**
     * 权重数组。
     */
    public static int[] WEIGHT_ARRAY = {1, 8, 3, 6, 5, 5, 4, 7, 2, 9};

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Integer getServerIpListSize() {
        return SIZE_SERVER_IP_LIST;
    }

    /**
     * 缓存目标服务器的Ip集合。
     */
    private static List<ServerIp>  SERVER_IP_LIST = new ArrayList<>(SIZE_SERVER_IP_LIST);


    /**
     * 以固定方式去获取当前IpList集合List。
     * @return
     */
    public static List<ServerIp> getServerIpsByFixedWay() {
        if(CollectionUtils.isNotEmpty(SERVER_IP_LIST) && Objects.nonNull(SERVER_IP_LIST.get(0))) {
            return SERVER_IP_LIST;
        }
        ServerIp currentServerIp = null;
        for(int i = 0; i < SIZE_SERVER_IP_LIST; i++) {
            currentServerIp = ServerIp.builder().serverIp(PRE_SERVER_IP + (i + 1)).build();
            SERVER_IP_LIST.add(currentServerIp);
        }
        return SERVER_IP_LIST;
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static  ReentrantReadWriteLock currentLock = new ReentrantReadWriteLock();
    private static Lock READ_LOCK = currentLock.readLock();
    private static Map<ServerIp, Integer> SERVER_IPS_MAP = new LinkedHashMap<>();
    /**
     * 以固定方式去获取当前IpMap。
     * @return
     */
    public static Map<ServerIp, Integer> getServerIpsMapByFixedWeight() {
        if(CollectionUtils.mapIsNotEmpty(SERVER_IPS_MAP) && CollectionUtils.isNotEmpty(SERVER_IPS_MAP.entrySet())) {
            return SERVER_IPS_MAP;
        }
        ServerIp currentServer = null;
        String currentIp = StringUtils.EMPTY_STRING;
        READ_LOCK.lock();
        try {
            for(int i = 0; i < SIZE_SERVER_IP_LIST; i++) {
    //            ServerIp currentServer = ServerIp.builder().serverIp(PRE_SERVER_IP + (i + 1)).build();
                currentIp = PRE_SERVER_IP + (i + 1);
                currentServer = new ServerIp(currentIp);
                SERVER_IPS_MAP.put(currentServer, WEIGHT_ARRAY[i]);
            }
        } finally {
            READ_LOCK.unlock();
        }
        return SERVER_IPS_MAP;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static List<ServerIp> WEIGHT_SUM_SERVERS = new LinkedList<ServerIp>();

    public static Integer getWeightSumServersListSize (){
        return WEIGHT_SUM_SERVERS.size() == 0 ? getServerIpListByCopyWeightWay().size() : WEIGHT_SUM_SERVERS.size();
    }

    /**
     * 加权且复制后的ListServer集合。
     */
    public static List<ServerIp> getServerIpListByCopyWeightWay() {
        if(CollectionUtils.isNotEmpty(WEIGHT_SUM_SERVERS) && Objects.nonNull(WEIGHT_SUM_SERVERS.get(0))) {
            return WEIGHT_SUM_SERVERS;
        }
        getServerIpsMapByFixedWeight().entrySet().forEach( entry -> {
            for(int i = 0; i < entry.getValue(); i++) {
                WEIGHT_SUM_SERVERS.add(entry.getKey());
            }
        });
        return WEIGHT_SUM_SERVERS;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
