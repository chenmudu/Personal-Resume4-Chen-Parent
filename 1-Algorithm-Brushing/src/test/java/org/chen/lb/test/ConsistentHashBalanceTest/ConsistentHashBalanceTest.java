package org.chen.lb.test.ConsistentHashBalanceTest;

import org.chen.lb.LoadBalanceIService;
import org.chen.lb.eCopyServerWeightRoundBalance.CopyIpWeightRoundGetServer;
import org.chen.lb.iConsistentHashBalance.ConsistentHashGetServer;
import org.chen.lb.serverConfig.ServerIp;

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
 * @Date: 2020/2/7 15:06
 * @Description: 测试一致性哈希算法。
 */
public class ConsistentHashBalanceTest {

    private static int FOR_LOOP_COUNT = 100;

    private static String NODE_PREFIX = "192.168.0.";

    private static String REMOVE_NODE = "10";

    private static String ADD_NODE = "0";

    private static LoadBalanceIService service = new ConsistentHashGetServer();

    public static void main(String[] args) {

        //普通测试。
        commonTest();

        //删除结点测试。
        ((ConsistentHashGetServer) service).removeNode(new ServerIp(NODE_PREFIX + REMOVE_NODE));

        printCurrentMapNodes(getServerIpList());

        //增加结点测试。
        ((ConsistentHashGetServer) service).addNode(new ServerIp(NODE_PREFIX + ADD_NODE));

        printCurrentMapNodes(getServerIpList());
    }


    /**
     *  打印当前map容器中的多个结点。
     * @param serverIps
     */
    private static void printCurrentMapNodes(List<String> serverIps) {
        Map<String, Long> collectMap =  serverIps.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> sortMapByKey = collectMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue,newValue) -> oldValue,
                        LinkedHashMap::new));
        System.out.println("current server ip result is: " + sortMapByKey);
    }

    /**
     * 普通测试。
     */
    private static void commonTest() {
        for(int i = 0; i < FOR_LOOP_COUNT; i++) {
            System.out.println(service.getServerIp());
        }
    }

    /**
     * 拿到固定server ip list.
     * @return
     */
    private static List<String> getServerIpList() {
        List<String> serverIps = new ArrayList<>(FOR_LOOP_COUNT);
        for(int i = 0; i < FOR_LOOP_COUNT; i++) {
            serverIps.add(service.getServerIp());
        }
        return serverIps;
    }
}
