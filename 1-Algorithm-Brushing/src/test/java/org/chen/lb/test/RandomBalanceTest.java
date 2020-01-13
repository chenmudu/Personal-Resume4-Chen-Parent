package org.chen.lb.test;

import org.chen.lb.LoadBalanceIService;
import org.chen.lb.aRandomBanlace.SimpleRandomGetServer;

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
 * @Date: 2020/1/14 00:40
 * @Description:
 */
public class RandomBalanceTest {
    public static void main(String[] args) {
        LoadBalanceIService service = new SimpleRandomGetServer();
        System.out.println("current server ip result is: " + service.getServerIp());
    }
}
