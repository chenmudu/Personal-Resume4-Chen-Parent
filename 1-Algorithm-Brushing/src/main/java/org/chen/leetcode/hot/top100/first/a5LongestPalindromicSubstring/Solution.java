package org.chen.leetcode.hot.top100.first.a5LongestPalindromicSubstring;

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
 * @Date: 2020/3/31 22:53
 * @Description:给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。（中等）
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class Solution {

    private static final String currStr = "babad";
    public static void main(String[] args) {
        System.out.println(getLongestPalindromicSubString(currStr));
    }

    /**
     *  中心扩展法，从current char 开始左右同时寻找。
     *  就像hash冲突后为元素在当前链表选择下一个位置时候的中心扩展法一样。(并发头插易成环。)
     *  直到不满足当前条件。
     * @param string
     * @return
     */
    private static String getLongestPalindromicSubString(String string) {
        int strLen = string.length();
        if(strLen < 2) {
            return string;
        }
        String longest = "";
        String currStr;
        for(int i = 0; i < strLen; i++) {
            currStr = PalindromicSubString(string, i, i);
            if(currStr.length() > longest.length()) {
                longest = currStr;
            }
            currStr = PalindromicSubString(string, i, i + 1);
            if(currStr.length() > longest.length()) {
                longest = currStr;
            }
        }
        return longest;
    }

    private static String PalindromicSubString(String str, int le, int rig) {
        int len = str.length();
        int left = le, right = le;
        int strLen = str.length();
        while(left >= 0 &&
                    right < len &&
                            str.charAt(left) ==
                                    str.charAt(right)) {
            --left;
            ++right;
        }
        return str.substring(left + 1, right);
    }
}
