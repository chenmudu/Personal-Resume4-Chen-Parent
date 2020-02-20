package org.chen.find.utils;

import lombok.Getter;
import org.chen.find.domain.PurchaseOrderDto;
import org.chen.lb.commonUtils.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
 * @Date: 2020/2/13 09:49
 * @Description:
 */
public class OrderUtils {

    @Getter
    private static Integer LOOP_FOR_ORDER_LIST = 10000;

    private static final String ORDER_NAME_PRE = "Order-";

    private static List<PurchaseOrderDto> ORDER_DTO_LIST = new ArrayList<>(LOOP_FOR_ORDER_LIST);

    private static HashMap<PurchaseOrderDto, Object>  ORDER_DTO_MAP = new HashMap<>(LOOP_FOR_ORDER_LIST);

    private static HashSet<PurchaseOrderDto> ORDER_DTO_SET = new HashSet<>(LOOP_FOR_ORDER_LIST);

    private static Object DEFAULT_OBJECT = new Object();

    private static void initOrderDtoList() {
        if(CollectionUtils.isNotEmpty(ORDER_DTO_LIST)) {
            return ;
        }
        initOrderList();
    }

    public static List<PurchaseOrderDto> getPurchaseOrderList() {
        initOrderDtoList();
        return ORDER_DTO_LIST;
    }

    private static void initOrderList() {
        PurchaseOrderDto orderDto = null;
        for(int i = 0; i < LOOP_FOR_ORDER_LIST; i++) {
            orderDto = new PurchaseOrderDto();
            orderDto.setOrderItemId(i + 1);
            orderDto.setOrderItemName(ORDER_NAME_PRE + (i + 1));
            ORDER_DTO_LIST.add(orderDto);
        }
    }

    public static HashMap getPurchaseOrderHashMap() {
        //
        if(CollectionUtils.mapIsNotEmpty(ORDER_DTO_MAP)) {
            return ORDER_DTO_MAP;
        }
        PurchaseOrderDto orderDto = null;
        for(int i = 0; i < LOOP_FOR_ORDER_LIST; i++) {
            orderDto = new PurchaseOrderDto();
            orderDto.setOrderItemId(i + 1);
            orderDto.setOrderItemName(ORDER_NAME_PRE + (i + 1));
            ORDER_DTO_MAP.put(orderDto, DEFAULT_OBJECT);
        }
        return ORDER_DTO_MAP;
    }

    public static HashSet getPurchaseOrderHashSet() {
        //
        if(CollectionUtils.isNotEmpty(ORDER_DTO_SET)) {
            return ORDER_DTO_SET;
        }
        PurchaseOrderDto orderDto = null;
        for(int i = 0; i < LOOP_FOR_ORDER_LIST; i++) {
            orderDto = new PurchaseOrderDto();
            orderDto.setOrderItemId(i + 1);
            orderDto.setOrderItemName(ORDER_NAME_PRE + (i + 1));
            ORDER_DTO_SET.add(orderDto);
            //ORDER_DTO_MAP.put(orderDto, DEFAULT_OBJECT);
        }
        return ORDER_DTO_SET;
    }
}
