package org.sec.asm.test;

import java.io.PrintStream;
import java.lang.invoke.MethodType;

import static org.sec.asm.core.ASM.__asm__;

public class TestHello {
    public static void main(String[] args) {
        System.out.println("test __asm__");
        __asm__(asm -> asm.INIT()
                .GETSTATIC(System.class, "out", PrintStream.class)
                .LDC("hello world!")
                .INVOKEVIRTUAL(PrintStream.class, "println",
                        MethodType.methodType(Void.TYPE, String.class)));
        System.out.println("test end");
    }
}

