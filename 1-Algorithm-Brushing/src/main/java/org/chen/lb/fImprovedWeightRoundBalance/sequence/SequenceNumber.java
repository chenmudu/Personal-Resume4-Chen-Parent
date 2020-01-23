package org.chen.lb.fImprovedWeightRoundBalance.sequence;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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
 * @Date: 2020/1/23 17:54
 * @Description: 序列号的作用是去计算出当前的offset值。即：映射所落区间。
 */
public class SequenceNumber {
    private static AtomicLong INCREMENT_SEQUENCE_NUMBER = new AtomicLong(0);

    public static Long getCurrentSequenceNumber() {
        if(Long.MAX_VALUE == INCREMENT_SEQUENCE_NUMBER.get()) {
            INCREMENT_SEQUENCE_NUMBER = new AtomicLong(0);
        }
        return INCREMENT_SEQUENCE_NUMBER.incrementAndGet();
    }
}
