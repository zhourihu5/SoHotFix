package com.tencent.sofix;

import java.lang.reflect.Field;
import java.util.Objects;

public class ReflectUtil {
    private ReflectUtil() {
        throw new UnsupportedOperationException();
    }

    public static Field getFiled(Object object, String filedName) {
        Class<?> objectClass = object.getClass();
        while (!CommonUtils.equals(objectClass.getName(), Object.class.getName())) {
            try {
                Field field = objectClass.getDeclaredField(filedName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                objectClass = objectClass.getSuperclass();
            }
        }
        return null;
    }

    public static Object getFiledValue(Object object, String filedName) {
        Field field = getFiled(object, filedName);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
