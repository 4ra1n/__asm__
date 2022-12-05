package org.sec.asm.test;

import java.lang.invoke.MethodType;

import static org.sec.asm.core.ASM.__asm__;

@SuppressWarnings("all")
public class TestExec {
    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        Process p = null;
        __asm__(asm -> asm.INIT()
                .ALOAD(1)
                .LDC("open /System/Applications/Calculator.app")
                .INVOKEVIRTUAL(Runtime.class, "exec",
                        MethodType.methodType(Process.class, String.class))
                .ASTORE(2)
        );
        if (p != null) {
            System.out.println("not null");
        }
    }
}
