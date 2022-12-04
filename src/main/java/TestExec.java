import static com.sec.asm.api.AsmBlock.__asm__;

public class TestExec {
    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        __asm__(asm -> asm.INIT()
                .LDC("JUNK CODE")
                .DUP()
                .POP()
                .POP());
        try {
            __asm__(asm -> asm.INIT()
                    .LDC("JUNK")
                    .LDC("CODE")
                    .DUP2()
                    .POP()
                    .POP()
                    .POP()
                    .POP());
            r.exec("calc.exe");
        } catch (Exception ex) {
            __asm__(asm -> asm.INIT()
                    .LDC("JUNK CODE")
                    .POP());
        }
    }
}
