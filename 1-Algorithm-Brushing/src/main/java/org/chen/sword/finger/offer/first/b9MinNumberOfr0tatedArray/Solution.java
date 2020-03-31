package org.chen.sword.finger.offer.first.b9MinNumberOfr0tatedArray;

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
 * @Date: 2020/3/31 23:24
 * @Description:
 */
public class Solution {

    private static int[] arr = {5, 6, 7, 3, 4};

    public static void main(String[] args) {
        System.out.println(minArray(arr));
    }

    // 注意最后取得是左边的值。
    private static int minArray(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = (left + right) >> 1;
            if(nums[mid] > nums[right]) {
                left = mid + 1;
            } else if(nums[mid] < nums[right]) {
                right = mid;
            } else {
                --right;
            }
        }
        return nums[left];
    }
}
