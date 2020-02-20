package org.chen.find.hashSetContainsUtils;

import org.chen.find.domain.PurchaseOrderDto;
import org.chen.find.utils.OrderUtils;

import java.util.HashMap;
import java.util.HashSet;

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
 * @Date: 2020/2/20 20:35
 * @Description:
 */
public class HashSetContainsUtils {
    private static int ORDER_ID_Count = 9999;

    public static void  detectionHashMapContains () {
        long startTime = System.currentTimeMillis();
        HashSet<PurchaseOrderDto> purchaseOrderSet = OrderUtils.getPurchaseOrderHashSet();
        PurchaseOrderDto orderDto = new PurchaseOrderDto();
        orderDto.setOrderItemId(OrderUtils.getLOOP_FOR_ORDER_LIST() - 1);
        orderDto.setOrderItemName("Order-" + (OrderUtils.getLOOP_FOR_ORDER_LIST() - 1));
        // contains的速度还是跟hash相关。
        System.out.println(purchaseOrderSet.contains(orderDto));
        System.out.println("cost time is:" + (System.currentTimeMillis() - startTime));
    }
}
