package org.chen.lb.serverUtils;

import org.chen.lb.commonUtils.CollectionUtils;
import org.chen.lb.serverConfig.ServerIp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    /**
     * ip前缀。
     */
    private transient static String PRE_SERVER_IP = "192.168.0.";

    /**
     * 给定服务器ip的个数。
     * 当然生产肯定是从Apollo中去读取.
     * 或者集中式网关中去读。
     */
    private transient static Integer SIZE_SERVER_IP_LIST = 10;

    public static Integer getServerIpListSize() {
        return SIZE_SERVER_IP_LIST;
    }

    /**
     * 缓存目标服务器的Ip集合。
     */
    private static List<ServerIp>  SERVER_IP_LIST = new ArrayList<>(SIZE_SERVER_IP_LIST);

    /**
     * 获取当前IpList集合。
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



}
