package org.chen.sword.finger.offer.first.b8FrogJumpBox;

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
 * @Date: 2020/3/26 23:30
 * @Description: 青蛙跳箱子。
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 *
 */
public class Solution {

    /**
     * 斐波那契数列。一维DP解决。
     *
     * 0  1  2  3  4  5   6   7
     * 1  1  2  3  5  8  13  21
     *
     */
    public int numWays(int n) {
        if(n == 0) {
            return 0;
        } else if(n < 4) {
            return n;
        }
        int prePreNum = 2;
        int preNum = 3;
        int currentNum;
        for(int i = 4; i <= n; i++) {
            currentNum = (prePreNum + preNum) % (1000000007);
            prePreNum = preNum;
            preNum = currentNum;
        }
        return preNum;
    }
}
