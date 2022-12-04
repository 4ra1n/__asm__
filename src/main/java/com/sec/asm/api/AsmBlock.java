package com.sec.asm.api;

import org.objectweb.asm.ConstantDynamic;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.Type;

import java.lang.invoke.MethodType;
import java.util.function.Consumer;

@SuppressWarnings("all")
public interface AsmBlock {
    static void __asm__(Consumer<AsmBlock> blocker) {
        throw new UnsupportedOperationException("Must be replaced with bytecode");
    }

    static long __asm__J(Consumer<AsmBlock> blocker) {
        throw new UnsupportedOperationException("Must be replaced with bytecode");
    }

    static double __asm__D(Consumer<AsmBlock> blocker) {
        throw new UnsupportedOperationException("Must be replaced with bytecode");
    }

    static int __asm__I(Consumer<AsmBlock> blocker) {
        throw new UnsupportedOperationException("Must be replaced with bytecode");
    }

    static float __asm__F(Consumer<AsmBlock> blocker) {
        throw new UnsupportedOperationException("Must be replaced with bytecode");
    }

    static char __asm__C(Consumer<AsmBlock> blocker) {
        throw new UnsupportedOperationException("Must be replaced with bytecode");
    }

    static short __asm__S(Consumer<AsmBlock> blocker) {
        throw new UnsupportedOperationException("Must be replaced with bytecode");
    }

    static byte __asm__B(Consumer<AsmBlock> blocker) {
        throw new UnsupportedOperationException("Must be replaced with bytecode");
    }

    static boolean __asm__Z(Consumer<AsmBlock> blocker) {
        throw new UnsupportedOperationException("Must be replaced with bytecode");
    }

    static <V> V __asm__A(Consumer<AsmBlock> blocker) {
        throw new UnsupportedOperationException("Must be replaced with bytecode");
    }

    AsmBlock INIT();

    AsmBlock NOP();

    AsmBlock NULL();

    AsmBlock INT(int value);

    AsmBlock LONG(long value);

    AsmBlock FLOAT(float value);

    AsmBlock DOUBLE(double value);

    AsmBlock LDC(String value);

    AsmBlock LDC(Class<?> type);

    AsmBlock LDC(Type type);

    AsmBlock LDC(MethodType type);

    AsmBlock LDC(Handle handle);

    AsmBlock LDC(ConstantDynamic constantDynamic);

    AsmBlock ILOAD(int idx);

    AsmBlock LLOAD(int idx);

    AsmBlock FLOAD(int idx);

    AsmBlock DLOAD(int idx);

    AsmBlock ALOAD(int idx);

    AsmBlock IALOAD();

    AsmBlock LALOAD();

    AsmBlock FALOAD();

    AsmBlock DALOAD();

    AsmBlock AALOAD();

    AsmBlock BALOAD();

    AsmBlock CALOAD();

    AsmBlock SALOAD();

    AsmBlock ISTORE(int idx);

    AsmBlock LSTORE(int idx);

    AsmBlock FSTORE(int idx);

    AsmBlock DSTORE(int idx);

    AsmBlock ASTORE(int idx);

    AsmBlock IASTORE();

    AsmBlock LASTORE();

    AsmBlock FASTORE();

    AsmBlock DASTORE();

    AsmBlock AASTORE();

    AsmBlock BASTORE();

    AsmBlock CASTORE();

    AsmBlock SASTORE();

    AsmBlock POP();

    AsmBlock POP2();

    AsmBlock DUP();

    AsmBlock DUPX1();

    AsmBlock DUPX2();

    AsmBlock DUP2();

    AsmBlock DUP2X1();

    AsmBlock DUP2X2();

    AsmBlock SWAP();

    AsmBlock IADD();

    AsmBlock LADD();

    AsmBlock FADD();

    AsmBlock DADD();

    AsmBlock ISUB();

    AsmBlock LSUB();

