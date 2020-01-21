package org.chen.lb.test.SimpleRoundLbTest;

import org.chen.lb.LoadBalanceIService;
import org.chen.lb.dSimpleGeneralRoundBalance.SimpleGeneralRoundGetServer;

import java.util.ArrayList;
import java.util.List;

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
 * @Date: 2020/1/21 23:32
 * @Description:
 */
public class SimpleRoundBalanceTest {

    private static int roundLength = 100;

    public static void main(String[] args) {
        LoadBalanceIService server = new SimpleGeneralRoundGetServer();
        //一共10个ip.轮流调？
        List<String> serverIps = new ArrayList<>(roundLength);

        for(int i = 0; i < roundLength; i++) {
            String currentIp = server.getServerIp();
            serverIps.add(currentIp);
        }
        serverIps.stream().forEach(System.out::println);

    }


}
