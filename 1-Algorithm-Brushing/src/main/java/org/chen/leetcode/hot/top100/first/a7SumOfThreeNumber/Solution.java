package org.chen.leetcode.hot.top100.first.a7SumOfThreeNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
 * @Date: 2020/4/8 20:28
 * @Description: 三数之和(中等)
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 */
public class Solution {

    private static int[] arr = {-1, 0, 1, 2, -1, -4};
    private static int[] arr2 = {-4, -1, -1, -1, 0, 1, 1, 2, 2};

    public static void main(String[] args) {
        printResult(threeSum1(arr));
        System.out.println("------");
        printResult(threeSum2(arr));
        System.out.println("------");
        printResult(threeSum3(arr));
    }

    /**
     *  暴力法循环获取所有可能。
     *  缺点是有重复,嗯。不符合题意。
     *  时间复杂度O(n)
     * @param nums
     * @return resultList
     */
    private static List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        if(null == nums || nums.length < 1) {
            return resultList;
        }
        int numsLen = nums.length;
        //Arrays.sort(nums);
        //三重循环。
        for(int i = 0; i < numsLen - 2; i++) {
            for(int j = i + 1; j < numsLen - 1; j++) {
                for(int k = j + 1; k < numsLen; k++) {
                    if(0 == (nums[i] + nums[j] + nums[k])) {
                        resultList.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return resultList;
    }

    /**
     * 同上，缺点是重复太大。
     * 但是优化点是：空间换时间。
     * 时间复杂度：双重for嵌套, O(N ^ 2)
     * 空间复杂度：2O(n) so ： O(n)
     * @param nums
     * @return resultList
     */
    private static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        if(null == nums || nums.length < 1) {
            return resultList;
        }
        int numsLen = nums.length;
        HashMap<Integer, Integer> mapping = new HashMap<>();
        //Arrays.sort(nums);
        for(int i = 0; i < numsLen - 2; i++) {
            for(int j = i + 1; j < numsLen -1; j++) {
                int currNum = 0 - nums[i] - nums[j];
                if(mapping.containsKey(currNum)) {
                    resultList.add(Arrays.asList(nums[i], nums[j], currNum));
                } else {
                    if(!mapping.containsKey(nums[i])){
                        mapping.put(nums[i], i);
                    }
                    if(!mapping.containsKey(nums[j])) {
                        mapping.put(nums[j], j);
                    }
                }
            }
        }
        return resultList;
    }

    /**
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        if(null == nums || nums.length < 1) {
            return resultList;
        }
        int numsLen = nums.length;
        Arrays.sort(nums);
        //{-4, -1, -1, -1, 0, 1, 1, 2, 2}
        for(int i = 0; i < numsLen; i++) {
            if(nums[i] > 0) {
                //return
                break;
            }
            //去重。当前和前一个。 -1, -1, -1,
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //双指针。
            int leftPoint = i + 1;
            int rightPoint = numsLen - 1;
            while(leftPoint < rightPoint) {
                int sum = nums[i] + nums[leftPoint] + nums[rightPoint];
                if(0 == sum) {
                    resultList.add(Arrays.asList(nums[i], nums[leftPoint], nums[rightPoint]));
                    while(leftPoint < rightPoint && nums[leftPoint] == nums[leftPoint + 1]) {
                        ++leftPoint;
                    }
                    while(leftPoint < rightPoint && nums[rightPoint] == nums[rightPoint - 1]) {
                        --rightPoint;
                    }
                    ++leftPoint;
                    --rightPoint;
                } else if(0 > sum) {
                    ++leftPoint;
                } else {
                    --rightPoint;
                }
            }
        }
        return resultList;
    }



    private static void printResult(List<List<Integer>> resultList) {
        for (List<Integer> integerList : resultList) {
            System.out.println(integerList);
        }
    }
}
