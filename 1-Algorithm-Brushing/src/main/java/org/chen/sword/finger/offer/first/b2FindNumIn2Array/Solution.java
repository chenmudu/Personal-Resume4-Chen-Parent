package org.chen.sword.finger.offer.first.b2FindNumIn2Array;

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
 * @Date: 2020/3/22 22:02
 * @Description:
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *  
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false
 *
 */
public class Solution {

    private static int [][] arr = {
            {1,   4,  7, 11, 15},
            {2,   5,  8, 12, 19},
            {3,   6,  9, 16, 22},
            {10, 13, 14, 17, 24}

    };

    public static void main(String[] args) {
        System.out.println(findNumberIn2DArray(arr, 15));
    }


    /**
     * 抓住题干要素，数组内元素按顺序排列。
     * 从矩阵的角落开始寻找。四个角落随便找一个。
     * 缺点就是边界值控制好。
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        boolean flag = false;
        if(matrix.length < 1 || matrix[0].length < 1 ) {
            return flag;
        }
        int row = matrix.length - 1;
        int rowMin = -1;
        int col = 0;
        int colMax = matrix[0].length;
        while(row > rowMin && col < colMax) {
            int currNum = matrix[row][col];
            if( currNum== target) {
                flag = true;
                break;
            } else if(currNum < target) {
                ++col;
            } else {
                --row;
            }
        }
        return flag;
    }

    /**
     * java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
     *   at line 12, Solution.findNumberIn2DArray
     *   at line 57, __DriverSolution__.__helper__
     *   at line 84, __Driver__.main
     */
}
