package com.sec.asm.core;

import com.sec.asm.api.ASMBlock;
import com.sec.asm.api.BiIntConsumer;
import org.objectweb.asm.*;

import java.lang.invoke.MethodType;

import static org.objectweb.asm.Opcodes.*;

@SuppressWarnings("all")
public class VisitingASMBlock implements ASMBlock {

    private final MethodVisitor visitor;

    public VisitingASMBlock(MethodVisitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public ASMBlock INIT() {
        return this;
    }

    @Override
    public ASMBlock NOP() {
        return visitInsn(NOP);
    }

    @Override
    public ASMBlock NULL() {
        return visitInsn(ACONST_NULL);
    }

    @Override
    public ASMBlock INT(int value) {
        MethodVisitor visitor = this.visitor;
        if (value >= -1 && value <= 5) {
            visitor.visitInsn(value + 3);
        } else if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE) {
            visitor.visitIntInsn(BIPUSH, value);
        } else if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE) {
            visitor.visitIntInsn(SIPUSH, value);
        } else {
            visitor.visitLdcInsn(value);
        }
        return this;
    }

    @Override
    public ASMBlock LONG(long value) {
        MethodVisitor visitor = this.visitor;
        if (value == 0L || value == 1L) {
            visitor.visitInsn((int) (value + 9));
        } else {
            visitor.visitLdcInsn(value);
        }
        return this;
    }

    @Override
    public ASMBlock FLOAT(float value) {
        MethodVisitor visitor = this.visitor;
        if (value == 0.0F || value == 1.0F || value == 2.0F) {
            visitor.visitInsn((int) value + 11);
        } else {
            visitor.visitLdcInsn(value);
        }
        return this;
    }

    @Override
    public ASMBlock DOUBLE(double value) {
        MethodVisitor visitor = this.visitor;
        if (value == 0.0 || value == 1.0) {
            visitor.visitInsn((int) value + 14);
        } else {
            visitor.visitLdcInsn(value);
        }
        return this;
    }

    @Override
    public ASMBlock LDC(String value) {
        return visitLdcInsn(value);
    }

    @Override
    public ASMBlock LDC(Class<?> type) {
        return visitLdcInsn(Type.getType(type));
    }

    @Override
    public ASMBlock LDC(Type type) {
        return visitLdcInsn(type);
    }

    @Override
    public ASMBlock LDC(MethodType type) {
        Class<?>[] $parameters = type.parameterArray();
        int j = $parameters.length;
        Type[] parameters = new Type[j];
        while (j-- > 0) {
            parameters[j] = Type.getType($parameters[j]);
        }
        return visitLdcInsn(Type.getMethodType(Type.getType(type.returnType()), parameters));
    }

    @Override
    public ASMBlock LDC(Handle handle) {
        return visitLdcInsn(handle);
    }

    @Override
    public ASMBlock LDC(ConstantDynamic constantDynamic) {
        return visitLdcInsn(constantDynamic);
    }

    @Override
    public ASMBlock LDC_W() {
        throw new RuntimeException("ASM NOT SUPPORT LDC_W");
    }

    @Override
    public ASMBlock LDC_2W() {
        throw new RuntimeException("ASM NOT SUPPORT LDC_2W");
    }

    @Override
    public ASMBlock ICONST_M1() {
        return INT(-1);
    }

    @Override
    public ASMBlock ICONST_0() {
        return INT(0);
    }

    @Override
    public ASMBlock ICONST_1() {
        return INT(1);
    }

    @Override
    public ASMBlock ICONST_2() {
        return INT(2);
    }

    @Override
    public ASMBlock ICONST_3() {
        return INT(3);
    }

    @Override
    public ASMBlock ICONST_4() {
        return INT(4);
    }

    @Override
    public ASMBlock ICONST_5() {
        return INT(5);
    }

    @Override
    public ASMBlock ILOAD(int idx) {
        return visitVarInsn(ILOAD, idx);
    }

    @Override
    public ASMBlock LLOAD(int idx) {
        return visitVarInsn(LLOAD, idx);
    }

    @Override
    public ASMBlock FLOAD(int idx) {
        return visitVarInsn(FLOAD, idx);
    }

    @Override
    public ASMBlock DLOAD(int idx) {
        return visitVarInsn(DLOAD, idx);
    }

    @Override
    public ASMBlock ALOAD(int idx) {
        return visitVarInsn(ALOAD, idx);
    }

    @Override
    public ASMBlock IALOAD() {
        return visitInsn(IALOAD);
    }

    @Override
    public ASMBlock LALOAD() {
        return visitInsn(LALOAD);
    }

    @Override
    public ASMBlock FALOAD() {
        return visitInsn(FALOAD);
    }

    @Override
    public ASMBlock DALOAD() {
        return visitInsn(DALOAD);
    }

    @Override
    public ASMBlock AALOAD() {
        return visitInsn(AALOAD);
    }

    @Override
    public ASMBlock BALOAD() {
        return visitInsn(BALOAD);
    }

    @Override
    public ASMBlock CALOAD() {
        return visitInsn(CALOAD);
    }

    @Override
    public ASMBlock SALOAD() {
        return visitInsn(SALOAD);
    }

    @Override
    public ASMBlock ISTORE(int idx) {
        return visitVarInsn(ISTORE, idx);
    }

    @Override
    public ASMBlock LSTORE(int idx) {
        return visitVarInsn(LSTORE, idx);
    }

    @Override
    public ASMBlock FSTORE(int idx) {
        return visitVarInsn(FSTORE, idx);
    }

    @Override
    public ASMBlock DSTORE(int idx) {
        return visitVarInsn(DSTORE, idx);
    }

    @Override
    public ASMBlock ASTORE(int idx) {
        return visitVarInsn(ASTORE, idx);
    }

    @Override
    public ASMBlock IASTORE() {
        return visitInsn(IASTORE);
    }

    @Override
    public ASMBlock LASTORE() {
        return visitInsn(LASTORE);
    }

    @Override
    public ASMBlock FASTORE() {
        return visitInsn(FASTORE);
    }

    @Override
    public ASMBlock DASTORE() {
        return visitInsn(DASTORE);
    }

    @Override
    public ASMBlock AASTORE() {
        return visitInsn(AASTORE);
    }

    @Override
    public ASMBlock BASTORE() {
        return visitInsn(BASTORE);
    }

    @Override
    public ASMBlock CASTORE() {
        return visitInsn(CASTORE);
    }

    @Override
    public ASMBlock SASTORE() {
        return visitInsn(SASTORE);
    }

    @Override
    public ASMBlock POP() {
        return visitInsn(POP);
    }

    @Override
    public ASMBlock POP2() {
        return visitInsn(POP2);
    }

    @Override
    public ASMBlock DUP() {
        return visitInsn(DUP);
    }

    @Override
    public ASMBlock DUPX1() {
        return visitInsn(DUP_X1);
    }

    @Override
    public ASMBlock DUPX2() {
        return visitInsn(DUP_X2);
    }

    @Override
    public ASMBlock DUP2() {
        return visitInsn(DUP2);
    }

    @Override
    public ASMBlock DUP2X1() {
        return visitInsn(DUP2_X1);
    }

    @Override
    public ASMBlock DUP2X2() {
        return visitInsn(DUP2_X2);
    }

    @Override
    public ASMBlock SWAP() {
        return visitInsn(SWAP);
    }

    @Override
    public ASMBlock IADD() {
        return visitInsn(IADD);
    }

    @Override
    public ASMBlock LADD() {
        return visitInsn(LADD);
    }

    @Override
    public ASMBlock FADD() {
        return visitInsn(FADD);
    }

    @Override
    public ASMBlock DADD() {
        return visitInsn(DADD);
    }

    @Override
    public ASMBlock ISUB() {
        return visitInsn(ISUB);
    }

    @Override
    public ASMBlock LSUB() {
        return visitInsn(LSUB);
    }

    @Override
    public ASMBlock FSUB() {
        return visitInsn(FSUB);
    }

    @Override
    public ASMBlock DSUB() {
        return visitInsn(DSUB);
    }

    @Override
    public ASMBlock IMUL() {
        return visitInsn(IMUL);
    }

    @Override
    public ASMBlock LMUL() {
        return visitInsn(LMUL);
    }

    @Override
    public ASMBlock FMUL() {
        return visitInsn(FMUL);
    }

    @Override
    public ASMBlock DMUL() {
        return visitInsn(DMUL);
    }

    @Override
    public ASMBlock IDIV() {
        return visitInsn(IDIV);
    }

    @Override
    public ASMBlock LDIV() {
        return visitInsn(LDIV);
    }

    @Override
    public ASMBlock FDIV() {
        return visitInsn(FDIV);
    }

    @Override
    public ASMBlock DDIV() {
        return visitInsn(DDIV);
    }

    @Override
    public ASMBlock IREM() {
        return visitInsn(IREM);
    }

    @Override
    public ASMBlock LREM() {
        return visitInsn(LREM);
    }

    @Override
    public ASMBlock FREM() {
        return visitInsn(FREM);
    }

    @Override
    public ASMBlock DREM() {
        return visitInsn(DREM);
    }

    @Override
    public ASMBlock INEG() {
        return visitInsn(INEG);
    }

    @Override
    public ASMBlock LNEG() {
        return visitInsn(LNEG);
    }

    @Override
    public ASMBlock FNEG() {
        return visitInsn(FNEG);
    }

    @Override
    public ASMBlock DNEG() {
        return visitInsn(DNEG);
    }

    @Override
    public ASMBlock ISHL() {
        return visitInsn(ISHL);
    }

    @Override
    public ASMBlock LSHL() {
        return visitInsn(LSHL);
    }

    @Override
    public ASMBlock ISHR() {
        return visitInsn(ISHR);
    }

    @Override
    public ASMBlock LSHR() {
        return visitInsn(LSHR);
    }

    @Override
    public ASMBlock IUSHR() {
        return visitInsn(IUSHR);
    }

    @Override
    public ASMBlock LUSHR() {
        return visitInsn(LUSHR);
    }

    @Override
    public ASMBlock IAND() {
        return visitInsn(IAND);
    }

    @Override
    public ASMBlock LAND() {
        return visitInsn(LAND);
    }

    @Override
    public ASMBlock IOR() {
        return visitInsn(IOR);
    }

    @Override
    public ASMBlock LOR() {
        return visitInsn(LOR);
    }

    @Override
    public ASMBlock IXOR() {
        return visitInsn(IXOR);
    }

    @Override
    public ASMBlock LXOR() {
        return visitInsn(LXOR);
    }

    @Override
    public ASMBlock IINC(int idx, int value) {
        visitor.visitIincInsn(idx, value);
        return this;
    }

    @Override
    public ASMBlock I2L() {
        return visitInsn(I2L);
    }

    @Override
    public ASMBlock I2F() {
        return visitInsn(I2F);
    }

    @Override
    public ASMBlock I2D() {
        return visitInsn(I2D);
    }

    @Override
    public ASMBlock L2I() {
        return visitInsn(L2I);
    }

    @Override
    public ASMBlock L2F() {
        return visitInsn(L2F);
    }

    @Override
    public ASMBlock L2D() {
        return visitInsn(L2D);
    }

    @Override
    public ASMBlock F2I() {
        return visitInsn(F2I);
    }

    @Override
    public ASMBlock F2L() {
        return visitInsn(F2L);
    }

    @Override
    public ASMBlock F2D() {
        return visitInsn(F2D);
    }

    @Override
    public ASMBlock D2I() {
        return visitInsn(D2I);
    }

    @Override
    public ASMBlock D2L() {
        return visitInsn(D2L);
    }

    @Override
    public ASMBlock D2F() {
        return visitInsn(D2F);
    }

    @Override
    public ASMBlock I2B() {
        return visitInsn(I2B);
    }

    @Override
    public ASMBlock I2C() {
        return visitInsn(I2C);
    }

    @Override
    public ASMBlock I2S() {
        return visitInsn(I2S);
    }

    @Override
    public ASMBlock LCMP() {
        return visitInsn(LCMP);
    }

    @Override
    public ASMBlock FCMPL() {
        return visitInsn(FCMPL);
    }

    @Override
    public ASMBlock FCMPG() {
        return visitInsn(FCMPG);
    }

    @Override
    public ASMBlock DCMPL() {
        return visitInsn(DCMPL);
    }

    @Override
    public ASMBlock DCMPG() {
        return visitInsn(DCMPG);
    }

    @Override
    public ASMBlock IFEQ(Label label) {
        return visitJumpInsn(IFEQ, label);
    }

    @Override
    public ASMBlock IFNE(Label label) {
        return visitJumpInsn(IFNE, label);
    }

    @Override
    public ASMBlock IFLT(Label label) {
        return visitJumpInsn(IFLT, label);
    }

    @Override
    public ASMBlock IFGE(Label label) {
        return visitJumpInsn(IFGE, label);
    }

    @Override
    public ASMBlock IFGT(Label label) {
        return visitJumpInsn(IFGT, label);
    }

    @Override
    public ASMBlock IFLE(Label label) {
        return visitJumpInsn(IFLE, label);
    }

    @Override
    public ASMBlock ICMPEQ(Label label) {
        return visitJumpInsn(IF_ICMPEQ, label);
    }

    @Override
    public ASMBlock ICMPNE(Label label) {
        return visitJumpInsn(IF_ICMPNE, label);
    }

    @Override
    public ASMBlock ICMPLT(Label label) {
        return visitJumpInsn(IF_ICMPLT, label);
    }

    @Override
    public ASMBlock ICMPGE(Label label) {
        return visitJumpInsn(IF_ICMPGE, label);
    }

    @Override
    public ASMBlock ICMPGT(Label label) {
        return visitJumpInsn(IF_ICMPGT, label);
    }

    @Override
    public ASMBlock ICMPLE(Label label) {
        return visitJumpInsn(IF_ICMPLE, label);
    }

    @Override
    public ASMBlock ACMPEQ(Label label) {
        return visitJumpInsn(IF_ACMPEQ, label);
    }

    @Override
    public ASMBlock ACMPNE(Label label) {
        return visitJumpInsn(IF_ACMPNE, label);
    }

    @Override
    public ASMBlock GOTO(Label label) {
        return visitJumpInsn(GOTO, label);
    }

    @Override
    public ASMBlock GOTO_W() {
        throw new RuntimeException("ASM NOT SUPPORT GOTO_W");
    }

    @Override
    public ASMBlock TABLESWITCH(int min, int max, Label dflt, Label... labels) {
        visitor.visitTableSwitchInsn(min, max, dflt, labels);
        return this;
    }

    @Override
    public ASMBlock LOOKUPSWITCH(int[] keys, Label dflt, Label... labels) {
        visitor.visitLookupSwitchInsn(dflt, keys, labels);
        return this;
    }

    @Override
    public ASMBlock IRETURN() {
        return visitInsn(IRETURN);
    }

    @Override
    public ASMBlock LRETURN() {
        return visitInsn(LRETURN);
    }

    @Override
    public ASMBlock FRETURN() {
        return visitInsn(FRETURN);
    }

    @Override
    public ASMBlock DRETURN() {
        return visitInsn(DRETURN);
    }

    @Override
    public ASMBlock ARETURN() {
        return visitInsn(ARETURN);
    }

    @Override
    public ASMBlock RETURN() {
        return visitInsn(RETURN);
    }

    @Override
    public ASMBlock RET(int index) {
        return visitVarInsn(RET, index);
    }

    @Override
    public ASMBlock JSR() {
        return visitInsn(JSR);
    }


    @Override
    public ASMBlock GETSTATIC(String owner, String name, String desc) {
        visitor.visitFieldInsn(GETSTATIC, owner, name, desc);
        return this;
    }

    @Override
    public ASMBlock PUTSTATIC(String owner, String name, String desc) {
        visitor.visitFieldInsn(PUTSTATIC, owner, name, desc);
        return this;
    }

    @Override
    public ASMBlock GETFIELD(String owner, String name, String desc) {
        visitor.visitFieldInsn(GETFIELD, owner, name, desc);
        return this;
    }

    @Override
    public ASMBlock PUTFIELD(String owner, String name, String desc) {
        visitor.visitFieldInsn(PUTFIELD, owner, name, desc);
        return this;
    }

    @Override
    public ASMBlock GETSTATIC(Class<?> owner, String name, String desc) {
        return GETSTATIC(internalName(owner), name, desc);
    }

    @Override
    public ASMBlock PUTSTATIC(Class<?> owner, String name, String desc) {
        return PUTSTATIC(internalName(owner), name, desc);
    }

    @Override
    public ASMBlock GETFIELD(Class<?> owner, String name, String desc) {
        return GETFIELD(internalName(owner), name, desc);
    }

    @Override
    public ASMBlock PUTFIELD(Class<?> owner, String name, String desc) {
        return PUTFIELD(internalName(owner), name, desc);
    }

    @Override
    public ASMBlock GETSTATIC(String owner, String name, Class<?> desc) {
        return GETSTATIC(owner, name, fieldDescriptor(desc));
    }

    @Override
    public ASMBlock PUTSTATIC(String owner, String name, Class<?> desc) {
        return PUTSTATIC(owner, name, fieldDescriptor(desc));
    }

    @Override
    public ASMBlock GETFIELD(String owner, String name, Class<?> desc) {
        return GETFIELD(owner, name, fieldDescriptor(desc));
    }

    @Override
    public ASMBlock PUTFIELD(String owner, String name, Class<?> desc) {
        return PUTFIELD(owner, name, fieldDescriptor(desc));
    }

    @Override
    public ASMBlock GETSTATIC(Class<?> owner, String name, Class<?> desc) {
        return GETSTATIC(internalName(owner), name, fieldDescriptor(desc));
    }

    @Override
    public ASMBlock PUTSTATIC(Class<?> owner, String name, Class<?> desc) {
        return PUTSTATIC(internalName(owner), name, fieldDescriptor(desc));
    }

    @Override
    public ASMBlock GETFIELD(Class<?> owner, String name, Class<?> desc) {
        return GETFIELD(internalName(owner), name, fieldDescriptor(desc));
    }

    @Override
    public ASMBlock PUTFIELD(Class<?> owner, String name, Class<?> desc) {
        return PUTFIELD(internalName(owner), name, fieldDescriptor(desc));
    }

    @Override
    public ASMBlock INVOKEVIRTUAL(String owner, String name, String desc) {
        visitor.visitMethodInsn(INVOKEVIRTUAL, owner, name, desc, false);
        return this;
    }

    @Override
    public ASMBlock INVOKESPECIAL(String owner, String name, String desc) {
        visitor.visitMethodInsn(INVOKESPECIAL, owner, name, desc, false);
        return this;
    }

    @Override
    public ASMBlock INVOKESTATIC(String owner, String name, String desc) {
        visitor.visitMethodInsn(INVOKESTATIC, owner, name, desc, false);
        return this;
    }

    @Override
    public ASMBlock INVOKEINTERFACE(String owner, String name, String desc) {
        visitor.visitMethodInsn(INVOKEINTERFACE, owner, name, desc, true);
        return this;
    }

    @Override
    public ASMBlock INVOKEVIRTUAL(String owner, String name, MethodType type) {
        return INVOKEVIRTUAL(owner, name, type.toMethodDescriptorString());
    }

    @Override
    public ASMBlock INVOKESTATIC(String owner, String name, MethodType type) {
        return INVOKESTATIC(owner, name, type.toMethodDescriptorString());
    }

    @Override
    public ASMBlock INVOKEINTERFACE(String owner, String name, MethodType type) {
        return INVOKEINTERFACE(owner, name, type.toMethodDescriptorString());
    }

    @Override
    public ASMBlock INVOKEVIRTUAL(Class<?> owner, String name, String desc) {
        return INVOKEVIRTUAL(internalName(owner), name, desc);
    }

    @Override
    public ASMBlock INVOKESPECIAL(Class<?> owner, String name, String desc) {
        return INVOKESPECIAL(internalName(owner), name, desc);
    }

    @Override
    public ASMBlock INVOKESTATIC(Class<?> owner, String name, String desc) {
        return INVOKESTATIC(internalName(owner), name, desc);
    }

    @Override
    public ASMBlock INVOKEVIRTUAL(Class<?> owner, String name, MethodType type) {
        return INVOKEVIRTUAL(internalName(owner), name, type.toMethodDescriptorString());
    }

    @Override
    public ASMBlock INVOKESPECIAL(Class<?> owner, String name, MethodType type) {
        return INVOKESPECIAL(internalName(owner), name, type.toMethodDescriptorString());
    }

    @Override
    public ASMBlock INVOKESTATIC(Class<?> owner, String name, MethodType type) {
        return INVOKESTATIC(internalName(owner), name, type.toMethodDescriptorString());
    }

    @Override
    public ASMBlock INVOKEINTERFACE(Class<?> owner, String name, String desc) {
        return INVOKEINTERFACE(internalName(owner), name, desc);
    }

    @Override
    public ASMBlock INVOKEINTERFACE(Class<?> owner, String name, MethodType type) {
        return INVOKEINTERFACE(internalName(owner), name, type.toMethodDescriptorString());
    }

    @Override
    public ASMBlock INVOKEDYNAMIC(String name, String desc, Handle bootstrap, Object... args) {
        visitor.visitInvokeDynamicInsn(name, desc, bootstrap, args);
        return this;
    }

    @Override
    public ASMBlock NEW(String type) {
        visitor.visitTypeInsn(NEW, type);
        return this;
    }

    @Override
    public ASMBlock NEW(Class<?> type) {
        visitor.visitTypeInsn(NEW, Type.getType(type).getInternalName());
        return this;
    }

    @Override
    public ASMBlock NEW(Type type) {
        visitor.visitTypeInsn(NEW, type.getInternalName());
        return this;
    }

    @Override
    public ASMBlock NEWARRAY(int type) {
        visitor.visitIntInsn(NEWARRAY, type);
        return this;
    }

    @Override
    public ASMBlock NEWARRAY(String type) {
        visitor.visitTypeInsn(ANEWARRAY, type);
        return this;
    }

    @Override
    public ASMBlock ANEWARRAY(Class<?> type) {
        visitor.visitTypeInsn(ANEWARRAY, Type.getInternalName(type));
        return this;
    }

    @Override
    public ASMBlock ARRAYLENGTH() {
        return visitInsn(ARRAYLENGTH);
    }

    @Override
    public ASMBlock ATHROW() {
        return visitInsn(ATHROW);
    }

    @Override
    public ASMBlock CHECKCAST(String type) {
        visitor.visitTypeInsn(CHECKCAST, type);
        return this;
    }

    @Override
    public ASMBlock INSTANCEOF(String type) {
        visitor.visitTypeInsn(INSTANCEOF, type);
        return this;
    }

    @Override
    public ASMBlock MONITORENTER() {
        return visitInsn(MONITORENTER);
    }

    @Override
    public ASMBlock MONITOREXIT() {
        return visitInsn(MONITOREXIT);
    }

    @Override
    public ASMBlock MULTINEWARRAY(String desc, int dimensions) {
        visitor.visitMultiANewArrayInsn(desc, dimensions);
        return this;
    }

    @Override
    public ASMBlock IFNULL(Label label) {
        return visitJumpInsn(IFNULL, label);
    }

    @Override
    public ASMBlock IFNONNULL(Label label) {
        return visitJumpInsn(IFNONNULL, label);
    }

    @Override
    public ASMBlock LABEL(Label label) {
        visitor.visitLabel(label);
        return this;
    }

    @Override
    public ASMBlock TRY(Label start, Label end, Label handler, String type) {
        visitor.visitTryCatchBlock(start, end, handler, type);
        return this;
    }

    @Override
    public ASMBlock LINE(int line, Label start) {
        visitor.visitLineNumber(line, start);
        return this;
    }

    @Override
    public ASMBlock PARAMETER(String name, int access) {
        visitor.visitParameter(name, access);
        return this;
    }

    @Override
    public ASMBlock ARRAY(long[] array, int off, int len) {
        NEWARRAY(T_LONG);
        while (off < len) {
            long v = array[off];
            DUP().INT(off++).LONG(v).LASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(double[] array, int off, int len) {
        INT(len - off);
        NEWARRAY(T_DOUBLE);
        while (off < len) {
            double v = array[off];
            DUP().INT(off++).DOUBLE(v).DASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(int[] array, int off, int len) {
        INT(len - off);
        NEWARRAY(T_INT);
        while (off < len) {
            int v = array[off];
            DUP().INT(off++).INT(v).IASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(float[] array, int off, int len) {
        INT(len - off);
        NEWARRAY(T_FLOAT);
        while (off < len) {
            float v = array[off];
            DUP().INT(off++).FLOAT(v).FASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(char[] array, int off, int len) {
        INT(len - off);
        NEWARRAY(T_CHAR);
        while (off < len) {
            char v = array[off];
            DUP().INT(off++).INT(v).CASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(short[] array, int off, int len) {
        INT(len - off);
        NEWARRAY(T_SHORT);
        while (off < len) {
            short v = array[off];
            DUP().INT(off++).INT(v).SASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(byte[] array, int off, int len) {
        INT(len - off);
        NEWARRAY(T_BYTE);
        while (off < len) {
            byte v = array[off];
            DUP().INT(off++).INT(v).BASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(boolean[] array, int off, int len) {
        INT(len - off);
        NEWARRAY(T_BOOLEAN);
        while (off < len) {
            boolean v = array[off];
            DUP().INT(off++).INT(v ? 1 : 0).BASTORE();
        }
        return this;
    }

    @Override
    public <T> ASMBlock ARRAY(T[] array, int off, int len, BiIntConsumer<T> producer) {
        INT(len - off);
        visitor.visitTypeInsn(ANEWARRAY, Type.getInternalName(array.getClass().getComponentType()));
        while (off < len) {
            T v = array[off];
            int idx = off++;
            DUP().INT(idx);
            producer.accept(idx, v);
            AASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(long[] array) {
        INT(array.length);
        NEWARRAY(T_LONG);
        for (int i = 0, j = array.length; i < j; i++) {
            long v = array[i];
            DUP().INT(i).LONG(v).LASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(double[] array) {
        INT(array.length);
        NEWARRAY(T_DOUBLE);
        for (int i = 0, j = array.length; i < j; i++) {
            double v = array[i];
            DUP().INT(i).DOUBLE(v).DASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(int[] array) {
        INT(array.length);
        NEWARRAY(T_INT);
        for (int i = 0, j = array.length; i < j; i++) {
            int v = array[i];
            DUP().INT(i).INT(v).IASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(float[] array) {
        INT(array.length);
        NEWARRAY(T_FLOAT);
        for (int i = 0, j = array.length; i < j; i++) {
            float v = array[i];
            DUP().INT(i).FLOAT(v).FASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(char[] array) {
        INT(array.length);
        NEWARRAY(T_CHAR);
        for (int i = 0, j = array.length; i < j; i++) {
            char v = array[i];
            DUP().INT(i).INT(v).CASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(short[] array) {
        INT(array.length);
        NEWARRAY(T_SHORT);
        for (int i = 0, j = array.length; i < j; i++) {
            short v = array[i];
            DUP().INT(i).INT(v).SASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(byte[] array) {
        INT(array.length);
        NEWARRAY(T_BYTE);
        for (int i = 0, j = array.length; i < j; i++) {
            byte v = array[i];
            DUP().INT(i).INT(v).BASTORE();
        }
        return this;
    }

    @Override
    public ASMBlock ARRAY(boolean[] array) {
        INT(array.length);
        NEWARRAY(T_BOOLEAN);
        for (int i = 0, j = array.length; i < j; i++) {
            boolean v = array[i];
            DUP().INT(i).INT(v ? 1 : 0).BASTORE();
        }
        return this;
    }

    @Override
    public <T> ASMBlock ARRAY(T[] array, BiIntConsumer<T> producer) {
        INT(array.length);
        visitor.visitTypeInsn(ANEWARRAY, Type.getInternalName(array.getClass().getComponentType()));
        for (int i = 0, j = array.length; i < j; i++) {
            T v = array[i];
            DUP().INT(i);
            producer.accept(i, v);
            AASTORE();
        }
        return this;
    }

    public ASMBlock WIDE() {
        throw new RuntimeException("ASM NOT SUPPORT LDC_W");
    }

    private ASMBlock visitInsn(int opcode) {
        visitor.visitInsn(opcode);
        return this;
    }

    private ASMBlock visitJumpInsn(int opcode, Label label) {
        visitor.visitJumpInsn(opcode, label);
        return this;
    }

    private ASMBlock visitVarInsn(int opcode, int var) {
        visitor.visitVarInsn(opcode, var);
        return this;
    }

    private ASMBlock visitLdcInsn(Object value) {
        visitor.visitLdcInsn(value);
        return this;
    }

    private static String internalName(Class<?> c) {
        return c.getName().replace('.', '/');
    }

    private static String fieldDescriptor(Class<?> c) {
        if (c.isPrimitive()) {
            return getPrimitiveInternal(c);
        }
        String name = c.getName().replace('.', '/');
        if (c.isArray()) {
            return name;
        }
        return 'L' + name + ';';
    }

    private static String getPrimitiveInternal(Class<?> c) {
        if (c == Long.TYPE) {
            return "J";
        } else if (c == Double.TYPE) {
            return "D";
        } else if (c == Integer.TYPE) {
            return "I";
        } else if (c == Float.TYPE) {
            return "F";
        } else if (c == Short.TYPE) {
            return "S";
        } else if (c == Character.TYPE) {
            return "C";
        } else if (c == Byte.TYPE) {
            return "B";
        } else if (c == Boolean.TYPE) {
            return "Z";
        } else {
            throw new IllegalArgumentException("Unknown primitive: " + c);
        }
    }
}
