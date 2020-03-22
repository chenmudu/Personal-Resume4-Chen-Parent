package org.chen.sword.finger.offer.first.b3ReplaceBlank;

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
 * @Date: 2020/3/22 22:37
 * @Description:
 * 请实现一个函数，把字符串 s 中的每个空格替换成 "%20"。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *  
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 10000
 *
 */
public class Solution {

    private static String str = "We are happy.";

    public static void main(String[] args) {
        System.out.println(replaceSpace(str));
    }

    /**
     * easy题：常规解法:
     *          创建字符数组,遇到空格就放入新的字符.否则就移动。
     *          库函数真香。
     * @param s
     * @return
     */
    private static String replaceSpace(String s) {
        //s.replaceAll("\\s+", '%20');
        int strLen = s.length();
        if(0 > strLen || 10000 < strLen) {
            throw new RuntimeException("不符合条件!");
        }
        // 3(20%) * (n个字符最多有n -1个间隔，最大有 n - 1个空格)
        char[] chArr = new char[3 * (strLen - 1)];
        int size = 0;
        for(int i = 0;  i < strLen; i++) {
            char curCh = s.charAt(i);
            if(curCh == ' ') {
                chArr[size++] = '%';
                chArr[size++] = '2';
                chArr[size++] = '0';
            } else {
                chArr[size++] = curCh;
            }
        }
        return new String (chArr, 0, size);
    }
}
