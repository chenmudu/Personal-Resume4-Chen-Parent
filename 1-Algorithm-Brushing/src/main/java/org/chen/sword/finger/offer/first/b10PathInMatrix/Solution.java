package org.chen.sword.finger.offer.first.b10PathInMatrix;

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
 * @Date: 2020/4/2 21:48
 * @Description: . 矩阵中的路径(Medium)
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的 3×4 的矩阵中包含一条字符串 “bfce” 的路径（路径中的字母用加粗标出）。
 *
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 *
 * 但矩阵中不包含字符串 “abfb” 的路径，因为字符串的第一个字符 b 占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 */
public class Solution {

    private static final String word = "ABCS";

    private static final char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','F'},
    };
    public static void main(String[] args) {
        System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {
        char[] wordArr = word.toCharArray();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; i < board[0].length; j++) {
                if(deepFirstSearch(board, wordArr, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param board     source  char array.
     * @param wordArr
     * @param i
     * @param j
     * @param k   当前这个字符串所遍历的下标。
     * @return
     */
    private static boolean deepFirstSearch(char[][] board, char[] wordArr, int i, int j, int k) {

        //超出边界
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != wordArr[k]) {
            return false;
        }
        //当前坐标char与待比较字符串k位的字符不相等。
//        if() {
//            return false;
//        }
        //满足后才去比较当前是不是最后一个字符。
        if(wordArr.length - 1 == k) {
            return true;
        }
        char tempChar = board[i][j];
        board[i][j] = '#';
        boolean flag = deepFirstSearch(board, wordArr, i, j + 1, k + 1) ||  //上
                        deepFirstSearch(board, wordArr, i, j - 1, k + 1) || //下
                        deepFirstSearch(board, wordArr, i -1, j, k + 1) ||  //左
                        deepFirstSearch(board, wordArr, i + 1, j, k + 1);   //右
        board[i][j] = tempChar;
        return flag;
    }
}
