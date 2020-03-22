package org.chen.leetcode.hot.top100.first.a2AddTwoNumbers;

import java.util.Arrays;
import java.util.List;
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
 * @Date: 2020/3/20 20:43
 * @Description:
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class Solution {

    public static void main(String[] args) {
        ListNode headNode1 = initListNodeByNodes(Arrays.asList(2, 5, 3));
        printListNodeFromHeadNode(headNode1);
        ListNode headNode2 = initListNodeByNodes(Arrays.asList(5, 6, 4, 9));
        printListNodeFromHeadNode(headNode2);
        // 352 + 9465 = 9817 == > 7->1->8->9.
        printListNodeFromHeadNode(addTwoNumbers(headNode1, headNode2));
    }

    /**
     * 其实很好理解，幼儿园时候学习10进制加法的时候都是从右向左去进位 + 1。
     * for example : 23 + 35 = ?
     *                  2  7
     *               +  3  5
     * ---------------------------------
     *                  6  2
     *  5 + 7(从右向左加，满10进1，且最大为：  9 + 9 + 1。)
     *  所以此题只需要时刻保存进的位数，同时将某位上的数放入链表的下一个结构即可。(从左往右即可。)
     *  此题有个小技巧： 创建空结点去当初始化的结点，返回则返回emptyNode.next。
     *  对于链表的双指针问题好像大多都需要这样的冗余结点。(至少上次面试删除倒数第K的元素就是这样)
     * @param nodeList1
     * @param nodeList2
     * @return 新链表的
     */
    private static ListNode addTwoNumbers(ListNode nodeList1, ListNode nodeList2) {
        // 空结点作为头结点。
        ListNode emptyNode = new ListNode(0);
        int aryNum = 0;
        ListNode currentNode = emptyNode;
        while(Objects.nonNull(nodeList1) || Objects.nonNull(nodeList2)) {
            int firstValue = nodeList1 == null ? 0 : nodeList1.value;
            int secondValue = nodeList2 == null ? 0 : nodeList2.value;
            int num = firstValue + secondValue + aryNum;
            aryNum = num / 10;
            // emptyNode -> node1 -> node2 ... -> nodeN;
            currentNode.nextNode =  new ListNode(num % 10);
            //移动三条链表到下一个元素即可。
            currentNode = currentNode.nextNode;
            if(Objects.nonNull(nodeList1)) { nodeList1 = nodeList1.nextNode;}
            if(Objects.nonNull(nodeList2)) { nodeList2 = nodeList2.nextNode;}
        }
        //这儿也就是最后多的那一位。
        if(aryNum > 0) {currentNode.nextNode = new ListNode(aryNum);}
        //空结点的下一个结点就是新链表的头结点。
        return emptyNode.nextNode;
    }

    private static ListNode initListNodeByNodes(List<Integer> nodeValues) {
        ListNode emptyNode = new ListNode(-1);
        ListNode currentNode = emptyNode;
        for(int i = 0; i < nodeValues.size(); i++) {
            currentNode.nextNode = new ListNode(nodeValues.get(i));
            //一定要记得移动当前链表的结点。
            currentNode = currentNode.nextNode;
        }
        return emptyNode.nextNode;
    }

    private static void printListNodeFromHeadNode(ListNode headNode) {
        String string = "->";
        while(Objects.nonNull(headNode)) {
            if(Objects.isNull(headNode.nextNode)) {
                string = "";
            }
            System.out.print(headNode.value + string);
            headNode = headNode.nextNode;
        }
        System.out.println();
    }
    static class ListNode {
        private int value;
        private ListNode nextNode;
        public ListNode(int value) {
            this.value = value;
        }
    }
}
