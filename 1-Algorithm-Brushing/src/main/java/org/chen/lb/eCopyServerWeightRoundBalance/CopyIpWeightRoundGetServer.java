package org.chen.lb.eCopyServerWeightRoundBalance;

import org.chen.lb.LoadBalanceIService;
import org.chen.lb.commonUtils.StringUtils;
import org.chen.lb.serverConfig.ServerIp;
import org.chen.lb.serverUtils.ServerIpUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
 * @Date: 2020/1/23 13:50
 * @Description: 基于权重复制的轮询方式。
 */
public class CopyIpWeightRoundGetServer extends LoadBalanceIService {

    private Random random = new Random();

    private AtomicInteger posCounter = new AtomicInteger(0);

    private Lock lock = new ReentrantLock();

    @Override
    public String getServerIp() {
        //拿到复制
        List<ServerIp> serverListByWeight = ServerIpUtils.getServerIpListByCopyWeightWay();

        String serverIp = StringUtils.EMPTY_STRING;

        lock.lock();;;;
        try {
            //构造环型数组。
            if(posCounter.get() > serverListByWeight.size() - 1) {
                posCounter = new AtomicInteger(0);
            }
            serverIp = serverListByWeight.get(posCounter.get()).getServerIp();
            posCounter.incrementAndGet();
        } finally {
            lock.unlock();
        }

        return StringUtils.isEmpty(serverIp) == true ?
                        serverListByWeight.get(random.nextInt(serverListByWeight.size())).
                                        getServerIp() : serverIp ;
    }
}
