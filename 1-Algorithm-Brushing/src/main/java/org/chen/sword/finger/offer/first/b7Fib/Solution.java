package org.chen.sword.finger.offer.first.b7Fib;

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
 * @Date: 2020/3/26 23:18
 * @Description: 斐波那契数列求某一项。(easy)
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 */
public class Solution {
    /**
     *
     * 0  1  2  3  4  5  6  7...
     * 0  1  1  2  3  5  8  13...
     */
    public int fib(int n) {
        if(2 > n) {
            return n;
        }
        int prePreNum = 0;
        int preNum = 1;
        int currentNum;
        for(int i = 2; i <= n; i++) {
            currentNum = (prePreNum + preNum) % 1000000007;
            prePreNum = preNum;
            preNum = currentNum;
        }
        return preNum;
    }
}