    AsmBlock FSUB();

    AsmBlock DSUB();

    AsmBlock IMUL();

    AsmBlock LMUL();

    AsmBlock FMUL();

    AsmBlock DMUL();

    AsmBlock IDIV();

    AsmBlock LDIV();

    AsmBlock FDIV();

    AsmBlock DDIV();

    AsmBlock IREM();

    AsmBlock LREM();

    AsmBlock FREM();

    AsmBlock DREM();

    AsmBlock INEG();

    AsmBlock LNEG();

    AsmBlock FNEG();

    AsmBlock DNEG();

    AsmBlock ISHL();

    AsmBlock LSHL();

    AsmBlock ISHR();

    AsmBlock LSHR();

    AsmBlock IUSHR();

    AsmBlock LUSHR();

    AsmBlock IAND();

    AsmBlock LAND();

    AsmBlock IOR();

    AsmBlock LOR();

    AsmBlock IXOR();

    AsmBlock LXOR();

    AsmBlock IINC(int idx, int value);

    AsmBlock I2L();

    AsmBlock I2F();

    AsmBlock I2D();

    AsmBlock L2I();

    AsmBlock L2F();

    AsmBlock L2D();

    AsmBlock F2I();

    AsmBlock F2L();

    AsmBlock F2D();

    AsmBlock D2I();

    AsmBlock D2L();

    AsmBlock D2F();

    AsmBlock I2B();

    AsmBlock I2C();

    AsmBlock I2S();

    AsmBlock LCMP();

    AsmBlock FCMPL();

    AsmBlock FCMPG();

    AsmBlock DCMPL();

    AsmBlock DCMPG();

    // = 0
    AsmBlock IFEQ(Label label);

    // != 0
    AsmBlock IFNE(Label label);

    // < 0
    AsmBlock IFLT(Label label);

    // >= 0
    AsmBlock IFGE(Label label);

    // > 0
    AsmBlock IFGT(Label label);

    // < 0
    AsmBlock IFLE(Label label);

    // [v2, v1]: v1 == v2
    AsmBlock ICMPEQ(Label label);

    // [v2, v1]: v1 != v2
    AsmBlock ICMPNE(Label label);

    // [v2, v1]: v1 < v2
    AsmBlock ICMPLT(Label label);

    // [v2, v1]: v1 >= v2
    AsmBlock ICMPGE(Label label);

    // [v2, v1]: v1 > v2
    AsmBlock ICMPGT(Label label);

    // [v2, v1]: v1 <= v2
    AsmBlock ICMPLE(Label label);

    // [v2, v1]: v1 == v2
    AsmBlock ACMPEQ(Label label);

    // [v2, v1]: v1 != v2
    AsmBlock ACMPNE(Label label);

    AsmBlock GOTO(Label label);

    AsmBlock TABLESWITCH(int min, int max, Label dflt, Label... labels);

    AsmBlock LOOKUPSWITCH(int[] keys, Label dflt, Label... labels);

    AsmBlock IRETURN();

    AsmBlock LRETURN();

    AsmBlock FRETURN();

    AsmBlock DRETURN();

    AsmBlock ARETURN();

    AsmBlock RETURN();

    AsmBlock GETSTATIC(String owner, String name, String desc);

    AsmBlock PUTSTATIC(String owner, String name, String desc);

    AsmBlock GETFIELD(String owner, String name, String desc);

    AsmBlock PUTFIELD(String owner, String name, String desc);

    AsmBlock GETSTATIC(Class<?> owner, String name, String desc);

    AsmBlock PUTSTATIC(Class<?> owner, String name, String desc);

    AsmBlock GETFIELD(Class<?> owner, String name, String desc);

    AsmBlock PUTFIELD(Class<?> owner, String name, String desc);

    AsmBlock GETSTATIC(String owner, String name, Class<?> desc);

    AsmBlock PUTSTATIC(String owner, String name, Class<?> desc);

