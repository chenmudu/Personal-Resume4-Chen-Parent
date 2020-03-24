package org.chen.sword.finger.offer.first.b4ReverseAndPrintListNode;

import java.util.List;
import java.util.Stack;

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
 * @Date: 2020/3/24 20:40
 * @Description:
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 *
 * 限制：
 *
 * 0 <= 链表长度 <= 10000
 */
public class Solution {
    private static int[] nodeArray = {1, 2, 3, 4, 5, 6};

    public static void main(String[] args) {
        printArrayNode(reversePrint(initListNodeByNodes(nodeArray)));
    }
    private static void printArrayNode(int[] nodes) {
        for(int i = 0; i < nodes.length; i++) {
            System.out.print(nodes[i] + "   ");
        }
    }
    private static ListNode initListNodeByNodes(int[] nodeValues) {
        ListNode emptyNode = new ListNode(-1);
        ListNode currentNode = emptyNode;
        for(int i = 0; i < nodeValues.length; i++) {
            currentNode.nextNode = new ListNode(nodeValues[i]);
            //一定要记得移动当前链表的结点。
            currentNode = currentNode.nextNode;
        }
        return emptyNode.nextNode;
    }

    /**
     * 这道题真的是,,,
     * 扯个犊子：好未来第一面那个做node.js的面试官贼可爱,还曾调侃我不打游戏。
     * 当时的算法题就是这个,一行代码递归执行就完了.
     * 现在这题，估计担心有的人背答案把.不过来刷题的人没几个人不知道递归本质就是栈的出入把。
     * 因为递归的本质：栈的出入。所以利用栈和for循环拿取栈内元素就可完成递归。
     * @param head
     * @return
     */
    private static int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack();
        while(head != null) {
            stack.push(head.val);
            head = head.nextNode;
        }
        int size = stack.size();
        int[] arr = new int[size];
        for(int i =0 ;i < size; i++) {
            arr[i] = stack.pop();
        }
        return arr;
    }
    static class ListNode {
        int val;
        ListNode nextNode;
        public ListNode(int val) {
            this.val = val;
        }
        // emptyNode init.
        public ListNode() {
        }
    }
}
