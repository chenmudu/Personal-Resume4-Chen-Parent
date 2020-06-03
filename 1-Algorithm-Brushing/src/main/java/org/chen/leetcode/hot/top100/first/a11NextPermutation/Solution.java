package org.chen.leetcode.hot.top100.first.a11NextPermutation;

import java.util.Arrays;

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
 * @Date: 2020/6/3 22:48
 * @Description: 31. 下一个排列 (middle)
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * 1. 字典序列的定义是解题关键。
 * 2. 题目要求原地排序。
 */

public class Solution {

    private static int[] nums = {1, 3, 7, 5, 6, 4, 2, 0};

    public static void main(String[] args) {
        printArrayNum(nums);
        nextPermutation(nums);
        printArrayNum(nums);
    }



    private static void nextPermutation(int[] nums) {
        if(null == nums || 0 == nums.length) {
            throw new RuntimeException("current num array is empty.");
        }
        //
        int len = nums.length;
        //逆序对比
        for(int i = len - 1; i >= 0; i--) {
            if(0 == i) {
                Arrays.sort(nums);
            } else {
                if(nums[i] > nums[i - 1]) {
                    Arrays.sort(nums, i, len); //[i, len)
                    for(int j = i; j < len; j++) {
                        //擂台法找到
                        if(nums[j] > nums[i - 1]) {
                            int temp = nums[j];
                            nums[j] = nums[i - 1];
                            nums[i - 1] = temp;
                            return ;
                        }
                    }
                }
            }
        }
    }

    private static void printArrayNum(int[] nums) {
        for (int num : nums) {
            System.out.print(num + "  ");
        }
        System.out.println();
    }
}
