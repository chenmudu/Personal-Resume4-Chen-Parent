package org.chen.lb.test.ImporvedWeightLbTest;

import org.chen.lb.LoadBalanceIService;
import org.chen.lb.cImprovedWeightRandomBalance.ImprovedWeightGetServer;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
 * @Date: 2020/1/19 23:42
 * @Description:
 */
public class RandomBalanceTest {
    private static int FOR_LOOP_COUNT = 100;
    public static void main(String[] args) {
        LoadBalanceIService service = new ImprovedWeightGetServer();
        List<String> serverIps = new ArrayList<>(FOR_LOOP_COUNT);
        for(int i = 0; i < FOR_LOOP_COUNT; i++) {
            serverIps.add(service.getServerIp());
        }
        Map<String, Long> collectMap =  serverIps.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> sortMapByKey = collectMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry :: getValue,
                        (oldValue,newValue) -> oldValue,
                        LinkedHashMap::new));
        System.out.println("current server ip result is: " + sortMapByKey);
    }
}