    AsmBlock GETFIELD(String owner, String name, Class<?> desc);

    AsmBlock PUTFIELD(String owner, String name, Class<?> desc);

    AsmBlock GETSTATIC(Class<?> owner, String name, Class<?> desc);

    AsmBlock PUTSTATIC(Class<?> owner, String name, Class<?> desc);

    AsmBlock GETFIELD(Class<?> owner, String name, Class<?> desc);

    AsmBlock PUTFIELD(Class<?> owner, String name, Class<?> desc);

    AsmBlock INVOKEVIRTUAL(String owner, String name, String desc);

    AsmBlock INVOKESPECIAL(String owner, String name, String desc);

    AsmBlock INVOKESTATIC(String owner, String name, String desc);

    AsmBlock INVOKEINTERFACE(String owner, String name, String desc);

    AsmBlock INVOKEDYNAMIC(String name, String desc, Handle bootstrap, Object... args);

    AsmBlock INVOKEVIRTUAL(String owner, String name, MethodType type);

    AsmBlock INVOKESTATIC(String owner, String name, MethodType type);

    AsmBlock INVOKEINTERFACE(String owner, String name, MethodType type);

    AsmBlock INVOKEVIRTUAL(Class<?> owner, String name, String desc);

    AsmBlock INVOKESPECIAL(Class<?> owner, String name, String desc);

    AsmBlock INVOKESTATIC(Class<?> owner, String name, String desc);

    AsmBlock INVOKEVIRTUAL(Class<?> owner, String name, MethodType type);

    AsmBlock INVOKESPECIAL(Class<?> owner, String name, MethodType type);

    AsmBlock INVOKESTATIC(Class<?> owner, String name, MethodType type);

    AsmBlock INVOKEINTERFACE(Class<?> owner, String name, String desc);

    AsmBlock INVOKEINTERFACE(Class<?> owner, String name, MethodType type);

    AsmBlock NEW(String type);

    AsmBlock NEW(Class<?> type);

    AsmBlock NEW(Type type);

    AsmBlock NEWARRAY(int type);

    AsmBlock NEWARRAY(String type);

    AsmBlock ARRAYLENGTH();

    AsmBlock ATHROW();

    AsmBlock CHECKCAST(String type);

    AsmBlock INSTANCEOF(String type);

    AsmBlock MONITORENTER();

    AsmBlock MONITOREXIT();

    AsmBlock MULTINEWARRAY(String desc, int dimensions);

    // v == null
    AsmBlock IFNULL(Label label);

    // v != null
    AsmBlock IFNONNULL(Label label);

    AsmBlock LABEL(Label label);

    AsmBlock TRY(Label start, Label end, Label handler, String type);

    AsmBlock LINE(int line, Label start);

    AsmBlock PARAMETER(String name, int access);

    AsmBlock ARRAY(long[] array, int off, int len);

    AsmBlock ARRAY(double[] array, int off, int len);

    AsmBlock ARRAY(int[] array, int off, int len);

    AsmBlock ARRAY(float[] array, int off, int len);

    AsmBlock ARRAY(char[] array, int off, int len);

    AsmBlock ARRAY(short[] array, int off, int len);

    AsmBlock ARRAY(byte[] array, int off, int len);

    AsmBlock ARRAY(boolean[] array, int off, int len);

    <T> AsmBlock ARRAY(T[] array, int off, int len, BiIntConsumer<T> producer);

    AsmBlock ARRAY(long[] array);

    AsmBlock ARRAY(double[] array);

    AsmBlock ARRAY(int[] array);

    AsmBlock ARRAY(float[] array);

    AsmBlock ARRAY(char[] array);

    AsmBlock ARRAY(short[] array);

    AsmBlock ARRAY(byte[] array);

    AsmBlock ARRAY(boolean[] array);

    <T> AsmBlock ARRAY(T[] array, BiIntConsumer<T> producer);
}
