package test;

import static com.sec.asm.core.ASM.__asm__;

public class TestObjectArray {
    public static void main(String[] args) {
        String[] arr = null;
        __asm__(asm->{
            asm.INIT()
                    .ICONST_3()
                    .ANEWARRAY(String.class)
                    .ASTORE(1)
                    .ALOAD(1)
                    .ICONST_0()
                    .LDC("test")
                    .AASTORE();
        });
        System.out.println(arr[0]);
    }
}
