package org.chen.lb.commonUtils;

import com.sun.istack.internal.Nullable;
import org.omg.CORBA.NamedValue;

import java.util.Collection;
import java.util.Map;
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
 * @Date: 2020/1/14 00:45
 * @Description:
 */
public class CollectionUtils {

    /**
     * Collection的判断。
     * @param collection
     * @return
     */
    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    /**
     * Collection的判断。
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 借助Map的判断。
     * @param currentMap
     * @return
     */
    public static boolean mapIsEmpty(@Nullable Map<?, ?> currentMap) {
        return MapUtils.mapIsEmpty(currentMap);
    }

    /**
     * 借助Map的判断。
     * @param currentMap
     * @return
     */
    public static boolean mapIsNotEmpty(@Nullable Map<?, ?> currentMap) {
        return MapUtils.mapIsNotEmpty(currentMap);
    }

    /**
     * 不暴露给外部。
     */
    private static class MapUtils{

        public static boolean mapIsEmpty(@Nullable Map<?, ?> currentMap) {
            return Objects.isNull(currentMap) || currentMap.isEmpty();
        }

        public static boolean mapIsNotEmpty(@Nullable Map<?, ?> currentMap) {
            return !mapIsEmpty(currentMap);
        }
    }
}
