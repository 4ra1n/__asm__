package com.sec.asm.api;

import com.sec.asm.run.Runner;
import org.objectweb.asm.ConstantDynamic;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.Type;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.invoke.MethodType;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.function.Consumer;

@SuppressWarnings("all")
public interface ASMBlock {
    static void __asm__internal__(String className) {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader) cl).getURLs();
        URL targetUrl = null;
        for (URL u : urls) {
            if (!u.toString().endsWith(".jar")) {
                targetUrl = u;
            }
        }
        if (targetUrl == null) {
            return;
        }
        try {
            Runner.run(new String[]{
                    className,
                    targetUrl.toURI().getPath().substring(1)
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!ASMConst.hasPrinted) {
            System.out.println("|----------------ASM----------------|");
            System.out.println("|  Please Restart Java Application  |");
            System.out.println("|-----------------------------------|");
            System.setOut(new PrintStream(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                }
            }));
            System.setErr(new PrintStream(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                }
            }));
            ASMConst.hasPrinted = true;
        }
    }

    static void __asm__(Consumer<ASMBlock> blocker) {
        __asm__internal__(
                blocker.getClass().getName().split("\\$\\$")[0]
        );
    }

    ASMBlock INIT();

    ASMBlock NOP();

    ASMBlock NULL();

    ASMBlock INT(int value);

    ASMBlock LONG(long value);

    ASMBlock FLOAT(float value);

    ASMBlock DOUBLE(double value);

    ASMBlock LDC(String value);

    ASMBlock LDC(Class<?> type);

    ASMBlock LDC(Type type);

    ASMBlock LDC(MethodType type);

    ASMBlock LDC(Handle handle);

    ASMBlock LDC(ConstantDynamic constantDynamic);

    ASMBlock ILOAD(int idx);

    ASMBlock LLOAD(int idx);

    ASMBlock FLOAD(int idx);

    ASMBlock DLOAD(int idx);

    ASMBlock ALOAD(int idx);

    ASMBlock IALOAD();

    ASMBlock LALOAD();

    ASMBlock FALOAD();

    ASMBlock DALOAD();

    ASMBlock AALOAD();

    ASMBlock BALOAD();

    ASMBlock CALOAD();

    ASMBlock SALOAD();

    ASMBlock ISTORE(int idx);

    ASMBlock LSTORE(int idx);

    ASMBlock FSTORE(int idx);

    ASMBlock DSTORE(int idx);

    ASMBlock ASTORE(int idx);

    ASMBlock IASTORE();

    ASMBlock LASTORE();

    ASMBlock FASTORE();

    ASMBlock DASTORE();

    ASMBlock AASTORE();

    ASMBlock BASTORE();

    ASMBlock CASTORE();

    ASMBlock SASTORE();

    ASMBlock POP();

    ASMBlock POP2();

    ASMBlock DUP();

    ASMBlock DUPX1();

    ASMBlock DUPX2();

    ASMBlock DUP2();

    ASMBlock DUP2X1();

    ASMBlock DUP2X2();

    ASMBlock SWAP();

    ASMBlock IADD();

    ASMBlock LADD();

    ASMBlock FADD();

    ASMBlock DADD();

    ASMBlock ISUB();

    ASMBlock LSUB();

    ASMBlock FSUB();

    ASMBlock DSUB();

    ASMBlock IMUL();

    ASMBlock LMUL();

    ASMBlock FMUL();

    ASMBlock DMUL();

    ASMBlock IDIV();

    ASMBlock LDIV();

    ASMBlock FDIV();

    ASMBlock DDIV();

    ASMBlock IREM();

    ASMBlock LREM();

    ASMBlock FREM();

    ASMBlock DREM();

    ASMBlock INEG();

    ASMBlock LNEG();

    ASMBlock FNEG();

    ASMBlock DNEG();

    ASMBlock ISHL();

    ASMBlock LSHL();

    ASMBlock ISHR();

    ASMBlock LSHR();

    ASMBlock IUSHR();

    ASMBlock LUSHR();

    ASMBlock IAND();

    ASMBlock LAND();

    ASMBlock IOR();

    ASMBlock LOR();

    ASMBlock IXOR();

    ASMBlock LXOR();

    ASMBlock IINC(int idx, int value);

    ASMBlock I2L();

    ASMBlock I2F();

    ASMBlock I2D();

    ASMBlock L2I();

    ASMBlock L2F();

    ASMBlock L2D();

    ASMBlock F2I();

    ASMBlock F2L();

    ASMBlock F2D();

    ASMBlock D2I();

    ASMBlock D2L();

    ASMBlock D2F();

    ASMBlock I2B();

    ASMBlock I2C();

    ASMBlock I2S();

    ASMBlock LCMP();

    ASMBlock FCMPL();

    ASMBlock FCMPG();

    ASMBlock DCMPL();

    ASMBlock DCMPG();

    // = 0
    ASMBlock IFEQ(Label label);

    // != 0
    ASMBlock IFNE(Label label);

    // < 0
    ASMBlock IFLT(Label label);

    // >= 0
    ASMBlock IFGE(Label label);

    // > 0
    ASMBlock IFGT(Label label);

    // < 0
    ASMBlock IFLE(Label label);

    // [v2, v1]: v1 == v2
    ASMBlock ICMPEQ(Label label);

    // [v2, v1]: v1 != v2
    ASMBlock ICMPNE(Label label);

    // [v2, v1]: v1 < v2
    ASMBlock ICMPLT(Label label);

    // [v2, v1]: v1 >= v2
    ASMBlock ICMPGE(Label label);

    // [v2, v1]: v1 > v2
    ASMBlock ICMPGT(Label label);

    // [v2, v1]: v1 <= v2
    ASMBlock ICMPLE(Label label);

    // [v2, v1]: v1 == v2
    ASMBlock ACMPEQ(Label label);

    // [v2, v1]: v1 != v2
    ASMBlock ACMPNE(Label label);

    ASMBlock GOTO(Label label);

    ASMBlock TABLESWITCH(int min, int max, Label dflt, Label... labels);

    ASMBlock LOOKUPSWITCH(int[] keys, Label dflt, Label... labels);

    ASMBlock IRETURN();

    ASMBlock LRETURN();

    ASMBlock FRETURN();

    ASMBlock DRETURN();

    ASMBlock ARETURN();

    ASMBlock RETURN();

    ASMBlock GETSTATIC(String owner, String name, String desc);

    ASMBlock PUTSTATIC(String owner, String name, String desc);

    ASMBlock GETFIELD(String owner, String name, String desc);

    ASMBlock PUTFIELD(String owner, String name, String desc);

    ASMBlock GETSTATIC(Class<?> owner, String name, String desc);

    ASMBlock PUTSTATIC(Class<?> owner, String name, String desc);

    ASMBlock GETFIELD(Class<?> owner, String name, String desc);

    ASMBlock PUTFIELD(Class<?> owner, String name, String desc);

    ASMBlock GETSTATIC(String owner, String name, Class<?> desc);

    ASMBlock PUTSTATIC(String owner, String name, Class<?> desc);

    ASMBlock GETFIELD(String owner, String name, Class<?> desc);

    ASMBlock PUTFIELD(String owner, String name, Class<?> desc);

    ASMBlock GETSTATIC(Class<?> owner, String name, Class<?> desc);

    ASMBlock PUTSTATIC(Class<?> owner, String name, Class<?> desc);

    ASMBlock GETFIELD(Class<?> owner, String name, Class<?> desc);

    ASMBlock PUTFIELD(Class<?> owner, String name, Class<?> desc);

    ASMBlock INVOKEVIRTUAL(String owner, String name, String desc);

    ASMBlock INVOKESPECIAL(String owner, String name, String desc);

    ASMBlock INVOKESTATIC(String owner, String name, String desc);

    ASMBlock INVOKEINTERFACE(String owner, String name, String desc);

    ASMBlock INVOKEDYNAMIC(String name, String desc, Handle bootstrap, Object... args);

    ASMBlock INVOKEVIRTUAL(String owner, String name, MethodType type);

    ASMBlock INVOKESTATIC(String owner, String name, MethodType type);

    ASMBlock INVOKEINTERFACE(String owner, String name, MethodType type);

    ASMBlock INVOKEVIRTUAL(Class<?> owner, String name, String desc);

    ASMBlock INVOKESPECIAL(Class<?> owner, String name, String desc);

    ASMBlock INVOKESTATIC(Class<?> owner, String name, String desc);

    ASMBlock INVOKEVIRTUAL(Class<?> owner, String name, MethodType type);

    ASMBlock INVOKESPECIAL(Class<?> owner, String name, MethodType type);

    ASMBlock INVOKESTATIC(Class<?> owner, String name, MethodType type);

    ASMBlock INVOKEINTERFACE(Class<?> owner, String name, String desc);

    ASMBlock INVOKEINTERFACE(Class<?> owner, String name, MethodType type);

    ASMBlock NEW(String type);

    ASMBlock NEW(Class<?> type);

    ASMBlock NEW(Type type);

    ASMBlock NEWARRAY(int type);

    ASMBlock NEWARRAY(String type);

    ASMBlock ARRAYLENGTH();

    ASMBlock ATHROW();

    ASMBlock CHECKCAST(String type);

    ASMBlock INSTANCEOF(String type);

    ASMBlock MONITORENTER();

    ASMBlock MONITOREXIT();

    ASMBlock MULTINEWARRAY(String desc, int dimensions);

    // v == null
    ASMBlock IFNULL(Label label);

    // v != null
    ASMBlock IFNONNULL(Label label);

    ASMBlock LABEL(Label label);

    ASMBlock TRY(Label start, Label end, Label handler, String type);

    ASMBlock LINE(int line, Label start);

    ASMBlock PARAMETER(String name, int access);

    ASMBlock ARRAY(long[] array, int off, int len);

    ASMBlock ARRAY(double[] array, int off, int len);

    ASMBlock ARRAY(int[] array, int off, int len);

    ASMBlock ARRAY(float[] array, int off, int len);

    ASMBlock ARRAY(char[] array, int off, int len);

    ASMBlock ARRAY(short[] array, int off, int len);

    ASMBlock ARRAY(byte[] array, int off, int len);

    ASMBlock ARRAY(boolean[] array, int off, int len);

    <T> ASMBlock ARRAY(T[] array, int off, int len, BiIntConsumer<T> producer);

    ASMBlock ARRAY(long[] array);

    ASMBlock ARRAY(double[] array);

    ASMBlock ARRAY(int[] array);

    ASMBlock ARRAY(float[] array);

    ASMBlock ARRAY(char[] array);

    ASMBlock ARRAY(short[] array);

    ASMBlock ARRAY(byte[] array);

    ASMBlock ARRAY(boolean[] array);

    <T> ASMBlock ARRAY(T[] array, BiIntConsumer<T> producer);
}
