package org.chen.lb.commonUtils;

import com.sun.istack.internal.Nullable;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.Objects;
import java.util.jar.JarEntry;

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
 * @Date: 2020/1/19 21:27
 * @Description:
 */
public class StringUtils {

    //空字符串。
    public static final String EMPTY_STRING = "";

    public static boolean isEmpty(@Nullable String currentStr) {
        return Objects.isNull(currentStr.trim()) || currentStr.trim().length() == 0;
    }
}
