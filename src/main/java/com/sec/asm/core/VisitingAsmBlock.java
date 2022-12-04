package com.sec.asm.core;

import com.sec.asm.api.AsmBlock;
import com.sec.asm.api.BiIntConsumer;
import org.objectweb.asm.*;

import java.lang.invoke.MethodType;

import static org.objectweb.asm.Opcodes.*;

@SuppressWarnings("all")
public class VisitingAsmBlock implements AsmBlock {

    private final MethodVisitor visitor;

    public VisitingAsmBlock(MethodVisitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public AsmBlock INIT() {
        return this;
    }

    @Override
    public AsmBlock NOP() {
        return visitInsn(NOP);
    }

    @Override
    public AsmBlock NULL() {
        return visitInsn(ACONST_NULL);
    }

    @Override
    public AsmBlock INT(int value) {
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
    public AsmBlock LONG(long value) {
        MethodVisitor visitor = this.visitor;
        if (value == 0L || value == 1L) {
            visitor.visitInsn((int) (value + 9));
        } else {
            visitor.visitLdcInsn(value);
        }
        return this;
    }

    @Override
    public AsmBlock FLOAT(float value) {
        MethodVisitor visitor = this.visitor;
        if (value == 0.0F || value == 1.0F || value == 2.0F) {
            visitor.visitInsn((int) value + 11);
        } else {
            visitor.visitLdcInsn(value);
        }
        return this;
    }

    @Override
    public AsmBlock DOUBLE(double value) {
        MethodVisitor visitor = this.visitor;
        if (value == 0.0 || value == 1.0) {
            visitor.visitInsn((int) value + 14);
        } else {
            visitor.visitLdcInsn(value);
        }
        return this;
    }

    @Override
    public AsmBlock LDC(String value) {
        return visitLdcInsn(value);
    }

    @Override
    public AsmBlock LDC(Class<?> type) {
        return visitLdcInsn(Type.getType(type));
    }

    @Override
    public AsmBlock LDC(Type type) {
        return visitLdcInsn(type);
    }

    @Override
    public AsmBlock LDC(MethodType type) {
        Class<?>[] $parameters = type.parameterArray();
        int j = $parameters.length;
        Type[] parameters = new Type[j];
        while (j-- > 0) {
            parameters[j] = Type.getType($parameters[j]);
        }
        return visitLdcInsn(Type.getMethodType(Type.getType(type.returnType()), parameters));
    }

    @Override
    public AsmBlock LDC(Handle handle) {
        return visitLdcInsn(handle);
    }

    @Override
    public AsmBlock LDC(ConstantDynamic constantDynamic) {
        return visitLdcInsn(constantDynamic);
    }

    @Override
    public AsmBlock ILOAD(int idx) {
        return visitVarInsn(ILOAD, idx);
    }

    @Override
    public AsmBlock LLOAD(int idx) {
        return visitVarInsn(LLOAD, idx);
    }

    @Override
    public AsmBlock FLOAD(int idx) {
        return visitVarInsn(FLOAD, idx);
    }

    @Override
    public AsmBlock DLOAD(int idx) {
        return visitVarInsn(DLOAD, idx);
    }

    @Override
    public AsmBlock ALOAD(int idx) {
        return visitVarInsn(ALOAD, idx);
    }

    @Override
    public AsmBlock IALOAD() {
        return visitInsn(IALOAD);
    }

    @Override
    public AsmBlock LALOAD() {
        return visitInsn(LALOAD);
    }

    @Override
    public AsmBlock FALOAD() {
        return visitInsn(FALOAD);
    }

    @Override
    public AsmBlock DALOAD() {
        return visitInsn(DALOAD);
    }

    @Override
    public AsmBlock AALOAD() {
        return visitInsn(AALOAD);
    }

    @Override
    public AsmBlock BALOAD() {
        return visitInsn(BALOAD);
    }

    @Override
    public AsmBlock CALOAD() {
        return visitInsn(CALOAD);
    }

    @Override
    public AsmBlock SALOAD() {
        return visitInsn(SALOAD);
    }

    @Override
    public AsmBlock ISTORE(int idx) {
        return visitVarInsn(ISTORE, idx);
    }

    @Override
    public AsmBlock LSTORE(int idx) {
        return visitVarInsn(LSTORE, idx);
    }

    @Override
    public AsmBlock FSTORE(int idx) {
        return visitVarInsn(FSTORE, idx);
    }

    @Override
    public AsmBlock DSTORE(int idx) {
        return visitVarInsn(DSTORE, idx);
    }

    @Override
    public AsmBlock ASTORE(int idx) {
        return visitVarInsn(ASTORE, idx);
    }

    @Override
    public AsmBlock IASTORE() {
        return visitInsn(IASTORE);
    }

    @Override
    public AsmBlock LASTORE() {
        return visitInsn(LASTORE);
    }

    @Override
    public AsmBlock FASTORE() {
        return visitInsn(FASTORE);
    }

    @Override
    public AsmBlock DASTORE() {
        return visitInsn(DASTORE);
    }

    @Override
    public AsmBlock AASTORE() {
        return visitInsn(AASTORE);
    }

    @Override
    public AsmBlock BASTORE() {
        return visitInsn(BASTORE);
    }

    @Override
    public AsmBlock CASTORE() {
        return visitInsn(CASTORE);
    }

    @Override
    public AsmBlock SASTORE() {
        return visitInsn(SASTORE);
    }

    @Override
    public AsmBlock POP() {
        return visitInsn(POP);
    }

    @Override
    public AsmBlock POP2() {
        return visitInsn(POP2);
    }

    @Override
    public AsmBlock DUP() {
        return visitInsn(DUP);
    }

    @Override
    public AsmBlock DUPX1() {
        return visitInsn(DUP_X1);
    }

    @Override
    public AsmBlock DUPX2() {
        return visitInsn(DUP_X2);
    }

    @Override
    public AsmBlock DUP2() {
        return visitInsn(DUP2);
    }

    @Override
    public AsmBlock DUP2X1() {
        return visitInsn(DUP2_X1);
    }

    @Override
    public AsmBlock DUP2X2() {
        return visitInsn(DUP2_X2);
    }

    @Override
    public AsmBlock SWAP() {
        return visitInsn(SWAP);
    }

    @Override
    public AsmBlock IADD() {
        return visitInsn(IADD);
    }

    @Override
    public AsmBlock LADD() {
        return visitInsn(LADD);
    }

    @Override
    public AsmBlock FADD() {
        return visitInsn(FADD);
    }

    @Override
    public AsmBlock DADD() {
        return visitInsn(DADD);
    }

    @Override
    public AsmBlock ISUB() {
        return visitInsn(ISUB);
    }

    @Override
    public AsmBlock LSUB() {
        return visitInsn(LSUB);
    }

    @Override
    public AsmBlock FSUB() {
        return visitInsn(FSUB);
    }

    @Override
    public AsmBlock DSUB() {
        return visitInsn(DSUB);
    }

    @Override
    public AsmBlock IMUL() {
        return visitInsn(IMUL);
    }

    @Override
    public AsmBlock LMUL() {
        return visitInsn(LMUL);
    }

    @Override
    public AsmBlock FMUL() {
        return visitInsn(FMUL);
    }

    @Override
    public AsmBlock DMUL() {
        return visitInsn(DMUL);
    }

    @Override
    public AsmBlock IDIV() {
        return visitInsn(IDIV);
    }

    @Override
    public AsmBlock LDIV() {
        return visitInsn(LDIV);
    }

    @Override
    public AsmBlock FDIV() {
        return visitInsn(FDIV);
    }

    @Override
    public AsmBlock DDIV() {
        return visitInsn(DDIV);
    }

    @Override
    public AsmBlock IREM() {
        return visitInsn(IREM);
    }

    @Override
    public AsmBlock LREM() {
        return visitInsn(LREM);
    }

    @Override
    public AsmBlock FREM() {
        return visitInsn(FREM);
    }

    @Override
    public AsmBlock DREM() {
        return visitInsn(DREM);
    }

    @Override
    public AsmBlock INEG() {
        return visitInsn(INEG);
    }

    @Override
    public AsmBlock LNEG() {
        return visitInsn(LNEG);
    }

    @Override
    public AsmBlock FNEG() {
        return visitInsn(FNEG);
    }

    @Override
    public AsmBlock DNEG() {
        return visitInsn(DNEG);
    }

    @Override
    public AsmBlock ISHL() {
        return visitInsn(ISHL);
    }

    @Override
    public AsmBlock LSHL() {
        return visitInsn(LSHL);
    }

    @Override
    public AsmBlock ISHR() {
        return visitInsn(ISHR);
    }

    @Override
    public AsmBlock LSHR() {
        return visitInsn(LSHR);
    }

    @Override
    public AsmBlock IUSHR() {
        return visitInsn(IUSHR);
    }

    @Override
    public AsmBlock LUSHR() {
        return visitInsn(LUSHR);
    }

    @Override
    public AsmBlock IAND() {
        return visitInsn(IAND);
    }

    @Override
    public AsmBlock LAND() {
        return visitInsn(LAND);
    }

    @Override
    public AsmBlock IOR() {
        return visitInsn(IOR);
    }

    @Override
    public AsmBlock LOR() {
        return visitInsn(LOR);
    }

    @Override
    public AsmBlock IXOR() {
        return visitInsn(IXOR);
    }

    @Override
    public AsmBlock LXOR() {
        return visitInsn(LXOR);
    }

    @Override
    public AsmBlock IINC(int idx, int value) {
        visitor.visitIincInsn(idx, value);
        return this;
    }

    @Override
    public AsmBlock I2L() {
        return visitInsn(I2L);
    }

    @Override
    public AsmBlock I2F() {
        return visitInsn(I2F);
    }

    @Override
    public AsmBlock I2D() {
        return visitInsn(I2D);
    }

    @Override
    public AsmBlock L2I() {
        return visitInsn(L2I);
    }

    @Override
    public AsmBlock L2F() {
        return visitInsn(L2F);
    }

    @Override
    public AsmBlock L2D() {
        return visitInsn(L2D);
    }

    @Override
    public AsmBlock F2I() {
        return visitInsn(F2I);
    }

    @Override
    public AsmBlock F2L() {
        return visitInsn(F2L);
    }

    @Override
    public AsmBlock F2D() {
        return visitInsn(F2D);
    }

    @Override
    public AsmBlock D2I() {
        return visitInsn(D2I);
    }

    @Override
    public AsmBlock D2L() {
        return visitInsn(D2L);
    }

    @Override
    public AsmBlock D2F() {
        return visitInsn(D2F);
    }

    @Override
    public AsmBlock I2B() {
        return visitInsn(I2B);
    }

    @Override
    public AsmBlock I2C() {
        return visitInsn(I2C);
    }

    @Override
    public AsmBlock I2S() {
        return visitInsn(I2S);
    }

    @Override
    public AsmBlock LCMP() {
        return visitInsn(LCMP);
    }

    @Override
    public AsmBlock FCMPL() {
        return visitInsn(FCMPL);
    }

    @Override
    public AsmBlock FCMPG() {
        return visitInsn(FCMPG);
    }

    @Override
    public AsmBlock DCMPL() {
        return visitInsn(DCMPL);
    }

    @Override
    public AsmBlock DCMPG() {
        return visitInsn(DCMPG);
    }

    @Override
    public AsmBlock IFEQ(Label label) {
        return visitJumpInsn(IFEQ, label);
    }

    @Override
    public AsmBlock IFNE(Label label) {
        return visitJumpInsn(IFNE, label);
    }

    @Override
    public AsmBlock IFLT(Label label) {
        return visitJumpInsn(IFLT, label);
    }

    @Override
    public AsmBlock IFGE(Label label) {
        return visitJumpInsn(IFGE, label);
    }

    @Override
    public AsmBlock IFGT(Label label) {
        return visitJumpInsn(IFGT, label);
    }

    @Override
    public AsmBlock IFLE(Label label) {
        return visitJumpInsn(IFLE, label);
    }

    @Override
    public AsmBlock ICMPEQ(Label label) {
        return visitJumpInsn(IF_ICMPEQ, label);
    }

    @Override
    public AsmBlock ICMPNE(Label label) {
        return visitJumpInsn(IF_ICMPNE, label);
    }

    @Override
    public AsmBlock ICMPLT(Label label) {
        return visitJumpInsn(IF_ICMPLT, label);
    }

    @Override
    public AsmBlock ICMPGE(Label label) {
        return visitJumpInsn(IF_ICMPGE, label);
    }

    @Override
    public AsmBlock ICMPGT(Label label) {
        return visitJumpInsn(IF_ICMPGT, label);
    }

    @Override
    public AsmBlock ICMPLE(Label label) {
        return visitJumpInsn(IF_ICMPLE, label);
    }

    @Override
    public AsmBlock ACMPEQ(Label label) {
        return visitJumpInsn(IF_ACMPEQ, label);
    }

    @Override
    public AsmBlock ACMPNE(Label label) {
        return visitJumpInsn(IF_ACMPNE, label);
    }

    @Override
    public AsmBlock GOTO(Label label) {
        return visitJumpInsn(GOTO, label);
    }

    @Override
    public AsmBlock TABLESWITCH(int min, int max, Label dflt, Label... labels) {
        visitor.visitTableSwitchInsn(min, max, dflt, labels);
        return this;
    }

    @Override
    public AsmBlock LOOKUPSWITCH(int[] keys, Label dflt, Label... labels) {
        visitor.visitLookupSwitchInsn(dflt, keys, labels);
        return this;
    }

    @Override
    public AsmBlock IRETURN() {
        return visitInsn(IRETURN);
    }

    @Override
    public AsmBlock LRETURN() {
        return visitInsn(LRETURN);
    }

    @Override
    public AsmBlock FRETURN() {
        return visitInsn(FRETURN);
    }

    @Override
    public AsmBlock DRETURN() {
        return visitInsn(DRETURN);
    }

    @Override
    public AsmBlock ARETURN() {
        return visitInsn(ARETURN);
    }

    @Override
    public AsmBlock RETURN() {
        return visitInsn(RETURN);
    }

    @Override
    public AsmBlock GETSTATIC(String owner, String name, String desc) {
        visitor.visitFieldInsn(GETSTATIC, owner, name, desc);
        return this;
    }

    @Override
    public AsmBlock PUTSTATIC(String owner, String name, String desc) {
        visitor.visitFieldInsn(PUTSTATIC, owner, name, desc);
        return this;
    }

    @Override
    public AsmBlock GETFIELD(String owner, String name, String desc) {
        visitor.visitFieldInsn(GETFIELD, owner, name, desc);
        return this;
    }

    @Override
    public AsmBlock PUTFIELD(String owner, String name, String desc) {
        visitor.visitFieldInsn(PUTFIELD, owner, name, desc);
        return this;
    }

    @Override
    public AsmBlock GETSTATIC(Class<?> owner, String name, String desc) {
        return GETSTATIC(internalName(owner), name, desc);
    }

    @Override
    public AsmBlock PUTSTATIC(Class<?> owner, String name, String desc) {
        return PUTSTATIC(internalName(owner), name, desc);
    }

    @Override
    public AsmBlock GETFIELD(Class<?> owner, String name, String desc) {
        return GETFIELD(internalName(owner), name, desc);
    }

    @Override
    public AsmBlock PUTFIELD(Class<?> owner, String name, String desc) {
        return PUTFIELD(internalName(owner), name, desc);
    }

    @Override
    public AsmBlock GETSTATIC(String owner, String name, Class<?> desc) {
        return GETSTATIC(owner, name, fieldDescriptor(desc));
    }

    @Override
    public AsmBlock PUTSTATIC(String owner, String name, Class<?> desc) {
        return PUTSTATIC(owner, name, fieldDescriptor(desc));
    }

    @Override
    public AsmBlock GETFIELD(String owner, String name, Class<?> desc) {
        return GETFIELD(owner, name, fieldDescriptor(desc));
    }

    @Override
    public AsmBlock PUTFIELD(String owner, String name, Class<?> desc) {
        return PUTFIELD(owner, name, fieldDescriptor(desc));
    }

    @Override
    public AsmBlock GETSTATIC(Class<?> owner, String name, Class<?> desc) {
        return GETSTATIC(internalName(owner), name, fieldDescriptor(desc));
    }

    @Override
    public AsmBlock PUTSTATIC(Class<?> owner, String name, Class<?> desc) {
        return PUTSTATIC(internalName(owner), name, fieldDescriptor(desc));
    }

    @Override
    public AsmBlock GETFIELD(Class<?> owner, String name, Class<?> desc) {
        return GETFIELD(internalName(owner), name, fieldDescriptor(desc));
    }

    @Override
    public AsmBlock PUTFIELD(Class<?> owner, String name, Class<?> desc) {
        return PUTFIELD(internalName(owner), name, fieldDescriptor(desc));
    }

    @Override
    public AsmBlock INVOKEVIRTUAL(String owner, String name, String desc) {
        visitor.visitMethodInsn(INVOKEVIRTUAL, owner, name, desc, false);
        return this;
    }

    @Override
    public AsmBlock INVOKESPECIAL(String owner, String name, String desc) {
        visitor.visitMethodInsn(INVOKESPECIAL, owner, name, desc, false);
        return this;
    }

    @Override
    public AsmBlock INVOKESTATIC(String owner, String name, String desc) {
        visitor.visitMethodInsn(INVOKESTATIC, owner, name, desc, false);
        return this;
    }

    @Override
    public AsmBlock INVOKEINTERFACE(String owner, String name, String desc) {
        visitor.visitMethodInsn(INVOKEINTERFACE, owner, name, desc, true);
        return this;
    }

    @Override
    public AsmBlock INVOKEVIRTUAL(String owner, String name, MethodType type) {
        return INVOKEVIRTUAL(owner, name, type.toMethodDescriptorString());
    }

    @Override
    public AsmBlock INVOKESTATIC(String owner, String name, MethodType type) {
        return INVOKESTATIC(owner, name, type.toMethodDescriptorString());
    }

    @Override
    public AsmBlock INVOKEINTERFACE(String owner, String name, MethodType type) {
        return INVOKEINTERFACE(owner, name, type.toMethodDescriptorString());
    }

    @Override
    public AsmBlock INVOKEVIRTUAL(Class<?> owner, String name, String desc) {
        return INVOKEVIRTUAL(internalName(owner), name, desc);
    }

    @Override
    public AsmBlock INVOKESPECIAL(Class<?> owner, String name, String desc) {
        return INVOKESPECIAL(internalName(owner), name, desc);
    }

    @Override
    public AsmBlock INVOKESTATIC(Class<?> owner, String name, String desc) {
        return INVOKESTATIC(internalName(owner), name, desc);
    }

    @Override
    public AsmBlock INVOKEVIRTUAL(Class<?> owner, String name, MethodType type) {
        return INVOKEVIRTUAL(internalName(owner), name, type.toMethodDescriptorString());
    }

    @Override
    public AsmBlock INVOKESPECIAL(Class<?> owner, String name, MethodType type) {
        return INVOKESPECIAL(internalName(owner), name, type.toMethodDescriptorString());
    }

    @Override
    public AsmBlock INVOKESTATIC(Class<?> owner, String name, MethodType type) {
        return INVOKESTATIC(internalName(owner), name, type.toMethodDescriptorString());
    }

    @Override
    public AsmBlock INVOKEINTERFACE(Class<?> owner, String name, String desc) {
        return INVOKEINTERFACE(internalName(owner), name, desc);
    }

    @Override
    public AsmBlock INVOKEINTERFACE(Class<?> owner, String name, MethodType type) {
        return INVOKEINTERFACE(internalName(owner), name, type.toMethodDescriptorString());
    }

    @Override
    public AsmBlock INVOKEDYNAMIC(String name, String desc, Handle bootstrap, Object... args) {
        visitor.visitInvokeDynamicInsn(name, desc, bootstrap, args);
        return this;
    }

    @Override
    public AsmBlock NEW(String type) {
        visitor.visitTypeInsn(NEW, type);
        return this;
    }

    @Override
    public AsmBlock NEW(Class<?> type) {
        visitor.visitTypeInsn(NEW, Type.getType(type).getInternalName());
        return this;
    }

    @Override
    public AsmBlock NEW(Type type) {
        visitor.visitTypeInsn(NEW, type.getInternalName());
        return this;
    }

    @Override
    public AsmBlock NEWARRAY(int type) {
        visitor.visitIntInsn(NEWARRAY, type);
        return this;
    }

    @Override
    public AsmBlock NEWARRAY(String type) {
        visitor.visitTypeInsn(ANEWARRAY, type);
        return this;
    }

    @Override
    public AsmBlock ARRAYLENGTH() {
        return visitInsn(ARRAYLENGTH);
    }

    @Override
    public AsmBlock ATHROW() {
        return visitInsn(ATHROW);
    }

    @Override
    public AsmBlock CHECKCAST(String type) {
        visitor.visitTypeInsn(CHECKCAST, type);
        return this;
    }

    @Override
    public AsmBlock INSTANCEOF(String type) {
        visitor.visitTypeInsn(INSTANCEOF, type);
        return this;
    }

    @Override
    public AsmBlock MONITORENTER() {
        return visitInsn(MONITORENTER);
    }

    @Override
    public AsmBlock MONITOREXIT() {
        return visitInsn(MONITOREXIT);
    }

    @Override
    public AsmBlock MULTINEWARRAY(String desc, int dimensions) {
        visitor.visitMultiANewArrayInsn(desc, dimensions);
        return this;
    }

    @Override
    public AsmBlock IFNULL(Label label) {
        return visitJumpInsn(IFNULL, label);
    }

    @Override
    public AsmBlock IFNONNULL(Label label) {
        return visitJumpInsn(IFNONNULL, label);
    }

    @Override
    public AsmBlock LABEL(Label label) {
        visitor.visitLabel(label);
        return this;
    }

    @Override
    public AsmBlock TRY(Label start, Label end, Label handler, String type) {
        visitor.visitTryCatchBlock(start, end, handler, type);
        return this;
    }

    @Override
    public AsmBlock LINE(int line, Label start) {
        visitor.visitLineNumber(line, start);
        return this;
    }

    @Override
    public AsmBlock PARAMETER(String name, int access) {
        visitor.visitParameter(name, access);
        return this;
    }

    @Override
    public AsmBlock ARRAY(long[] array, int off, int len) {
        NEWARRAY(T_LONG);
        while (off < len) {
            long v = array[off];
            DUP().INT(off++).LONG(v).LASTORE();
        }
        return this;
    }

    @Override
    public AsmBlock ARRAY(double[] array, int off, int len) {
        INT(len - off);
        NEWARRAY(T_DOUBLE);
        while (off < len) {
            double v = array[off];
            DUP().INT(off++).DOUBLE(v).DASTORE();
        }
        return this;
    }

    @Override
    public AsmBlock ARRAY(int[] array, int off, int len) {
        INT(len - off);
        NEWARRAY(T_INT);
        while (off < len) {
            int v = array[off];
            DUP().INT(off++).INT(v).IASTORE();
        }
        return this;
    }

    @Override
    public AsmBlock ARRAY(float[] array, int off, int len) {
        INT(len - off);
        NEWARRAY(T_FLOAT);
        while (off < len) {
            float v = array[off];
            DUP().INT(off++).FLOAT(v).FASTORE();
        }
        return this;
    }

    @Override
    public AsmBlock ARRAY(char[] array, int off, int len) {
        INT(len - off);
        NEWARRAY(T_CHAR);
        while (off < len) {
            char v = array[off];
            DUP().INT(off++).INT(v).CASTORE();
        }
        return this;
    }

    @Override
    public AsmBlock ARRAY(short[] array, int off, int len) {
        INT(len - off);
        NEWARRAY(T_SHORT);
        while (off < len) {
            short v = array[off];
            DUP().INT(off++).INT(v).SASTORE();
        }
        return this;
    }

    @Override
    public AsmBlock ARRAY(byte[] array, int off, int len) {
        INT(len - off);
        NEWARRAY(T_BYTE);
        while (off < len) {
            byte v = array[off];
            DUP().INT(off++).INT(v).BASTORE();
        }
        return this;
    }

    @Override
    public AsmBlock ARRAY(boolean[] array, int off, int len) {
        INT(len - off);
        NEWARRAY(T_BOOLEAN);
        while (off < len) {
            boolean v = array[off];
            DUP().INT(off++).INT(v ? 1 : 0).BASTORE();
        }
        return this;
    }

    @Override
    public <T> AsmBlock ARRAY(T[] array, int off, int len, BiIntConsumer<T> producer) {
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
    public AsmBlock ARRAY(long[] array) {
        INT(array.length);
        NEWARRAY(T_LONG);
        for (int i = 0, j = array.length; i < j; i++) {
            long v = array[i];
            DUP().INT(i).LONG(v).LASTORE();
        }
        return this;
    }

    @Override
    public AsmBlock ARRAY(double[] array) {
        INT(array.length);
        NEWARRAY(T_DOUBLE);
        for (int i = 0, j = array.length; i < j; i++) {
            double v = array[i];
            DUP().INT(i).DOUBLE(v).DASTORE();
        }
        return this;
    }

    @Override
    public AsmBlock ARRAY(int[] array) {
        INT(array.length);
        NEWARRAY(T_INT);
        for (int i = 0, j = array.length; i < j; i++) {
            int v = array[i];
            DUP().INT(i).INT(v).IASTORE();
        }
        return this;
    }

    @Override
    public AsmBlock ARRAY(float[] array) {
        INT(array.length);
        NEWARRAY(T_FLOAT);
        for (int i = 0, j = array.length; i < j; i++) {
            float v = array[i];
            DUP().INT(i).FLOAT(v).FASTORE();
        }
        return this;
    }

    @Override
    public AsmBlock ARRAY(char[] array) {
        INT(array.length);
        NEWARRAY(T_CHAR);
        for (int i = 0, j = array.length; i < j; i++) {
            char v = array[i];
            DUP().INT(i).INT(v).CASTORE();
        }
        return this;
    }

    @Override
    public AsmBlock ARRAY(short[] array) {
        INT(array.length);
        NEWARRAY(T_SHORT);
        for (int i = 0, j = array.length; i < j; i++) {
            short v = array[i];
            DUP().INT(i).INT(v).SASTORE();
        }
        return this;
    }

    @Override
    public AsmBlock ARRAY(byte[] array) {
        INT(array.length);
        NEWARRAY(T_BYTE);
        for (int i = 0, j = array.length; i < j; i++) {
            byte v = array[i];
            DUP().INT(i).INT(v).BASTORE();
        }
        return this;
    }

    @Override
    public AsmBlock ARRAY(boolean[] array) {
        INT(array.length);
        NEWARRAY(T_BOOLEAN);
        for (int i = 0, j = array.length; i < j; i++) {
            boolean v = array[i];
            DUP().INT(i).INT(v ? 1 : 0).BASTORE();
        }
        return this;
    }

    @Override
    public <T> AsmBlock ARRAY(T[] array, BiIntConsumer<T> producer) {
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

    private AsmBlock visitInsn(int opcode) {
        visitor.visitInsn(opcode);
        return this;
    }

    private AsmBlock visitJumpInsn(int opcode, Label label) {
        visitor.visitJumpInsn(opcode, label);
        return this;
    }

    private AsmBlock visitVarInsn(int opcode, int var) {
        visitor.visitVarInsn(opcode, var);
        return this;
    }

    private AsmBlock visitLdcInsn(Object value) {
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
