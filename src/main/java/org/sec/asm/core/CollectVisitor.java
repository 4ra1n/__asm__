package org.sec.asm.core;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Modifier;
import java.util.Map;

final class CollectVisitor extends ClassVisitor {

    private final Map<MethodRef, ClassWriter> writers;
    private int version;
    private String name;

    public CollectVisitor(
            Map<MethodRef, ClassWriter> writers) {
        super(Opcodes.ASM9);
        this.writers = writers;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName,
                      String[] interfaces) {
        this.version = version;
        this.name = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                                     String[] exceptions) {
        if (Modifier.isStatic(access) && Constants.BLOCK_TYPE_DESC.equals(descriptor)) {
            ClassWriter writer = new ClassWriter(0);
            String className = String.format("%s$%s$%s", this.name, name, System.currentTimeMillis());
            writer.visit(version, Opcodes.ACC_PUBLIC, className,
                    null, "java/lang/Object", null);
            MethodVisitor proxy = writer.visitMethod(access, name, descriptor, signature, exceptions);
            writers.put(new MethodRef(name, descriptor), writer);
            return proxy;
        }
        return null;
    }
}
