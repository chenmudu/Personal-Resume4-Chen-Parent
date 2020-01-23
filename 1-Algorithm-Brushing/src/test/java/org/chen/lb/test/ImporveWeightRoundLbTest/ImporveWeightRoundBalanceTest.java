package org.chen.lb.test.ImporveWeightRoundLbTest;

import org.chen.lb.LoadBalanceIService;
import org.chen.lb.fImprovedWeightRoundBalance.ImproveWeightRoundGetServer;

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
 * @Date: 2020/1/23 23:18
 * @Description:
 */
public class ImporveWeightRoundBalanceTest {
    private static int FOR_LOOP_COUNT = 100;

    public static void main(String[] args) {
        LoadBalanceIService service = new ImproveWeightRoundGetServer();
        for(int i = 0; i < FOR_LOOP_COUNT; i++) {
            System.out.println(service.getServerIp());
        }

    }
}
