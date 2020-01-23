package org.chen.lb.fImprovedWeightRoundBalance;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.chen.lb.LoadBalanceIService;
import org.chen.lb.commonUtils.StringUtils;
import org.chen.lb.fImprovedWeightRoundBalance.sequence.SequenceNumber;
import org.chen.lb.serverConfig.ServerIp;
import org.chen.lb.serverUtils.ServerIpUtils;
import org.omg.CORBA.NamedValue;

import javax.swing.plaf.basic.BasicViewportUI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
 * @Date: 2020/1/23 17:01
 * @Description: 改进版的基于权重的轮询算法。(序列号与权重总值取模然后映射到一维坐标取结果。)
 */
public class ImproveWeightRoundGetServer extends LoadBalanceIService {

    @Override
    public String getServerIp() {
        //获取当前序列号。
        long sequenceNum = SequenceNumber.getCurrentSequenceNumber();
        //获取当前权重总值。
        int sumWeightAll = ServerIpUtils.WEIGHT_SUM_ARRAY;
        long offset = sequenceNum % sumWeightAll;
        offset = offset == 0 ? sumWeightAll : offset;
        //依据映射去获取Ip。
        return getServerIpBySameWeightFlag(getSameWeightFlag(), offset);
    }

    /**
     * 判断所有的权重是否均相等。
     * @return
     */
    private boolean getSameWeightFlag() {
        boolean sameWeightFlagAll = true;
        // 避免 i - 1数组越界。
        for(int i = 1; i < ServerIpUtils.WEIGHT_ARRAY.length; i++) {
            if(sameWeightFlagAll && !Objects.equals(ServerIpUtils.WEIGHT_ARRAY[i], ServerIpUtils.WEIGHT_ARRAY[i - 1])) {
                sameWeightFlagAll = false;
                break;
            }
        }
        return sameWeightFlagAll;
    }

    /**
     * 依据对应的权重判断值去拿到对应Ip。
     * @param sameWeightFlag
     * @param offset
     * @return
     */
    private String getServerIpBySameWeightFlag(boolean sameWeightFlag, long offset) {
        if(!sameWeightFlag) {
            for (ServerIp serverIp : ServerIpUtils.getServerIpsByFixedWay()) {
                int currentWeight = ServerIpUtils.getServerIpsMapByFixedWeight().get(serverIp).intValue();
                if(offset <= currentWeight) {
                    return serverIp.getServerIp();
                }
                offset -= currentWeight;
            }
        }
        return getRoundServerIpByPos();
    }

    private Lock lock = new ReentrantLock();

    private AtomicInteger position = new AtomicInteger(0);

    /**
     * 轮询的方式去获取。
     * @return
     */
    private String getRoundServerIpByPos() {
        String severIp = StringUtils.EMPTY_STRING;
        lock.lock();;
        try {
            if(position.get() > ServerIpUtils.getServerIpListSize() - 1) {
                position = new AtomicInteger(0);
            }
            severIp = ServerIpUtils.getServerIpsByFixedWay().get(position.get()).getServerIp();
            position.incrementAndGet();
        } finally {
            lock.unlock();
        }
        return severIp;
    }
}
