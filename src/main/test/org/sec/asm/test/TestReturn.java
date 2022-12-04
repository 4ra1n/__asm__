package org.sec.asm.test;

import java.lang.invoke.MethodType;

import static org.sec.asm.core.ASM.__asm__;

public class TestReturn {
    public static void main(String[] args) {
        // args:0 r:1
        Runtime r = Runtime.getRuntime();
        // process:2
        Process p = null;
        __asm__(asm -> {
            asm.INIT()
                    .ALOAD(1)
                    .LDC("calc.exe")
                    .INVOKEVIRTUAL(Runtime.class, "exec",
                            MethodType.methodType(Process.class, String.class))
                    // process = returnValue
                    .ASTORE(2);
        });
        System.out.println(p.getInputStream());
    }
}
