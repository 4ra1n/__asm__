import java.io.PrintStream;
import java.lang.invoke.MethodType;

import static com.sec.asm.api.AsmBlock.__asm__;

public class Test {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        System.out.println("test asm");
        __asm__(asm -> {
            asm.INIT()
                    .GETSTATIC(System.class, "out", PrintStream.class)
                    .NEW(StringBuilder.class)
                    .DUP()
                    .INVOKESPECIAL(StringBuilder.class, "<init>",
                            MethodType.methodType(Void.TYPE))
                    .LDC("hello ")
                    .INVOKEVIRTUAL(StringBuilder.class, "append",
                            MethodType.methodType(StringBuilder.class, String.class))
                    .LDC("world!")
                    .INVOKEVIRTUAL(StringBuilder.class, "append",
                            MethodType.methodType(StringBuilder.class, String.class))
                    .INVOKEVIRTUAL(StringBuilder.class, "toString",
                            MethodType.methodType(String.class))
                    .INVOKEVIRTUAL(PrintStream.class,
                            "println",
                            MethodType.methodType(Void.TYPE, String.class));
        });
        System.out.println("test ok");
    }
}

