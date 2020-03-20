package org.chen.leetcode.hot.top100.a2AddTwoNumbers;

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
        //
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
