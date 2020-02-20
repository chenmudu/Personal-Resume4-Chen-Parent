package org.chen.find.listContains;

import org.chen.find.domain.PurchaseOrderDto;
import org.chen.find.utils.OrderUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
 * @Date: 2020/2/13 16:11
 * @Description:   给定集合去判断对应对象是否包含于此集合内。 List.contains其实不算很慢。数据小的时候。
 */
public class ListContainsUtils {

    private static int ORDER_ID_Count = 9999;

    public static void detectionListContains() {
        long startTime = System.currentTimeMillis();
        List<PurchaseOrderDto> purchaseOrderList = OrderUtils.getPurchaseOrderList();
        PurchaseOrderDto orderDto = new PurchaseOrderDto();
        orderDto.setOrderItemId(ORDER_ID_Count);
        orderDto.setOrderItemName("Order-" + ORDER_ID_Count);
        //取决于传入参数的equals方法,和hashcode方法没有关系。
        //至少在ArrayList.contains()方法看来是这样。
        System.out.println(purchaseOrderList.contains(orderDto));
        System.out.println("cost time is:" + (System.currentTimeMillis() - startTime));
    }
}
