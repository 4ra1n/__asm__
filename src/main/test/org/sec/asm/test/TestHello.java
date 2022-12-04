package org.sec.asm.test;

import java.io.PrintStream;
import java.lang.invoke.MethodType;

import static org.sec.asm.core.ASM.__asm__;

public class TestHello {
    public static void main(String[] args) {
        System.out.println("test asm");
        test(args);
        System.out.println("test ok");
    }

    public static void test(String[] args) {
        __asm__(asm -> {
            asm.INIT()
                    .GETSTATIC(System.class, "out", PrintStream.class)
                    .NEW(StringBuilder.class)
                    .DUP()
                    .INVOKESPECIAL(StringBuilder.class, "<init>",
                            MethodType.methodType(Void.TYPE))
                    .LDC("hello ")
                    .INVOKEVIRTUAL(StringBuilder.class, "append",
                            MethodType.methodType(StringBuilder.class, String.class))
                    .LDC("world!")
                    .INVOKEVIRTUAL(StringBuilder.class, "append",
                            MethodType.methodType(StringBuilder.class, String.class))
                    .INVOKEVIRTUAL(StringBuilder.class, "toString",
                            MethodType.methodType(String.class))
                    .INVOKEVIRTUAL(PrintStream.class,
                            "println",
                            MethodType.methodType(Void.TYPE, String.class));
        });
    }
}

