package org.chen.lb.serverConfig;

import lombok.*;
import org.chen.lb.serverConfig.node.Node;

import java.io.Serializable;

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
 * @Date: 2020/1/14 00:09
 * @Description:
 */
//@EqualsAndHashCode
//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ServerIp implements Serializable, Node {

    private transient String  serverIp;
    // 很久之前就因为equals和hashcode的东西做过比较。
    //还追看了guava, commons-lang3的重写方式.

    //从前去背hashcode和equals的东西。
    //后来发现当你用集合的时候。比如List.contains(Object obj) 或 HashMap, LinkedHashMap的时候就需要了。

    //重写equals不一定需要重写hashcode的方法。具体看你调用的JDK源码。
    //比如List.contains就不需要.源码只是调用了equals方法.
    //至于为什么建议两个一起重写的原因是在于：重写了equals方法你可能是需要Hash相关的集合。
    //所以才会给出最佳时间是二者同时写。

    /**
     * 1.hash的O(1)是牺牲了最开始的计算时间。
     * 2.hash冲突是不可避免.良好的hash计算方法真的很厉害。
     * 3.hash冲突的三种解决方法:大三数据结构倒数第三章的 单向/双向线性探测法, 以及拉链法.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        //强转去比较对应
        ServerIp serverIp1 = (ServerIp) o;
        return serverIp != null ? serverIp.equals(serverIp1.serverIp) : serverIp1.serverIp == null;
    }

    @Override
    public int hashCode() {
        return serverIp != null ? serverIp.hashCode() : 0;
    }

    @Override
    public String getKey() {
        return serverIp;
    }
}
