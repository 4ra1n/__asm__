package com.sec.asm.test;

import com.sec.asm.api.AsmBlock;

import java.io.PrintStream;
import java.lang.invoke.MethodType;

public class Test {
    public static void main(String[] args) {
        int a = 1;
        int c = 2;
        AsmBlock.inline(b -> {
            b.getstatic(System.class, "out", PrintStream.class)
                    .ldc("Hello, World!")
                    .ldc("Hello, World!")
                    .pop()
                    .invokevirtual(PrintStream.class, "println",
                            MethodType.methodType(Void.TYPE, String.class));
            // Note that RETURN will be added by javac.
        });
        System.out.println(a+c);
    }
}
