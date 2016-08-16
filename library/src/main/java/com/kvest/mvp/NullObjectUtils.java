package com.kvest.mvp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by roman on 8/16/16.
 */
public class NullObjectUtils {
    private static boolean DEFAULT_BOOLEAN;
    private static byte DEFAULT_BYTE;
    private static short DEFAULT_SHORT;
    private static int DEFAULT_INT;
    private static long DEFAULT_LONG;
    private static float DEFAULT_FLOAT;
    private static double DEFAULT_DOUBLE;
    private static char DEFAULT_CHAR;

    public static <T> T create(Class<T> clazz) {
        ClassLoader localClassLoader = clazz.getClassLoader();
        NullInvocationHandler localNullInvocationHandler = new NullInvocationHandler();
        return clazz.cast(Proxy.newProxyInstance(localClassLoader, new Class[]{clazz}, localNullInvocationHandler));
    }

    private static class NullInvocationHandler implements InvocationHandler {
        public Object invoke(Object object, Method method, Object[] args) throws Throwable {
           if (method.getReturnType().isPrimitive()) {
               if (method.getReturnType().equals(boolean.class)) {
                   return DEFAULT_BOOLEAN;
               } else if (method.getReturnType().equals(byte.class)) {
                   return DEFAULT_BYTE;
               } else if (method.getReturnType().equals(short.class)) {
                   return DEFAULT_SHORT;
               } else if (method.getReturnType().equals(int.class)) {
                   return DEFAULT_INT;
               } else if (method.getReturnType().equals(long.class)) {
                   return DEFAULT_LONG;
               } else if (method.getReturnType().equals(float.class)) {
                   return DEFAULT_FLOAT;
               } else if (method.getReturnType().equals(double.class)) {
                   return DEFAULT_DOUBLE;
               } else if (method.getReturnType().equals(char.class)) {
                return DEFAULT_CHAR;
               }
           }

            return null;
        }
    }
}
