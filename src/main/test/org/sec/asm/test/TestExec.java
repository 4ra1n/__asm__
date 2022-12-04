package org.sec.asm.test;

import java.lang.invoke.MethodType;

import static org.sec.asm.core.ASM.__asm__;

public class TestExec {
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
