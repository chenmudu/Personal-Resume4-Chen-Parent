package org.chen.leetcode.hot.top100.first.a9RemoveNthNodeFromEndOfList;

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
 * @Date: 2020/4/9 23:32
 * @Description: 删除链表的倒数第 N 个节点(中等)
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 中间件转岗的时候遇到的算法题。
 */
public class Solution {

    /**
     * 双指针同时指向空的结点, 并且定长移动。
     * "0" ->1 -> 2 -> 3 -> 4 -> 5 -> 6 ->7
     * k = 3; right移动K个位置。
     * right -> 3;
     * left 和 right同时移动，直到right == null.
     * left - > 1  right ->4
     * left -> 2   right ->5
     * left -> 3   right ->6
     * left -> 4   right ->7
     * left.nextNode = left.nextNode.nextNode
     * 4 -> 5 -> 6 -> 7  ==> 4 -> 6 -> 7
     * //垃圾回收自己回获取没有引用的对象。
     *
     * @param head 头结点。
     * @param n 倒数K。
     * @return 链表头结点。
     */
    private static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode emptyNode = new ListNode(0);
        emptyNode.nextNode = head;
        //初始化左右指针并指向空的头结点。
        ListNode leftPoint = emptyNode;
        ListNode rightPoint = emptyNode;
        while(rightPoint.nextNode != null) {
            if(n-- > 0) {
                rightPoint = rightPoint.nextNode;
            }
            leftPoint = leftPoint.nextNode;
            rightPoint = rightPoint.nextNode;
        }
        leftPoint.nextNode = leftPoint.nextNode.nextNode;
        //链表有2种结构。(数据结构书原话。)
        // 1. 存在头结点，但是头结点不存储任何信息，只是作为链表结点开始标志。
        // 2. 不存在头结点(或者存在头结点，但是头结点存储了第一个元素)。
        return emptyNode.nextNode;
    }

    static class ListNode {
        int value;
        ListNode nextNode;
        ListNode(int value) {
            this.value = value;
        }
    }
}
