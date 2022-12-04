import java.io.PrintStream;
import java.lang.invoke.MethodType;

import static com.sec.asm.api.AsmBlock.__asm__;

public class TestExec {
    public static void main(String[] args) {
        int a = 1;
        Runtime r = Runtime.getRuntime();
        __asm__(asm -> {
            asm.INIT()
                    .LDC("JUNK CODE")
                    .ISTORE(0);

        });
        System.out.println("test ok");
    }
}
