package org.chen.sword.finger.offer.first.b5RebuildBT;

import java.util.Arrays;
import java.util.Objects;

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
 * @Date: 2020/3/26 20:01
 * @Description: 重建二叉树(中等难度)
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 */
public class Solution {
    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        int preLen = preOrder.length;
        int inLen = inOrder.length;
        if(Objects.isNull(preOrder) ||
                    Objects.isNull(inOrder) ||
                            preLen == 0 ||
                                    inLen == 0) {
            return null;
        }
        TreeNode rootNode = new TreeNode(preOrder[0]);
        for(int i =0; i < inLen; i++) {
            if(preOrder[0] == inOrder[i]) {
                rootNode.leftNode = buildTree(Arrays.copyOfRange(preOrder, 1, i + 1), //去除头结点.
                                               Arrays.copyOfRange(inOrder, 0, i)); //去除根结点。
                rootNode.rightNode = buildTree(Arrays.copyOfRange(preOrder, i + 1, preLen), //其他剩余部分
                                               Arrays.copyOfRange(inOrder, i + 1, inLen));
            }
        }
        return rootNode;
    }

    static class TreeNode {
        private TreeNode leftNode;

        private TreeNode rightNode;

        private Integer value;

        public TreeNode(int value){
            this.value = value;
        }
    }
}
