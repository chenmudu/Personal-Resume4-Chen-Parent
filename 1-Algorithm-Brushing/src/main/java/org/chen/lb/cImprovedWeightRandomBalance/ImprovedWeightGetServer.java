package org.chen.lb.cImprovedWeightRandomBalance;

import org.chen.lb.LoadBalanceIService;
import org.chen.lb.serverConfig.ServerIp;
import org.chen.lb.serverUtils.ServerIpUtils;
import org.ietf.jgss.Oid;
import org.omg.CORBA.NVList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
 * @Date: 2020/1/19 22:16
 * @Description: 改进版本。将所有权重映射在一个个的区间段上.随机数的大小和区间段进行匹配。即可获取对应Ip。
 */
public class ImprovedWeightGetServer extends LoadBalanceIService {

    private Random random = new Random();

    @Override
    public String getServerIp() {
        Map<ServerIp, Integer> weightServerIpsMap = ServerIpUtils.getServerIpsMapByFixedWeight();
        //判断当前权重和前一次权重是否一致。
        boolean sameAllWeightFlag = true;

        int weightSum = ServerIpUtils.WEIGHT_SUM_ARRAY;

        List<Integer> allWeightList = weightServerIpsMap.values().stream().collect(Collectors.toList());

        for(int i = 0; i < allWeightList.size(); i++) {
            if(0 == i) {
                continue;
            }
            int currentWeight = allWeightList.get(i);
            if(sameAllWeightFlag && !Objects.equals(currentWeight, allWeightList.get(i - 1))) {
                sameAllWeightFlag = false;
            }
        }

        //当前权重区间所落点。
        int weightOps = random.nextInt(weightSum);

        if(!sameAllWeightFlag) {
            for(Map.Entry<ServerIp, Integer> mapEntry : weightServerIpsMap.entrySet()) {
                int currentWeight = mapEntry.getValue();
                //左闭右开.
                if(weightOps < currentWeight) {
                    return mapEntry.getKey().getServerIp();
                }
                //区间右移。
                weightOps -= currentWeight;
            }
        }
        return weightServerIpsMap.keySet().stream().collect(Collectors.toList()).get(random.nextInt(ServerIpUtils.getServerIpListSize())).getServerIp();
    }
}
