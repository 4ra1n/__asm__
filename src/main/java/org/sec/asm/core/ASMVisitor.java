package org.sec.asm.core;

import org.sec.asm.define.ClassDefiner;
import org.objectweb.asm.*;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Consumer;

public final class ASMVisitor extends ClassVisitor {

    private static final Type ASM_BLOCK = Type
            .getMethodType(Type.VOID_TYPE, Type.getType(ASMOpcodes.class));
    @SuppressWarnings("all")
    private static final Handle LAMBDA_FACTORY_HANDLE = new Handle(
            Opcodes.H_INVOKESTATIC,
            "java/lang/invoke/LambdaMetafactory",
            "metafactory",
            "(Ljava/lang/invoke/MethodHandles$Lookup;" +
                    "Ljava/lang/String;Ljava/lang/invoke/MethodType;" +
                    "Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;" +
                    "Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
            false
    );
    private static final Type CONSUMER = Type.getType(Consumer.class);
    private final ClassLoader loader;
    private final Map<MethodInfo, ClassWriter> methods;
    boolean rewrite;

    public ASMVisitor(ClassVisitor classVisitor, ClassLoader loader,
                      Map<MethodInfo, ClassWriter> methods) {
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
        if (methods.containsKey(new MethodInfo(name, descriptor))) {
            return null;
        }

        return new MethodVisitor(Opcodes.ASM9,
                super.visitMethod(access, name, descriptor, signature, exceptions)) {
            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String descriptor,
                                        boolean isInterface) {
                if (opcode == Opcodes.INVOKESTATIC && owner.equals("org/sec/asm/core/ASM")
                        && isInlineName(name)
                        && isInlineDescriptor(descriptor)) {
                    return;
                }
                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
            }

            @Override
            public void visitInvokeDynamicInsn(String name, String descriptor,
                                               Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
                if (LAMBDA_FACTORY_HANDLE.equals(bootstrapMethodHandle)) {
                    if (bootstrapMethodArguments.length > 2) {
                        Object o = bootstrapMethodArguments[1];
                        if (o instanceof Handle) {
                            Handle handle = (Handle) o;
                            if (handle.getTag() == Opcodes.H_INVOKESTATIC) {
                                Type type = Type.getMethodType(handle.getDesc());
                                if (ASM_BLOCK.equals(type)) {
                                    try {
                                        String methodName = handle.getName();
                                        Class<?> klass = generateInlineClass(
                                                new MethodInfo(methodName, Constants.BLOCK_TYPE_DESC));
                                        ASMOpcodes block = new ASMCoreVisitor(this);

                                        Method method = klass.getDeclaredMethod(methodName,
                                                Constants.BLOCK_TYPE.parameterArray());
                                        method.setAccessible(true);
                                        method.invoke(null, block);

                                        ASMVisitor.this.rewrite = true;

                                    } catch (Exception ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
                super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle,
                        bootstrapMethodArguments);
            }

            private boolean isInlineName(String name) {
                return name.startsWith("__asm__");
            }

            private boolean isInlineDescriptor(String desc) {
                Type[] parameters = Type.getArgumentTypes(desc);
                return parameters.length == 1 && CONSUMER.equals(parameters[0]);
            }
        };
    }

    private Class<?> generateInlineClass(MethodInfo info) {
        ClassWriter writer = methods.get(info);
        if (writer == null) {
            throw new RuntimeException("error");
        }
        byte[] classBytes = writer.toByteArray();
        return ClassDefiner.INSTANCE.defineClass(loader, classBytes, 0, classBytes.length);
    }
}
