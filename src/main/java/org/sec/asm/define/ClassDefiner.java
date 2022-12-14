package org.sec.asm.define;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

public final class ClassDefiner {
    public static ClassDefiner INSTANCE = new ClassDefiner();
    private static final Object UNSAFE;

    public Class<?> defineClass(ClassLoader loader, byte[] classBytes, int off, int len) {
        try {
            Method m = UNSAFE.getClass().getDeclaredMethod("defineClass",
                    String.class, byte[].class, int.class, int.class,
                    ClassLoader.class, ProtectionDomain.class);
            return (Class<?>) m.invoke(UNSAFE, null, classBytes, off, len, loader, null);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    static {
        try {
            Class<?> unsafe = Class.forName("sun.misc.Unsafe");
            Field field = unsafe.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = field.get(null);
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}
