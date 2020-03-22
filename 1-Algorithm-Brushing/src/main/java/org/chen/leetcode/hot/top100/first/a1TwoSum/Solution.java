package org.chen.leetcode.hot.top100.first.a1TwoSum;

import java.util.HashMap;

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
 * @Date: 2020/3/18 20:42
 * @Description:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class Solution {
    private static int[] arrays = {1, 3,  5, 6, 7, 10};
    public static void main(String[] args) {
        int[] ints = towSum1(arrays, 15);
        System.out.println("第一个坐标为：" + ints[0] + "，第二个坐标为：" + ints[1]);
    }


    /**
     *  2数之和，O(n^2)时间效率最低。
     *  Hash寻址O(1)，时间换空间。
     *  key为给定数组的值，value为其下标。
     *  一次遍历与目标值的插值,不存在将当前元素放入map中。
     *  存在即可获取当前差值得下标和currentNumber的下标。输出即可。
     * @param nums
     * @param target
     * @return
     */
    public static int[] towSum(int[] nums, int target) {
        int[] resultArray = new int[2];
        HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if(valueIndexMap.containsKey(num)) {
                //map中已经存在了。所以第一个数组元素去已经存在的目标值得下标。
                resultArray[0] = valueIndexMap.get(num);
                resultArray[1] = i;
                return resultArray;
            }
            valueIndexMap.put(nums[i], i);
        }
        return resultArray;
    }

    public static int[] towSum1(int[] nums, int target) {
        int volume = 32; //2^5 = 32   100000
        int bitMode = volume-1;     //011111
        int [] result =new int[volume];
        for (int i=0;i<nums.length;i++){
            int c = (target - nums[i]) & bitMode;
            if (result[c]!=0){
                return new int[]{result[c]-1,i};
            }
            result[nums[i] & bitMode]=i+1;
        }
        return null;
    }
}
