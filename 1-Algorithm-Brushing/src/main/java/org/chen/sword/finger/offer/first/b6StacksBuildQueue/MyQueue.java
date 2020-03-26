package org.chen.sword.finger.offer.first.b6StacksBuildQueue;

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
 * @Date: 2020/3/26 20:33
 * @Description: 两个栈去实现队列。(简单)
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在
 * 队列尾部插入整数和在
 * 队列头部删除整数
 * 的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * 示例 1：
 *
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 *
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 */
public class MyQueue {
    private Stack<Integer> masterQueue;

    private Stack<Integer> slaveQueue;

    private volatile Integer size;

    public MyQueue() {
        this.masterQueue = new Stack<>();
        this.slaveQueue = new Stack<>();
        this.size = 0;
    }

    /**
     *  过程如下：
     *      当然你也可以把所有的数据都放在stack1内。当删除的时候在stack1和stack2中去交换数据。
     *  1, 2, 3, 4, 5
     * m : 1
     * s :
     * m : 2
     * s : 1
     * m : 2, 1
     * s:
     * @param value
     */
    public void appendTail(int value) {
        while(!masterQueue.isEmpty()) {
            slaveQueue.push(masterQueue.pop());
        }
        masterQueue.push(value);
        while(!slaveQueue.isEmpty()) {
            masterQueue.push(slaveQueue.pop());
        }
        ++size;
    }

    public int deleteHead() {
        if(size == 0) {
            return -1;
        }
        int head = masterQueue.pop();
        --size;
        return head;
    }
}
