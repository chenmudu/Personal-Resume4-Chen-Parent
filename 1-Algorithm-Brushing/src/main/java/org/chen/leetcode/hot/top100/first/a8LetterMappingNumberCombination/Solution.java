package org.chen.leetcode.hot.top100.first.a8LetterMappingNumberCombination;

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
 * @Date: 2020/4/9 22:03
 * @Description: 电话号码的字母组合(中等难度)
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 对顺序无要求。
 */
public class Solution {

    private static HashMap<String, List<Character>> mapping = new HashMap();

    private static List<String> result = new ArrayList();

    static {
        mapping.put("2", Arrays.asList('a', 'b', 'c'));
        mapping.put("3", Arrays.asList('d', 'e', 'f'));
        mapping.put("4", Arrays.asList('g', 'h', 'i'));
        mapping.put("5", Arrays.asList('j', 'k', 'l'));
        mapping.put("6", Arrays.asList('m', 'n', 'o'));
        mapping.put("7", Arrays.asList('p', 'q', 'r', 's'));
        mapping.put("8", Arrays.asList('t', 'u', 'v'));
        mapping.put("9", Arrays.asList('w', 'x', 'y', 'z'));
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    /**
     *  南北流量夹杂东西流量。
     * @param digits
     * @return
     */
    private static List<String> letterCombinations(String digits) {
        if(digits != null && digits.length() > 0) {
            dfs("", digits);
        }
        return result;
    }

    /**
     * 排列组合。但是可以将其看作一颗root为empty字符串的树。
     * 将问题转换成：求树根节点到所有叶子结点的路径。
     *              到叶子结点，故用深度遍历即可。
     * @param charOfStr
     * @param nextDigits
     */
    private static void dfs(String charOfStr, String nextDigits) {
        if(null == nextDigits || 0 == nextDigits.length()) {
            result.add(charOfStr);
        } else {// 234
            // 2 --- > a , b, c
            String chNum = nextDigits.substring(0, 1);
            List<Character> chNumMapping = mapping.get(chNum);
            for(int i = 0; i < chNumMapping.size(); i++) {
                // "" + a,        3,4
                // "a" + b,        4
                dfs(charOfStr + chNumMapping.get(i), nextDigits.substring(1));
            }
        }
    }
}
