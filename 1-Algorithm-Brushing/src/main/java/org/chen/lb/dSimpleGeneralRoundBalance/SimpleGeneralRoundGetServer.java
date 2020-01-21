package org.chen.lb.dSimpleGeneralRoundBalance;

import org.chen.lb.LoadBalanceIService;
import org.chen.lb.commonUtils.StringUtils;
import org.chen.lb.serverConfig.ServerIp;
import org.chen.lb.serverUtils.ServerIpUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
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
 * @Date: 2020/1/21 23:06
 * @Description: 简单的轮询获取当前ip的值。
 */
public class SimpleGeneralRoundGetServer extends LoadBalanceIService {

    private static ReentrantReadWriteLock PARENT_LOCK = new ReentrantReadWriteLock();

    //游标卡尺 必保可见性。
    private volatile int pos = 0;

    private static Lock READ_LOCK = PARENT_LOCK.readLock();

    private static Lock WRITE_LOCK = PARENT_LOCK.writeLock();

    private Random random = new Random();

    /**
     * 写，设置游标卡尺。
     * @param serverIpSize
     */
    private void setPosWhenOverFlow(int serverIpSize) {
        WRITE_LOCK.lock();
        try {
            if(pos > serverIpSize - 1) {
                pos = 0;
            }
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    /**
     * 其实我在想.加锁的原因是什么呢:
     * 其实是因为将方法抽取出来后。
     * 读和写的加锁范围更加细粒度化。
     * 在getServer方法没有任何保护措施。
     * 这样多台机器去请求的时候会出现问题的。
     * 解决方案：1. 扩大锁的粒度。
     *
     *          2. 在写 - 读 - 写这个操作顺序中,将第二个写放在第一个写的粒度内。
     *                                          不过在读的时候获取下标就要 - 1。
     *          3.对第二个写操作加锁： 加锁加什么锁？
     *                              考虑此时的情况：读 - 写 - 操作已完成，此时的并发情况较小。
     *                              使用Synchronized我觉得还是更好.毕竟此时竞争不大。不会发生
     *                              锁的膨胀问题。其实Lock也可以。看个人吧。我只是喜欢均等的平均以下。
     *                              前面的 读 - 写 操作已经用到了Lock。秉着公平原则这里用Synchronized。
     */
    private synchronized void posIncreaseHandler() {
        ++pos;
    }


    @Override
    public String getServerIp() {
        //养成良好的编程习惯。接口提供者不提供null。接口调用者必判Null。
        String currentServerIp = StringUtils.EMPTY_STRING;

        //所有机器ip集合。
        List<ServerIp> serverIps = ServerIpUtils.getServerIpsByFixedWay();

        //设置溢出游标。 写操作。
        setPosWhenOverFlow(serverIps.size());
        //获取当前机器IP。 读操作。
        currentServerIp = getServerIpFromList(serverIps);
        //设置游标卡尺。 写操作。
        posIncreaseHandler();

        return StringUtils.isEmpty(currentServerIp) ?
                    serverIps.get(random.nextInt(serverIps.size())).getServerIp() : currentServerIp;
    }

    /**
     * 读：获取Ip。
     * @param serverIps
     * @return
     */
    private String getServerIpFromList(List<ServerIp> serverIps) {
        String currentIp = StringUtils.EMPTY_STRING;
        READ_LOCK.lock();
        try {
            currentIp = serverIps.get(pos).getServerIp();
        } finally {
            READ_LOCK.unlock();
        }
        return currentIp;
    }


}
