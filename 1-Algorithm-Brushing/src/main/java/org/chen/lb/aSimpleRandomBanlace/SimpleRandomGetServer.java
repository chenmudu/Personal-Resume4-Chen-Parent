package org.chen.lb.aSimpleRandomBanlace;

import org.chen.lb.LoadBalanceIService;
import org.chen.lb.serverUtils.ServerIpUtils;

import java.util.Random;

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
 * @Date: 2020/1/14 00:07
 * @Description: 简单的随机算法.依据伪随机数从指定Ip列表中随机获取对应下表Ip值并返回。
 */
public class SimpleRandomGetServer extends LoadBalanceIService {
    /**
     * 随机种子。
     */
    private static Random RANDOM_SEED = new Random();

    private Integer serverIpListSize = ServerIpUtils.getServerIpListSize();

    @Override
    public String getServerIp() {
        int randomIndex = RANDOM_SEED.nextInt(serverIpListSize);
        return ServerIpUtils.getServerIpsByFixedWay().get(randomIndex).getServerIp();
    }
}
