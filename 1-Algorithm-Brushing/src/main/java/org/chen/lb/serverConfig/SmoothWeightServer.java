package org.chen.lb.serverConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.chen.lb.serverConfig.ServerIp;

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
 * @Date: 2020/1/29 01:15
 * @Description: 平滑加权轮询的基础实体类。
 *
 * 初始权值二者一致.变化的只有当前的新权值。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmoothWeightServer {

    /**
     * 当前serverIp。
     */
    private ServerIp serverIp;

    /**
     * 原始权重.
     */
    private Integer oldWeight;

    /**
     * 当前新的权重.
     * 因为每次权重都在变化。
     * 最终会走完一个定长循环。回到最初的状态。
     *
     *
     * 1. 5, 1, 1   --->  -2, 1, 1    最大的权值 - totalWeight.
     *    set newCurrentWeight = newCurrentWeight - totalWight.
     *
     * 2. -2, 1, 1  (+ 5, 1, 1)  --->    3, 2, 2
     *    所有ip的newCurrentWeight统一加上原始权重。
     *
     * 3. 然后走完定长循环之后最终的权重又会变成 5 , 1, 1. 美滋滋。
     *
     */
    private Integer newCurrentWeight;
}
