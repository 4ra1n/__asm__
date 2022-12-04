package test;

import static com.sec.asm.core.ASM.__asm__;
import static org.objectweb.asm.Opcodes.T_INT;

public class TestMulti {
    public static void main(String[] args) {
        int[] arr = null;
        __asm__(asm->{
            asm.INIT()
                    .ICONST_3()
                    .NEWARRAY(T_INT)
                    .ASTORE(1)
                    .ALOAD(1)
                    .ICONST_0()
                    .ICONST_1()
                    .IASTORE();
        });
        __asm__(asm->{
            asm.INIT()
                    .ICONST_3()
                    .NEWARRAY(T_INT)
                    .ASTORE(1)
                    .ALOAD(1)
                    .ICONST_0()
                    .ICONST_1()
                    .IASTORE();
        });
        __asm__(asm->{
            asm.INIT()
                    .ICONST_3()
                    .NEWARRAY(T_INT)
                    .ASTORE(1)
                    .ALOAD(1)
                    .ICONST_0()
                    .ICONST_1()
                    .IASTORE();
        });
        __asm__(asm->{
            asm.INIT()
                    .ICONST_3()
                    .NEWARRAY(T_INT)
                    .ASTORE(1)
                    .ALOAD(1)
                    .ICONST_0()
                    .ICONST_1()
                    .IASTORE();
        });
        __asm__(asm->{
            asm.INIT()
                    .ICONST_3()
                    .NEWARRAY(T_INT)
                    .ASTORE(1)
                    .ALOAD(1)
                    .ICONST_0()
                    .ICONST_1()
                    .IASTORE();
        });
        System.out.println(arr[0]);
    }
}
