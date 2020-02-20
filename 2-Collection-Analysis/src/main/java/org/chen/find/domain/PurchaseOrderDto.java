package org.chen.find.domain;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.AllArgsConstructor;

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
 * @Date: 2020/2/12 23:52
 * @Description:
 */
@lombok.Setter
@lombok.Getter
public class PurchaseOrderDto {

    private Integer orderItemId;

    private String orderItemName;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PurchaseOrderDto orderDto = (PurchaseOrderDto) o;
        return Objects.equals(orderItemId, orderDto.orderItemId) &&
                Objects.equals(orderItemName, orderDto.orderItemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemId, orderItemName);
    }
}
