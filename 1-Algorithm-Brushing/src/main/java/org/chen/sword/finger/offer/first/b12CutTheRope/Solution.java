package org.chen.sword.finger.offer.first.b12CutTheRope;

import com.sun.deploy.util.SyncAccess;

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
 * @Date: 2020/4/7 20:09
 * @Description:  剪绳子(中等)
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n 都是整数，n>1 并且 m>1），每段绳子的长度记为 k[0],k[1]...k[m] 。请问 k[0]*k[1]*...*k[m] 可能的最大乘积是多少？例如，当绳子的长度是 8 时，我们把它剪成长度分别为 2、3、3 的三段，此时得到的最大乘积是 18。
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 *
 * 2 <= n <= 58
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(cuttingRope(10));
    }

    private static int cuttingRope(int n) {
//        return cuttingRopeHelper1(n);
        return cuttingRopeHelper2(n, new int[n + 1]);
    }



    /**
     *  1. 常规思路：假设法。
     *  假设n = 6;
     *  1. 2, 3, 1  sum = 2 * 3 * 1 = 6;
     *  2. 2, 4     sum = 2 * 4 = 8;
     *  3. 1, 5     sum = 1 * 5 = 5;
     *
     *  first cut: 只减一刀，不管了。 sum = i * (n - i)
     *  second cut: 减一刀， 继续减。 sum = i * F(n-i) -->递归求解。
     *              n = 1,2  return 1;
     *  so :  我们要拿的是不管第一种还是第二种减法中的最大值即可。
     * @param n   长度
     * @return
     */
    private static int cuttingRopeHelper1(int n) {
        //递归结束条件。
        if(n < 3) {
            return 1;
        }
        int max = 0,  cutNum = 0,  notCutNum = 0;
        //循环进行递归操作。
        for(int i = 1; i < n; i++) {
            cutNum = i * cuttingRopeHelper1(n - i);
            notCutNum = i * (n - i);
            max = Math.max(max, Math.max(cutNum, notCutNum));
        }
        return max;
    }

    /**
     * 思路不变，只是我们需要把重复计算的结果缓存起来。
     * 就减少了对应的递归深度。也减少了计算的时间。
     * 以空间换取时间。
     * @param n
     * @param memory
     * @return
     */
    private static int cuttingRopeHelper2(int n, int[] memory) {
        //递归结束条件。
        if(n < 3) return 1;
        if(memory[n] > 0) return memory[n];
        int max = 0, cutNum = 0, notCutNum = 0;
        //循环进行递归操作。
        for(int i = 1; i < n; i++) {
            cutNum = i * cuttingRopeHelper2(n - i, memory);
            notCutNum = i * (n - i);
            max = Math.max(max, Math.max(cutNum, notCutNum));
        }
        return memory[n] = max;
    }

    private static int cuttingRopeHelper3() {
        return 0;
    }
}
