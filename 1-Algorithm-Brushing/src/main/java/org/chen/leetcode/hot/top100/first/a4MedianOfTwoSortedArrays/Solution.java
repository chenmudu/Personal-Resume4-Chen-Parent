package org.chen.leetcode.hot.top100.first.a4MedianOfTwoSortedArrays;

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
 * @Date: 2020/3/21 15:46
 * @Description: 二遍刷的时候再去回复这个思想....  这是个数学问题... 但是又很抽象... 难受....
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class Solution {
    private static int[] nums1 = {1, 3};
    private static int[] nums2 = {2};
    private static int[] nums3 = {1, 2};
    private static int[] nums4 = {3, 4};
    private static int[] nums5 = {};
    private static int[] nums6 = {1};
    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println(findMedianSortedArrays(nums3, nums4));
        System.out.println(findMedianSortedArrays(nums5, nums6));
    }

    /**
     * i = m - i ; j = n - j;
     * i + j = m + n - i -j + 1 = sumNewLen >> 1;
     * so: 2 (i + j) = m + n + 1 = sumLen;
     *             j = (m + n + 1) >> 1  - i;
     * 同时：i in [iMinx, iMax=m]   j = sumLen / 2 - i
     * 同时：ensure nums1[i - 1] <= nums2[j] && nums2[j-1] <= nums1[i]
     * 移动i的位置，确定i的准确点:
     *      1. i 太小 扩大左边界。
     *      2. i 太大 减小右边界
     *      3 i 恰好 (且满足边界值条件)：
     *          maxLeft:
     *              i == 0 最左边划分  那就是nums2划分点的左边
     *              j == 0 最左边划分  那就是nums1划分点的左边
     *              其他就是比较二者大小 选出大的。
     *              如果最后 m + n 为奇数 直接return就可以
     *          同理客得minRight
     *          minRight:
     *              i = m
     *              j = n
     *              比较临界值大小
     *
     *          return (maxLeft + minRight) >> 1
     * @param nums1
     * @param nums2
     * @return (maxLeft + minRight) >> 1;
     */
    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        //确保m <= n      cause:j != 0
        if(m > n) {
            int temp = m; m = n; n = temp;
            int[] tempArr = nums1; nums1 = nums2; nums2 = tempArr;
        }
        int iMin = 0, iMax = m;
        int halfLen = (m + n + 1) >> 1; //case i + j = m + n - i - j + 1;
        // j = (m + n + 1) >> 1 - i
        //令 halfLen = (m + n + 1) >> 1; j = halfLen - i;
        while(iMin <= iMax) {
            int i = (iMin + iMax) >> 1;
            int j = halfLen - i;
            //三种情况. 关于i的位置大小问题已经确保条件满足的时候
            if(i < iMax && nums2[j-1] > nums1[i]) {
                iMin = i + 1;
            } else if(i > iMin && nums1[i-1] > nums2 [j]) {
                //i too big 右边界限减下
                iMax = i - 1;
            } else {
                int maxLeft = 0;
                if (i == 0) { maxLeft = nums2[j-1]; }
                else if (j == 0) { maxLeft = nums1[i-1]; }
                else { maxLeft = Math.max(nums1[i-1], nums2[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = nums2[j]; }
                else if (j == n) { minRight = nums1[i]; }
                else { minRight = Math.min(nums2[j], nums1[i]); }

                //return (maxLeft + minRight) >> 1;
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
