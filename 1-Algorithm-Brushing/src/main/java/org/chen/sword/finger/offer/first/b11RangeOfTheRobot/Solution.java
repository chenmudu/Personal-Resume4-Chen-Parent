package org.chen.sword.finger.offer.first.b11RangeOfTheRobot;

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
 * @Date: 2020/4/7 18:54
 * @Description: 机器人的运动范围(中等)
 * 地上有一个 m 行 n 列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于 k 的格子。例如，当 k 为 18 时，机器人能够进入方格 [35, 37] ，因为 3+5+3+7=18。但它不能进入方格 [35, 38]，因为 3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 1：
 *
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 *
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(movingCount(3, 1, 0));
    }

    /**
     * DFS.
     * 重要的是标记这个地方已经走过。
     * 但是不能像上个问题中直接去替换已经访问过的字母。
     * 所以需要额外的空间去保存current station 的状态。
     * @param m  行
     * @param n  列
     * @param k  target。
     * @return
     */
    private static int movingCount(int m, int n, int k) {
        //默认初始化boolean数组的值为flase。
        boolean[][] flag = new boolean[m][n];
        return dfs(0, 0, m, n, k, flag);
    }

    private static int dfs(int i, int j, int m, int n, int k, boolean[][] flag) {
        //十位 + 个位 之和：i/10 + i%10 + j/10 + j%10
        if(i < 0 ||
                i >=m ||
                    j < 0 ||
                        j >= n ||
                            (i/10 + i%10 + j/10 + j%10) > k ||
                                flag[i][j] == true) {
            return 0;
        }
        flag[i][j] = true;
        //因为从 0 ,0 开始的。所以并不需要去遍历 上 和 左  并且要加上 0 , 0这个坐标。
        return dfs(i + 1, j, m, n, k, flag) +
                    dfs(i, j + 1, m, n, k, flag) +
                            1;
    }
}
