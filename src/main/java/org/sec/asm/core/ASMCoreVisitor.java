package org.sec.asm.core;

import org.objectweb.asm.*;

import java.lang.invoke.MethodType;

import static org.objectweb.asm.Opcodes.*;

@SuppressWarnings("all")
public class ASMCoreVisitor implements ASMOpcodes, ASM {

    private final MethodVisitor visitor;

    public ASMCoreVisitor(MethodVisitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public ASMOpcodes INIT() {
        return this;
    }

    @Override
    public ASMOpcodes NOP() {
        return visitInsn(NOP);
    }

    @Override
    public ASMOpcodes ACONST_NULL() {
        return visitInsn(ACONST_NULL);
    }

    @Override
    public ASMOpcodes INT(int value) {
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
    public ASMOpcodes LONG(long value) {
        MethodVisitor visitor = this.visitor;
        if (value == 0L || value == 1L) {
            visitor.visitInsn((int) (value + 9));
        } else {
            visitor.visitLdcInsn(value);
        }
        return this;
    }

    @Override
    public ASMOpcodes FLOAT(float value) {
        MethodVisitor visitor = this.visitor;
        if (value == 0.0F || value == 1.0F || value == 2.0F) {
            visitor.visitInsn((int) value + 11);
        } else {
            visitor.visitLdcInsn(value);
        }
        return this;
    }

    @Override
    public ASMOpcodes DOUBLE(double value) {
        MethodVisitor visitor = this.visitor;
        if (value == 0.0 || value == 1.0) {
            visitor.visitInsn((int) value + 14);
        } else {
            visitor.visitLdcInsn(value);
        }
        return this;
    }

    @Override
    public ASMOpcodes LDC(String value) {
        return visitLdcInsn(value);
    }

    @Override
    public ASMOpcodes LDC(Class<?> type) {
        return visitLdcInsn(Type.getType(type));
    }

    @Override
    public ASMOpcodes LDC(Type type) {
        return visitLdcInsn(type);
    }

    @Override
    public ASMOpcodes LDC(MethodType type) {
        Class<?>[] $parameters = type.parameterArray();
        int j = $parameters.length;
        Type[] parameters = new Type[j];
        while (j-- > 0) {
            parameters[j] = Type.getType($parameters[j]);
        }
        return visitLdcInsn(Type.getMethodType(Type.getType(type.returnType()), parameters));
    }

    @Override
    public ASMOpcodes LDC(Handle handle) {
        return visitLdcInsn(handle);
    }

    @Override
    public ASMOpcodes LDC(ConstantDynamic constantDynamic) {
        return visitLdcInsn(constantDynamic);
    }

    @Override
    public ASMOpcodes LDC_W() {
        throw new RuntimeException("ASM NOT SUPPORT LDC_W");
    }

    @Override
    public ASMOpcodes LDC_2W() {
        throw new RuntimeException("ASM NOT SUPPORT LDC_2W");
    }

    @Override
    public ASMOpcodes LCONST_0() {
        return LONG(0L);
    }

    @Override
    public ASMOpcodes LCONST_1() {
        return LONG(1L);
    }

    @Override
    public ASMOpcodes DCONST_0() {
        return DOUBLE(0.0D);
    }

    @Override
    public ASMOpcodes DCONST_1() {
        return DOUBLE(1.0D);
    }

    @Override
    public ASMOpcodes FCONST_0() {
        return FLOAT(0.0F);
    }

    @Override
    public ASMOpcodes FCONST_1() {
        return FLOAT(1.0F);
    }

    @Override
    public ASMOpcodes FCONST_2() {
        return FLOAT(2.0F);
    }

    @Override
    public ASMOpcodes ICONST_M1() {
        return INT(-1);
    }

    @Override
    public ASMOpcodes ICONST_0() {
        return INT(0);
    }

    @Override
    public ASMOpcodes ICONST_1() {
        return INT(1);
    }

    @Override
    public ASMOpcodes ICONST_2() {
        return INT(2);
    }

    @Override
    public ASMOpcodes ICONST_3() {
        return INT(3);
    }

    @Override
    public ASMOpcodes ICONST_4() {
        return INT(4);
    }

    @Override
    public ASMOpcodes ICONST_5() {
        return INT(5);
    }

    @Override
    public ASMOpcodes ILOAD(int idx) {
        return visitVarInsn(ILOAD, idx);
    }

    @Override
    public ASMOpcodes LLOAD(int idx) {
        return visitVarInsn(LLOAD, idx);
    }

    @Override
    public ASMOpcodes FLOAD(int idx) {
        return visitVarInsn(FLOAD, idx);
    }

    @Override
    public ASMOpcodes DLOAD(int idx) {
        return visitVarInsn(DLOAD, idx);
    }

    @Override
    public ASMOpcodes ALOAD(int idx) {
        return visitVarInsn(ALOAD, idx);
    }

    @Override
    public ASMOpcodes IALOAD() {
        return visitInsn(IALOAD);
    }

    @Override
    public ASMOpcodes LALOAD() {
        return visitInsn(LALOAD);
    }

    @Override
    public ASMOpcodes FALOAD() {
        return visitInsn(FALOAD);
    }

    @Override
    public ASMOpcodes DALOAD() {
        return visitInsn(DALOAD);
    }

    @Override
    public ASMOpcodes AALOAD() {
        return visitInsn(AALOAD);
    }

    @Override
    public ASMOpcodes BALOAD() {
        return visitInsn(BALOAD);
    }

    @Override
    public ASMOpcodes CALOAD() {
        return visitInsn(CALOAD);
    }

    @Override
    public ASMOpcodes SALOAD() {
        return visitInsn(SALOAD);
    }

    @Override
    public ASMOpcodes ISTORE(int idx) {
        return visitVarInsn(ISTORE, idx);
    }

    @Override
    public ASMOpcodes LSTORE(int idx) {
        return visitVarInsn(LSTORE, idx);
    }

    @Override
    public ASMOpcodes FSTORE(int idx) {
        return visitVarInsn(FSTORE, idx);
    }

    @Override
    public ASMOpcodes DSTORE(int idx) {
        return visitVarInsn(DSTORE, idx);
    }

    @Override
    public ASMOpcodes ASTORE(int idx) {
        return visitVarInsn(ASTORE, idx);
    }

    @Override
    public ASMOpcodes IASTORE() {
        return visitInsn(IASTORE);
    }

    @Override
    public ASMOpcodes LASTORE() {
        return visitInsn(LASTORE);
    }

    @Override
    public ASMOpcodes FASTORE() {
        return visitInsn(FASTORE);
    }

    @Override
    public ASMOpcodes DASTORE() {
        return visitInsn(DASTORE);
    }

    @Override
    public ASMOpcodes AASTORE() {
        return visitInsn(AASTORE);
    }

    @Override
    public ASMOpcodes BASTORE() {
        return visitInsn(BASTORE);
    }

    @Override
    public ASMOpcodes CASTORE() {
        return visitInsn(CASTORE);
    }

    @Override
    public ASMOpcodes SASTORE() {
        return visitInsn(SASTORE);
    }

    @Override
    public ASMOpcodes POP() {
        return visitInsn(POP);
    }

    @Override
    public ASMOpcodes POP2() {
        return visitInsn(POP2);
    }

    @Override
    public ASMOpcodes DUP() {
        return visitInsn(DUP);
    }

    @Override
    public ASMOpcodes DUPX1() {
        return visitInsn(DUP_X1);
    }

    @Override
    public ASMOpcodes DUPX2() {
        return visitInsn(DUP_X2);
    }

    @Override
    public ASMOpcodes DUP2() {
        return visitInsn(DUP2);
    }

    @Override
    public ASMOpcodes DUP2X1() {
        return visitInsn(DUP2_X1);
    }

    @Override
    public ASMOpcodes DUP2X2() {
        return visitInsn(DUP2_X2);
    }

    @Override
    public ASMOpcodes SWAP() {
        return visitInsn(SWAP);
    }

    @Override
    public ASMOpcodes IADD() {
        return visitInsn(IADD);
    }

    @Override
    public ASMOpcodes LADD() {
        return visitInsn(LADD);
    }

    @Override
    public ASMOpcodes FADD() {
        return visitInsn(FADD);
    }

    @Override
    public ASMOpcodes DADD() {
        return visitInsn(DADD);
    }

    @Override
    public ASMOpcodes ISUB() {
        return visitInsn(ISUB);
    }

    @Override
    public ASMOpcodes LSUB() {
        return visitInsn(LSUB);
    }

    @Override
    public ASMOpcodes FSUB() {
        return visitInsn(FSUB);
    }

    @Override
    public ASMOpcodes DSUB() {
        return visitInsn(DSUB);
    }

    @Override
    public ASMOpcodes IMUL() {
        return visitInsn(IMUL);
    }

    @Override
    public ASMOpcodes LMUL() {
        return visitInsn(LMUL);
    }

    @Override
    public ASMOpcodes FMUL() {
        return visitInsn(FMUL);
    }

    @Override
    public ASMOpcodes DMUL() {
        return visitInsn(DMUL);
    }

    @Override
    public ASMOpcodes IDIV() {
        return visitInsn(IDIV);
    }

    @Override
    public ASMOpcodes LDIV() {
        return visitInsn(LDIV);
    }

    @Override
    public ASMOpcodes FDIV() {
        return visitInsn(FDIV);
    }

    @Override
    public ASMOpcodes DDIV() {
        return visitInsn(DDIV);
    }

    @Override
    public ASMOpcodes IREM() {
        return visitInsn(IREM);
    }

    @Override
    public ASMOpcodes LREM() {
        return visitInsn(LREM);
    }

    @Override
    public ASMOpcodes FREM() {
        return visitInsn(FREM);
    }

    @Override
    public ASMOpcodes DREM() {
        return visitInsn(DREM);
    }

    @Override
    public ASMOpcodes INEG() {
        return visitInsn(INEG);
    }

    @Override
    public ASMOpcodes LNEG() {
        return visitInsn(LNEG);
    }

    @Override
    public ASMOpcodes FNEG() {
        return visitInsn(FNEG);
    }

    @Override
    public ASMOpcodes DNEG() {
        return visitInsn(DNEG);
    }

    @Override
    public ASMOpcodes ISHL() {
        return visitInsn(ISHL);
    }

    @Override
    public ASMOpcodes LSHL() {
        return visitInsn(LSHL);
    }

    @Override
    public ASMOpcodes ISHR() {
        return visitInsn(ISHR);
    }

    @Override
    public ASMOpcodes LSHR() {
        return visitInsn(LSHR);
    }

    @Override
    public ASMOpcodes IUSHR() {
        return visitInsn(IUSHR);
    }

    @Override
    public ASMOpcodes LUSHR() {
        return visitInsn(LUSHR);
    }

    @Override
    public ASMOpcodes IAND() {
        return visitInsn(IAND);
    }

    @Override
    public ASMOpcodes LAND() {
        return visitInsn(LAND);
    }

    @Override
    public ASMOpcodes IOR() {
        return visitInsn(IOR);
    }

    @Override
    public ASMOpcodes LOR() {
        return visitInsn(LOR);
    }

    @Override
    public ASMOpcodes IXOR() {
        return visitInsn(IXOR);
    }

    @Override
    public ASMOpcodes LXOR() {
        return visitInsn(LXOR);
    }

    @Override
    public ASMOpcodes IINC(int idx, int value) {
        visitor.visitIincInsn(idx, value);
        return this;
    }

    @Override
    public ASMOpcodes I2L() {
        return visitInsn(I2L);
    }

    @Override
    public ASMOpcodes I2F() {
        return visitInsn(I2F);
    }

    @Override
    public ASMOpcodes I2D() {
        return visitInsn(I2D);
    }

    @Override
    public ASMOpcodes L2I() {
        return visitInsn(L2I);
    }

    @Override
    public ASMOpcodes L2F() {
        return visitInsn(L2F);
    }

    @Override
    public ASMOpcodes L2D() {
        return visitInsn(L2D);
    }

    @Override
    public ASMOpcodes F2I() {
        return visitInsn(F2I);
    }

    @Override
    public ASMOpcodes F2L() {
        return visitInsn(F2L);
    }

    @Override
    public ASMOpcodes F2D() {
        return visitInsn(F2D);
    }

    @Override
    public ASMOpcodes D2I() {
        return visitInsn(D2I);
    }

    @Override
    public ASMOpcodes D2L() {
        return visitInsn(D2L);
    }

    @Override
    public ASMOpcodes D2F() {
        return visitInsn(D2F);
    }

    @Override
    public ASMOpcodes I2B() {
        return visitInsn(I2B);
    }

    @Override
    public ASMOpcodes I2C() {
        return visitInsn(I2C);
    }

    @Override
    public ASMOpcodes I2S() {
        return visitInsn(I2S);
    }

    @Override
    public ASMOpcodes LCMP() {
        return visitInsn(LCMP);
    }

    @Override
    public ASMOpcodes FCMPL() {
        return visitInsn(FCMPL);
    }

    @Override
    public ASMOpcodes FCMPG() {
        return visitInsn(FCMPG);
    }

    @Override
    public ASMOpcodes DCMPL() {
        return visitInsn(DCMPL);
    }

    @Override
    public ASMOpcodes DCMPG() {
        return visitInsn(DCMPG);
    }

    @Override
    public ASMOpcodes IFEQ(Label label) {
        return visitJumpInsn(IFEQ, label);
    }

    @Override
    public ASMOpcodes IFNE(Label label) {
        return visitJumpInsn(IFNE, label);
    }

    @Override
    public ASMOpcodes IFLT(Label label) {
        return visitJumpInsn(IFLT, label);
    }

    @Override
    public ASMOpcodes IFGE(Label label) {
        return visitJumpInsn(IFGE, label);
    }

    @Override
    public ASMOpcodes IFGT(Label label) {
        return visitJumpInsn(IFGT, label);
    }

    @Override
    public ASMOpcodes IFLE(Label label) {
        return visitJumpInsn(IFLE, label);
    }

    @Override
    public ASMOpcodes ICMPEQ(Label label) {
        return visitJumpInsn(IF_ICMPEQ, label);
    }

    @Override
    public ASMOpcodes ICMPNE(Label label) {
        return visitJumpInsn(IF_ICMPNE, label);
    }

    @Override
    public ASMOpcodes ICMPLT(Label label) {
        return visitJumpInsn(IF_ICMPLT, label);
    }

    @Override
    public ASMOpcodes ICMPGE(Label label) {
        return visitJumpInsn(IF_ICMPGE, label);
    }

    @Override
    public ASMOpcodes ICMPGT(Label label) {
        return visitJumpInsn(IF_ICMPGT, label);
    }

    @Override
    public ASMOpcodes ICMPLE(Label label) {
        return visitJumpInsn(IF_ICMPLE, label);
    }

    @Override
    public ASMOpcodes ACMPEQ(Label label) {
        return visitJumpInsn(IF_ACMPEQ, label);
    }

    @Override
    public ASMOpcodes ACMPNE(Label label) {
        return visitJumpInsn(IF_ACMPNE, label);
    }

    @Override
    public ASMOpcodes GOTO(Label label) {
        return visitJumpInsn(GOTO, label);
    }

    @Override
    public ASMOpcodes GOTO_W() {
        throw new RuntimeException("ASM NOT SUPPORT GOTO_W");
    }

    @Override
    public ASMOpcodes TABLESWITCH(int min, int max, Label dflt, Label... labels) {
        visitor.visitTableSwitchInsn(min, max, dflt, labels);
        return this;
    }

    @Override
    public ASMOpcodes LOOKUPSWITCH(int[] keys, Label dflt, Label... labels) {
        visitor.visitLookupSwitchInsn(dflt, keys, labels);
        return this;
    }

    @Override
    public ASMOpcodes IRETURN() {
        return visitInsn(IRETURN);
    }

    @Override
    public ASMOpcodes LRETURN() {
        return visitInsn(LRETURN);
    }

    @Override
    public ASMOpcodes FRETURN() {
        return visitInsn(FRETURN);
    }

    @Override
    public ASMOpcodes DRETURN() {
        return visitInsn(DRETURN);
    }

    @Override
    public ASMOpcodes ARETURN() {
        return visitInsn(ARETURN);
    }

    @Override
    public ASMOpcodes RETURN() {
        return visitInsn(RETURN);
    }

    @Override
    public ASMOpcodes RET(int index) {
        return visitVarInsn(RET, index);
    }

    @Override
    public ASMOpcodes JSR() {
        return visitInsn(JSR);
    }


    @Override
    public ASMOpcodes GETSTATIC(String owner, String name, String desc) {
        visitor.visitFieldInsn(GETSTATIC, owner, name, desc);
        return this;
    }

    @Override
    public ASMOpcodes PUTSTATIC(String owner, String name, String desc) {
        visitor.visitFieldInsn(PUTSTATIC, owner, name, desc);
        return this;
    }

    @Override
    public ASMOpcodes GETFIELD(String owner, String name, String desc) {
        visitor.visitFieldInsn(GETFIELD, owner, name, desc);
        return this;
    }

    @Override
    public ASMOpcodes PUTFIELD(String owner, String name, String desc) {
        visitor.visitFieldInsn(PUTFIELD, owner, name, desc);
        return this;
    }

    @Override
    public ASMOpcodes GETSTATIC(Class<?> owner, String name, String desc) {
        return GETSTATIC(internalName(owner), name, desc);
    }

    @Override
    public ASMOpcodes PUTSTATIC(Class<?> owner, String name, String desc) {
        return PUTSTATIC(internalName(owner), name, desc);
    }

    @Override
    public ASMOpcodes GETFIELD(Class<?> owner, String name, String desc) {
        return GETFIELD(internalName(owner), name, desc);
    }

    @Override
    public ASMOpcodes PUTFIELD(Class<?> owner, String name, String desc) {
        return PUTFIELD(internalName(owner), name, desc);
    }

    @Override
    public ASMOpcodes GETSTATIC(String owner, String name, Class<?> desc) {
        return GETSTATIC(owner, name, fieldDescriptor(desc));
    }

    @Override
    public ASMOpcodes PUTSTATIC(String owner, String name, Class<?> desc) {
        return PUTSTATIC(owner, name, fieldDescriptor(desc));
    }

    @Override
    public ASMOpcodes GETFIELD(String owner, String name, Class<?> desc) {
        return GETFIELD(owner, name, fieldDescriptor(desc));
    }

    @Override
    public ASMOpcodes PUTFIELD(String owner, String name, Class<?> desc) {
        return PUTFIELD(owner, name, fieldDescriptor(desc));
    }

    @Override
    public ASMOpcodes GETSTATIC(Class<?> owner, String name, Class<?> desc) {
        return GETSTATIC(internalName(owner), name, fieldDescriptor(desc));
    }

    @Override
    public ASMOpcodes PUTSTATIC(Class<?> owner, String name, Class<?> desc) {
        return PUTSTATIC(internalName(owner), name, fieldDescriptor(desc));
    }

    @Override
    public ASMOpcodes GETFIELD(Class<?> owner, String name, Class<?> desc) {
        return GETFIELD(internalName(owner), name, fieldDescriptor(desc));
    }

    @Override
    public ASMOpcodes PUTFIELD(Class<?> owner, String name, Class<?> desc) {
        return PUTFIELD(internalName(owner), name, fieldDescriptor(desc));
    }

    @Override
    public ASMOpcodes INVOKEVIRTUAL(String owner, String name, String desc) {
        visitor.visitMethodInsn(INVOKEVIRTUAL, owner, name, desc, false);
        return this;
    }

    @Override
    public ASMOpcodes INVOKESPECIAL(String owner, String name, String desc) {
        visitor.visitMethodInsn(INVOKESPECIAL, owner, name, desc, false);
        return this;
    }

    @Override
    public ASMOpcodes INVOKESTATIC(String owner, String name, String desc) {
        visitor.visitMethodInsn(INVOKESTATIC, owner, name, desc, false);
        return this;
    }

    @Override
    public ASMOpcodes INVOKEINTERFACE(String owner, String name, String desc) {
        visitor.visitMethodInsn(INVOKEINTERFACE, owner, name, desc, true);
        return this;
    }

    @Override
    public ASMOpcodes INVOKEVIRTUAL(String owner, String name, MethodType type) {
        return INVOKEVIRTUAL(owner, name, type.toMethodDescriptorString());
    }

    @Override
    public ASMOpcodes INVOKESTATIC(String owner, String name, MethodType type) {
        return INVOKESTATIC(owner, name, type.toMethodDescriptorString());
    }

    @Override
    public ASMOpcodes INVOKEINTERFACE(String owner, String name, MethodType type) {
        return INVOKEINTERFACE(owner, name, type.toMethodDescriptorString());
    }

    @Override
    public ASMOpcodes INVOKEVIRTUAL(Class<?> owner, String name, String desc) {
        return INVOKEVIRTUAL(internalName(owner), name, desc);
    }

    @Override
    public ASMOpcodes INVOKESPECIAL(Class<?> owner, String name, String desc) {
        return INVOKESPECIAL(internalName(owner), name, desc);
    }

    @Override
    public ASMOpcodes INVOKESTATIC(Class<?> owner, String name, String desc) {
        return INVOKESTATIC(internalName(owner), name, desc);
    }

    @Override
    public ASMOpcodes INVOKEVIRTUAL(Class<?> owner, String name, MethodType type) {
        return INVOKEVIRTUAL(internalName(owner), name, type.toMethodDescriptorString());
    }

    @Override
    public ASMOpcodes INVOKESPECIAL(Class<?> owner, String name, MethodType type) {
        return INVOKESPECIAL(internalName(owner), name, type.toMethodDescriptorString());
    }

    @Override
    public ASMOpcodes INVOKESTATIC(Class<?> owner, String name, MethodType type) {
        return INVOKESTATIC(internalName(owner), name, type.toMethodDescriptorString());
    }

    @Override
    public ASMOpcodes INVOKEINTERFACE(Class<?> owner, String name, String desc) {
        return INVOKEINTERFACE(internalName(owner), name, desc);
    }

    @Override
    public ASMOpcodes INVOKEINTERFACE(Class<?> owner, String name, MethodType type) {
        return INVOKEINTERFACE(internalName(owner), name, type.toMethodDescriptorString());
    }

    @Override
    public ASMOpcodes INVOKEDYNAMIC(String name, String desc, Handle bootstrap, Object... args) {
        visitor.visitInvokeDynamicInsn(name, desc, bootstrap, args);
        return this;
    }

    @Override
    public ASMOpcodes NEW(String type) {
        visitor.visitTypeInsn(NEW, type);
        return this;
    }

    @Override
    public ASMOpcodes NEW(Class<?> type) {
        visitor.visitTypeInsn(NEW, Type.getType(type).getInternalName());
        return this;
    }

    @Override
    public ASMOpcodes NEW(Type type) {
        visitor.visitTypeInsn(NEW, type.getInternalName());
        return this;
    }

    @Override
    public ASMOpcodes NEWARRAY(int type) {
        visitor.visitIntInsn(NEWARRAY, type);
        return this;
    }

    @Override
    public ASMOpcodes NEWARRAY(String type) {
        visitor.visitTypeInsn(ANEWARRAY, type);
        return this;
    }

    @Override
    public ASMOpcodes ANEWARRAY(Class<?> type) {
        visitor.visitTypeInsn(ANEWARRAY, Type.getInternalName(type));
        return this;
    }

    @Override
    public ASMOpcodes ARRAYLENGTH() {
        return visitInsn(ARRAYLENGTH);
    }

    @Override
    public ASMOpcodes ATHROW() {
        return visitInsn(ATHROW);
    }

    @Override
    public ASMOpcodes CHECKCAST(String type) {
        visitor.visitTypeInsn(CHECKCAST, type);
        return this;
    }

    @Override
    public ASMOpcodes INSTANCEOF(String type) {
        visitor.visitTypeInsn(INSTANCEOF, type);
        return this;
    }

    @Override
    public ASMOpcodes MONITORENTER() {
        return visitInsn(MONITORENTER);
    }

    @Override
    public ASMOpcodes MONITOREXIT() {
        return visitInsn(MONITOREXIT);
    }

    @Override
    public ASMOpcodes MULTINEWARRAY(String desc, int dimensions) {
        visitor.visitMultiANewArrayInsn(desc, dimensions);
        return this;
    }

    @Override
    public ASMOpcodes IFNULL(Label label) {
        return visitJumpInsn(IFNULL, label);
    }

    @Override
    public ASMOpcodes IFNONNULL(Label label) {
        return visitJumpInsn(IFNONNULL, label);
    }

    @Override
    public ASMOpcodes LABEL(Label label) {
        visitor.visitLabel(label);
        return this;
    }

    @Override
    public ASMOpcodes TRY(Label start, Label end, Label handler, String type) {
        visitor.visitTryCatchBlock(start, end, handler, type);
        return this;
    }

    public ASMOpcodes WIDE() {
        throw new RuntimeException("ASM NOT SUPPORT LDC_W");
    }

    private ASMOpcodes visitInsn(int opcode) {
        visitor.visitInsn(opcode);
        return this;
    }

    private ASMOpcodes visitJumpInsn(int opcode, Label label) {
        visitor.visitJumpInsn(opcode, label);
        return this;
    }

    private ASMOpcodes visitVarInsn(int opcode, int var) {
        visitor.visitVarInsn(opcode, var);
        return this;
    }

    private ASMOpcodes visitLdcInsn(Object value) {
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
