package org.chen.lb.serverConfig.node.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.chen.lb.serverConfig.node.Node;

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
 * @Date: 2020/2/3 15:20
 * @Description:
 */
@NoArgsConstructor
public class VirtualNode<T extends Node> implements Node {
    /**
     * pNode.
     */
    private T physicalNode;

    private int copyIndex;


    public VirtualNode(T physicalNode, Integer copyIndex) {
        this.physicalNode = physicalNode;
        this.copyIndex = copyIndex;
    }


    @Override
    public String getKey() {
        return physicalNode + "-" + copyIndex;
    }

    public T getPhysicalNode() {
        return this.physicalNode;
    }

    public boolean isVirtualNodeOf(T pNode) {
        return physicalNode.getKey().equals(pNode.getKey());
    }
}
