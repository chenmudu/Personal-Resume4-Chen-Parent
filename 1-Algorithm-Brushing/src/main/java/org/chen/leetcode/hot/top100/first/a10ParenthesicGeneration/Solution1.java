package org.chen.leetcode.hot.top100.first.a10ParenthesicGeneration;

import java.util.ArrayList;
import java.util.Arrays;
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
 * @Date: 2020/4/22 23:22
 * @Description: 22. 括号生成(middle.)
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 */
public class Solution1 {

    List<String> result = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        dfs(n, n, "");
        return result;
    }

    /**
     * 二叉选择 + 减枝。
     * 二叉选择： 每次要么增加一个 ‘(’， 要么增加一个 ‘)’，这叫做二叉选择。
     * 减枝： 提前过滤不符合条件，减少暴力搜索中的无效路径。(从问题和宏观角度去衡量。)
     * 这里的二叉选择：
     * dfs(left -1, right, current + '(')  /  dfs(left, right - 1, current + ')');
     * 但是在添加左括号的时候只有左括号在n中还有剩余的时候才去进行剪枝。
     * 同理对于右括号不仅要考虑剩余，并且还要考虑必须多于左括号剩余(其实已满足剩余)，才进行剪枝。
     *
     * 只有左右括号都不剩余的时候才会技术此次暴力递归。并添加到结果集。
     * @param left
     * @param right
     * @param current
     */
    private void dfs(int left, int right, String current) {
        //左右括号均不剩余的时候，才会添加结果集。
        if(0 == left && 0 == right) {
            result.add(current);
        }

        //对于左括号  需要满足其不剩余的减枝条件
        if(left > 0) {
            dfs(left - 1, right, current + "(");
        }

        //对于右括号，必须满足剩余 > 左括号的剩余。
        if(right > left) {
            dfs(left, right - 1, current + ")");
        }
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        List<String> stringList = solution.generateParenthesis(10);
        for (String s : stringList) {
            System.out.println(s);
        }
    }
}
