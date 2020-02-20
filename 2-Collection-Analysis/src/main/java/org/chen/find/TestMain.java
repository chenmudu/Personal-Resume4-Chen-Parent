package org.chen.find;

import org.chen.find.hashMapContains.HashMapContainsUtils;
import org.chen.find.hashSetContainsUtils.HashSetContainsUtils;
import org.chen.find.listContains.ListContainsUtils;

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
 * @Date: 2020/2/12 23:50
 * @Description: 业务中常用得几种对比数据得方式。
 * 1. List.Contains(Object obj) 用到了Object对象得equals方法,但是没有用到HashCode方法。具体看下源码。
 * 2. HashMap.containsKey(Object obj) 其实此方式用到了Hash的O(1)时间复杂度。我们模拟的是HashSet的源码。
 * 3. HashSet.contains(Object obj) 还是hash。跟hash相关的需要重写hashcode。equeals和hashcode一起写吧。
 * 4. 目前已经是BitSet，可以做很多了。
 * 5. 最后令人激动的应该是布隆过滤器。
 *  数据量从1 ->1K ->10W ->1M -> 100M.
 */
public class TestMain {
    public static void main(String[] args) {
        //ListContainsUtils.detectionListContains();
        //HashMapContainsUtils.detectionHashMapContains();
        HashSetContainsUtils.detectionHashMapContains();
    }
}
