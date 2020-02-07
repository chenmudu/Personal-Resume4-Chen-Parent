package org.chen.lb.iConsistentHashBalance;

import org.chen.lb.LoadBalanceIService;
import org.chen.lb.iConsistentHashBalance.consistentHash.ConsistentHashRouter;
import org.chen.lb.serverConfig.ServerIp;
import org.chen.lb.serverConfig.node.Node;
import org.chen.lb.serverUtils.ServerIpUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
 * @Date: 2020/2/3 15:00
 * @Description:
 */
public class ConsistentHashGetServer extends LoadBalanceIService {

    private volatile ConsistentHashRouter hashRouter;

    private Random random = new Random();

    /**
     * 虚拟结点个数.... 怎么确定呢。
     */
    private int vNodeCount = 10;
    @Override
    public String getServerIp() {
        List<ServerIp> serverIps = ServerIpUtils.getServerIpsByFixedWay();
        initConsistentHashRouter(serverIps, vNodeCount);
        //call goRoute function.
        Node node = hashRouter.routeNodeServer(String.valueOf(random.nextInt(Integer.MAX_VALUE)));
        return node.getKey();
    }

    /**
     * 初始化分发器。
     * @param pNodes
     * @param vNodeCount
     */
    private void initConsistentHashRouter(Collection<?> pNodes, int vNodeCount) {
        if(Objects.isNull(hashRouter)) {
            synchronized (ConsistentHashRouter.class) {
                if(Objects.isNull(hashRouter)) {
                    hashRouter = new ConsistentHashRouter(pNodes, vNodeCount);
                }
            }
        }
    }


    public void removeNode(ServerIp serverIp) {
        if(Objects.isNull(hashRouter)) {
            throw new IllegalArgumentException("Current hash router is null.");
        }
        hashRouter.removeNode(serverIp);
    }

    public void addNode(ServerIp pNode) {
        Objects.requireNonNull(pNode, "Current add pNode is null.");
        hashRouter.addNode(pNode, vNodeCount);
    }
}
