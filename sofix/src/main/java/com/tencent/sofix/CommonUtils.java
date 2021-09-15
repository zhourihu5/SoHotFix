package com.tencent.sofix;

import java.lang.reflect.Array;

public class CommonUtils {
    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    public static Object insertElementAtFirst(Object firstObj, Object array) {
        Class<?> localClass = array.getClass().getComponentType();
        int len = Array.getLength(array) + 1;
        Object result = Array.newInstance(localClass, len);
        Array.set(result, 0, firstObj);
        for (int k = 1; k < len; ++k) {
            Array.set(result, k, Array.get(array, k - 1));
        }
        return result;
    }
}
