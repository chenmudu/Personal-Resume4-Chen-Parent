package org.chen.leetcode.hot.top100.first.a6ContainerWithMostWater;

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
 * @Date: 2020/4/7 22:10
 * @Description: 盛最多水的容器(中等???)
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 示例：
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */
public class Solution {

    private static int[] array = {1, 8, 6, 2, 5, 4, 8, 3, 7};

    public static void main(String[] args) {
        System.out.println(maxArea1(array));
        System.out.println(maxArea2(array));
    }

    /**
     *  类似于一维数组挑选最大值一样。
     *  擂台法。谁最牛逼谁站到最后。
     *  短板效应，取决于最小的那个长度。
     * @param height
     * @return
     */
    private static int maxArea1(int[] height) {
        int maxArea = 0;
        for(int i = 0; i < height.length; i++) {
            for(int j = i + 1; j <height.length; j++) {
                maxArea =  Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return maxArea;
    }

    /**
     * 在1中已经确定好了短板效应。
     * 我们就不应该用擂台法，换个思路。
     * 双指针移动即可。移动的过程都会经过最优解。所以不必担心。
     * @param height
     * @return
     */
    private static int maxArea2(int[] height) {
        int leftPoint = 0, rightPoint = height.length - 1, maxArea = 0;
        while(leftPoint < rightPoint) {
            maxArea =  Math.max(maxArea,
                    Math.min(height[leftPoint], height[rightPoint]) *
                                            (rightPoint - leftPoint));
            //移动短的一边  --->  长的一边。
            if(height[leftPoint] < height[rightPoint]) {
                ++leftPoint;
            } else {
                --rightPoint;
            }
        }
        return maxArea;
    }
}
