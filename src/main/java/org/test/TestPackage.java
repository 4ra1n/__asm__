package org.test;

import java.lang.invoke.MethodType;

import static com.sec.asm.core.ASMBlock.__asm__;

public class TestPackage {
    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        __asm__(asm -> {
            asm.INIT()
                    .ALOAD(1)
                    .LDC("calc.exe")
                    .INVOKEVIRTUAL(Runtime.class, "exec",
                            MethodType.methodType(Process.class, String.class));
        });
    }
}
