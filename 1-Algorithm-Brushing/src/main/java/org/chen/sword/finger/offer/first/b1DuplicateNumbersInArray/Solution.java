package org.chen.sword.finger.offer.first.b1DuplicateNumbersInArray;

import java.util.Arrays;
import java.util.HashSet;

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
 * @Date: 2020/3/22 21:46
 * @Description:
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *  
 *
 * 限制：
 *
 * 2 <= n <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    private static int[] arrTest1 ={2, 3, 1, 0, 2, 5, 3};
    private static int[] arrTest2 = {1,2,3,4,9,0,1};
    private static int[] arrTest3 = {4,7,4,2,3,4,3};
    private static int[] arrTest4 = {0,1,4,6,3,1};

    public static void main(String[] args) {
        System.out.println(findRepeatNumber(arrTest1));
        System.out.println(findRepeatNumber(arrTest2));
        System.out.println(findRepeatNumber(arrTest3));
        System.out.println(findRepeatNumber(arrTest4));
    }

    /**
     *  其实平时写业务代码也会遇到这样的情况.利用HashSet不重复特性即可。
     * @param nums 目标数组
     * @return 第一个出现重复的数字(正向循环)
     */
    private static int findRepeatNumber(int[] nums) {
        int arrLen = nums.length;
        if(2 > arrLen || 100000 < arrLen) {
            throw new RuntimeException("不符合条件！");
        }
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < arrLen; i++) {
            if(!set.add(nums[i])) {
                arrLen = nums[i];
                break;
            }
        }
        return arrLen;
    }
}
