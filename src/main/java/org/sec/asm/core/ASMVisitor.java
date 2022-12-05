package org.sec.asm.core;

import org.objectweb.asm.*;

import java.util.Map;

public final class ASMVisitor extends ClassVisitor {
    private final ClassLoader loader;
    private final Map<MethodRef, ClassWriter> methods;
    boolean rewrite;

    public ASMVisitor(ClassVisitor classVisitor, ClassLoader loader,
                      Map<MethodRef, ClassWriter> methods) {
        super(Opcodes.ASM9, classVisitor);
        this.loader = loader;
        this.methods = methods;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName,
                      String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                                     String[] exceptions) {
        if (methods.containsKey(new MethodRef(name, descriptor))) {
            return null;
        }

        return new CoreAdapter(Opcodes.ASM9,
                super.visitMethod(access, name, descriptor, signature, exceptions),
                loader, methods,this);
    }
}
