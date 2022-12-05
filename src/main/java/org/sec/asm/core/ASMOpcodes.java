package org.sec.asm.core;

import org.objectweb.asm.*;

import java.lang.invoke.MethodType;

@SuppressWarnings("all")
public interface ASMOpcodes {
    ASMOpcodes INIT();

    ASMOpcodes BIPUSH(int value);

    ASMOpcodes SIPUSH(int value);

    ASMOpcodes NOP();

    ASMOpcodes ACONST_NULL();

    ASMOpcodes LDC(long value);

    ASMOpcodes LDC(float value);

    ASMOpcodes LDC(double value);

    ASMOpcodes LDC(String value);

    ASMOpcodes LDC(Class<?> type);

    ASMOpcodes LDC(Type type);

    ASMOpcodes LDC(MethodType type);

    ASMOpcodes LDC(Handle handle);

    ASMOpcodes LDC(ConstantDynamic constantDynamic);

    ASMOpcodes LDC_W();

    ASMOpcodes LDC_2W();

    ASMOpcodes LCONST_0();

    ASMOpcodes LCONST_1();

    ASMOpcodes DCONST_0();

    ASMOpcodes DCONST_1();

    ASMOpcodes FCONST_0();

    ASMOpcodes FCONST_1();

    ASMOpcodes FCONST_2();

    ASMOpcodes ICONST_M1();

    ASMOpcodes ICONST_0();

    ASMOpcodes ICONST_1();

    ASMOpcodes ICONST_2();

    ASMOpcodes ICONST_3();

    ASMOpcodes ICONST_4();

    ASMOpcodes ICONST_5();

    ASMOpcodes ILOAD(int idx);

    ASMOpcodes LLOAD(int idx);

    ASMOpcodes FLOAD(int idx);

    ASMOpcodes DLOAD(int idx);

    ASMOpcodes ALOAD(int idx);

    ASMOpcodes IALOAD();

    ASMOpcodes LALOAD();

    ASMOpcodes FALOAD();

    ASMOpcodes DALOAD();

    ASMOpcodes AALOAD();

    ASMOpcodes BALOAD();

    ASMOpcodes CALOAD();

    ASMOpcodes SALOAD();

    ASMOpcodes ISTORE(int idx);

    ASMOpcodes LSTORE(int idx);

    ASMOpcodes FSTORE(int idx);

    ASMOpcodes DSTORE(int idx);

    ASMOpcodes ASTORE(int idx);

    ASMOpcodes IASTORE();

    ASMOpcodes LASTORE();

    ASMOpcodes FASTORE();

    ASMOpcodes DASTORE();

    ASMOpcodes AASTORE();

    ASMOpcodes BASTORE();

    ASMOpcodes CASTORE();

    ASMOpcodes SASTORE();

    ASMOpcodes POP();

    ASMOpcodes POP2();

    ASMOpcodes DUP();

    ASMOpcodes DUPX1();

    ASMOpcodes DUPX2();

    ASMOpcodes DUP2();

    ASMOpcodes DUP2X1();

    ASMOpcodes DUP2X2();

    ASMOpcodes SWAP();

    ASMOpcodes IADD();

    ASMOpcodes LADD();

    ASMOpcodes FADD();

    ASMOpcodes DADD();

    ASMOpcodes ISUB();

    ASMOpcodes LSUB();

    ASMOpcodes FSUB();

    ASMOpcodes DSUB();

    ASMOpcodes IMUL();

    ASMOpcodes LMUL();

    ASMOpcodes FMUL();

    ASMOpcodes DMUL();

    ASMOpcodes IDIV();

    ASMOpcodes LDIV();

    ASMOpcodes FDIV();

    ASMOpcodes DDIV();

    ASMOpcodes IREM();

    ASMOpcodes LREM();

    ASMOpcodes FREM();

    ASMOpcodes DREM();

    ASMOpcodes INEG();

    ASMOpcodes LNEG();

    ASMOpcodes FNEG();

    ASMOpcodes DNEG();

    ASMOpcodes ISHL();

    ASMOpcodes LSHL();

    ASMOpcodes ISHR();

    ASMOpcodes LSHR();

    ASMOpcodes IUSHR();

    ASMOpcodes LUSHR();

    ASMOpcodes IAND();

    ASMOpcodes LAND();

    ASMOpcodes IOR();

    ASMOpcodes LOR();

    ASMOpcodes IXOR();

    ASMOpcodes LXOR();

    ASMOpcodes IINC(int idx, int value);

    ASMOpcodes I2L();

    ASMOpcodes I2F();

    ASMOpcodes I2D();

    ASMOpcodes L2I();

    ASMOpcodes L2F();

    ASMOpcodes L2D();

    ASMOpcodes F2I();

    ASMOpcodes F2L();

    ASMOpcodes F2D();

    ASMOpcodes D2I();

    ASMOpcodes D2L();

    ASMOpcodes D2F();

    ASMOpcodes I2B();

    ASMOpcodes I2C();

    ASMOpcodes I2S();

    ASMOpcodes LCMP();

    ASMOpcodes FCMPL();

    ASMOpcodes FCMPG();

    ASMOpcodes DCMPL();

    ASMOpcodes DCMPG();

    // = 0
    ASMOpcodes IFEQ(Label label);

    // != 0
    ASMOpcodes IFNE(Label label);

