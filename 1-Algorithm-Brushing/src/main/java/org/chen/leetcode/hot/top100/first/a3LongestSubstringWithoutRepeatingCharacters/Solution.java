package org.chen.leetcode.hot.top100.first.a3LongestSubstringWithoutRepeatingCharacters;

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
 * @Date: 2020/3/22 12:00
 * @Description:
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 */
public class Solution {
    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(str));
        System.out.println(lengthOfLongestSubstring(str = "bbbbb"));
        System.out.println(lengthOfLongestSubstring(str = "pwwkew"));
    }

    /**
     * 滑动窗口, 且滑动窗口的左右指针均小于当前字符串长度。
     * 借助Hash的唯一性和O(1)的寻址去移动窗口的大小。
     * @param str 带寻字符串。
     * @return 滑动窗口大小。
     */
    public static Integer lengthOfLongestSubstring(String str) {
        int window = 0;
        int leftPoint = 0;
        int rightPoint = 0;
        int strLen = str.length();
        HashSet<Character> charSet = new HashSet<>(str.length());
        while(leftPoint < strLen && rightPoint < strLen) {
            char ch = str.charAt(rightPoint);
            if(!charSet.contains(ch)) {
               charSet.add(ch);
               ++rightPoint;
               window = Math.max(window, rightPoint - leftPoint);
            } else {
                //当存在的时候删除左边和此字符相同的字符。然后左指针移动即可。
                charSet.remove(str.charAt(leftPoint));
                ++leftPoint;
            }
        }
        return window;
    }
}
