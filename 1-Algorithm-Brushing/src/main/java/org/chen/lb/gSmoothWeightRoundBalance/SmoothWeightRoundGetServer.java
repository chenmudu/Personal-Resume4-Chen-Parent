package org.chen.lb.gSmoothWeightRoundBalance;

import org.chen.lb.LoadBalanceIService;
import org.chen.lb.serverConfig.ServerIp;
import org.chen.lb.serverConfig.SmoothWeightServer;
import org.chen.lb.serverUtils.ServerIpUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

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
 * @Author 陈晨
 * @Date: 2020/1/29 01:30
 * @Description: 基于权重的平滑轮询算法。
 * 数学证明省略。不急。后期慢慢查看即可。
 */
public class SmoothWeightRoundGetServer extends LoadBalanceIService {

    //i'll still use your back turn.
    private static Map<ServerIp, SmoothWeightServer> KOBE_BRYANT_MAP = new ConcurrentHashMap<>();

    @Override
    public String getServerIp() {
        //get sum of weight from this array.
        int totalServerWeight = ServerIpUtils.WEIGHT_SUM_ARRAY;

        //init map
        initMapIfNotExist();

        //get max smooth weight server
        SmoothWeightServer maxWeightServer = getMaxSmoothWeightServer();

        //5, 1, 1   --->  -2, 1, 1    最大的权值 - totalWeight.
        // set newCurrentWeight = newCurrentWeight - totalWight.
        maxWeightServer.setNewCurrentWeight(maxWeightServer.getNewCurrentWeight() - totalServerWeight);

        // -2, 1, 1  (+ 5, 1, 1)  --->    3, 2, 2
        // 所有ip的newCurrentWeight统一加上原始权重。
       addOldWeight4NewCurrentWeight();

       return maxWeightServer.getServerIp().getServerIp();
    }

    private void addOldWeight4NewCurrentWeight() {
        KOBE_BRYANT_MAP.values().forEach(sws -> {
            sws.setNewCurrentWeight(sws.getNewCurrentWeight() + sws.getOldWeight());
        });
    }

    /**
     * 初始化当前Map，并将初始化的数据放入。
     */
    private void initMapIfNotExist() {
        if(KOBE_BRYANT_MAP.isEmpty()) {
            ServerIpUtils.getServerIpsMapByFixedWeight().forEach((s, w) -> {
                KOBE_BRYANT_MAP.put(s, new SmoothWeightServer(s, w, w));
            });
        }
    }


    private SmoothWeightServer getMaxSmoothWeightServer() {
        SmoothWeightServer maxWeightServer = null;
        // 遍历循环找到最大的那个。
//        KOBE_BRYANT_MAP.values().forEach(currentServer -> {
//            //|| smc.getNewCurrentWeight()
//            if(Objects.isNull(maxWeightServer) || currentServer.getNewCurrentWeight()
//                                            > maxWeightServer.getNewCurrentWeight()) {
//                maxWeightServer = currentServer;
//            }
//        });
        for(SmoothWeightServer currentWeightServer : KOBE_BRYANT_MAP.values()) {
            if(Objects.isNull(maxWeightServer) || currentWeightServer.getNewCurrentWeight()
                                            > maxWeightServer.getNewCurrentWeight()) {
                maxWeightServer = currentWeightServer;
            }
        }
        return maxWeightServer;
    }
}
