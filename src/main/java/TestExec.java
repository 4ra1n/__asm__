import java.lang.invoke.MethodType;

import static com.sec.asm.api.ASMBlock.__asm__;

public class TestExec {
    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        Process p = null;
        __asm__(asm -> {
            asm.INIT()
                    .ALOAD(1)
                    .LDC("calc.exe")
                    .INVOKEVIRTUAL(Runtime.class, "exec",
                            MethodType.methodType(Process.class, String.class))
                    .DUP()
                    .ASTORE(2);
//                    .POP();
        });
        System.out.println(p.getInputStream());
    }
}