    // < 0
    ASMOpcodes IFLT(Label label);

    // >= 0
    ASMOpcodes IFGE(Label label);

    // > 0
    ASMOpcodes IFGT(Label label);

    // < 0
    ASMOpcodes IFLE(Label label);

    // [v2, v1]: v1 == v2
    ASMOpcodes ICMPEQ(Label label);

    // [v2, v1]: v1 != v2
    ASMOpcodes ICMPNE(Label label);

    // [v2, v1]: v1 < v2
    ASMOpcodes ICMPLT(Label label);

    // [v2, v1]: v1 >= v2
    ASMOpcodes ICMPGE(Label label);

    // [v2, v1]: v1 > v2
    ASMOpcodes ICMPGT(Label label);

    // [v2, v1]: v1 <= v2
    ASMOpcodes ICMPLE(Label label);

    // [v2, v1]: v1 == v2
    ASMOpcodes ACMPEQ(Label label);

    // [v2, v1]: v1 != v2
    ASMOpcodes ACMPNE(Label label);

    ASMOpcodes GOTO(Label label);

    ASMOpcodes GOTO_W();

    ASMOpcodes WIDE();

    ASMOpcodes TABLESWITCH(int min, int max, Label dflt, Label... labels);

    ASMOpcodes LOOKUPSWITCH(int[] keys, Label dflt, Label... labels);

    ASMOpcodes IRETURN();

    ASMOpcodes LRETURN();

    ASMOpcodes FRETURN();

    ASMOpcodes DRETURN();

    ASMOpcodes ARETURN();

    ASMOpcodes RETURN();

    ASMOpcodes RET(int index);

    ASMOpcodes JSR();

    ASMOpcodes GETSTATIC(String owner, String name, String desc);

    ASMOpcodes PUTSTATIC(String owner, String name, String desc);

    ASMOpcodes GETFIELD(String owner, String name, String desc);

    ASMOpcodes PUTFIELD(String owner, String name, String desc);

    ASMOpcodes GETSTATIC(Class<?> owner, String name, String desc);

    ASMOpcodes PUTSTATIC(Class<?> owner, String name, String desc);

    ASMOpcodes GETFIELD(Class<?> owner, String name, String desc);

    ASMOpcodes PUTFIELD(Class<?> owner, String name, String desc);

    ASMOpcodes GETSTATIC(String owner, String name, Class<?> desc);

    ASMOpcodes PUTSTATIC(String owner, String name, Class<?> desc);

    ASMOpcodes GETFIELD(String owner, String name, Class<?> desc);

    ASMOpcodes PUTFIELD(String owner, String name, Class<?> desc);

    ASMOpcodes GETSTATIC(Class<?> owner, String name, Class<?> desc);

    ASMOpcodes PUTSTATIC(Class<?> owner, String name, Class<?> desc);

    ASMOpcodes GETFIELD(Class<?> owner, String name, Class<?> desc);

    ASMOpcodes PUTFIELD(Class<?> owner, String name, Class<?> desc);

    ASMOpcodes INVOKEVIRTUAL(String owner, String name, String desc);

    ASMOpcodes INVOKESPECIAL(String owner, String name, String desc);

    ASMOpcodes INVOKESTATIC(String owner, String name, String desc);

    ASMOpcodes INVOKEINTERFACE(String owner, String name, String desc);

    ASMOpcodes INVOKEDYNAMIC(String name, String desc, Handle bootstrap, Object... args);

    ASMOpcodes INVOKEVIRTUAL(String owner, String name, MethodType type);

    ASMOpcodes INVOKESTATIC(String owner, String name, MethodType type);

    ASMOpcodes INVOKEINTERFACE(String owner, String name, MethodType type);

    ASMOpcodes INVOKEVIRTUAL(Class<?> owner, String name, String desc);

    ASMOpcodes INVOKESPECIAL(Class<?> owner, String name, String desc);

    ASMOpcodes INVOKESTATIC(Class<?> owner, String name, String desc);

    ASMOpcodes INVOKEVIRTUAL(Class<?> owner, String name, MethodType type);

    ASMOpcodes INVOKESPECIAL(Class<?> owner, String name, MethodType type);

    ASMOpcodes INVOKESTATIC(Class<?> owner, String name, MethodType type);

    ASMOpcodes INVOKEINTERFACE(Class<?> owner, String name, String desc);

    ASMOpcodes INVOKEINTERFACE(Class<?> owner, String name, MethodType type);

    ASMOpcodes NEW(String type);

    ASMOpcodes NEW(Class<?> type);

    ASMOpcodes NEW(Type type);

    ASMOpcodes NEWARRAY(int type);

    ASMOpcodes NEWARRAY(String type);

    ASMOpcodes ANEWARRAY(Class<?> type);

    ASMOpcodes ARRAYLENGTH();

    ASMOpcodes ATHROW();

    ASMOpcodes CHECKCAST(String type);

    ASMOpcodes INSTANCEOF(String type);

    ASMOpcodes MONITORENTER();

    ASMOpcodes MONITOREXIT();

    ASMOpcodes MULTINEWARRAY(String desc, int dimensions);

    ASMOpcodes IFNULL(Label label);

    ASMOpcodes IFNONNULL(Label label);

    ASMOpcodes LABEL(Label label);

    ASMOpcodes TRY(Label start, Label end, Label handler, String type);
}
