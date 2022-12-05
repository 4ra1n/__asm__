package org.sec.asm.core;

import org.sec.asm.utils.ASMUtil;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.util.*;

public final class Patch {

    public Patch() {
    }

    public static byte[] patchBytes(ClassLoader loader, byte[] bytes) {
        ClassReader reader = new ClassReader(bytes);
        Map<MethodRef, ClassWriter> methods = new HashMap<>();
        reader.accept(new CollectVisitor(methods), 0);
        if (methods.isEmpty()) {
            return bytes;
        }
        ClassWriter rewriter = new ClassWriter(reader, 0);
        ASMVisitor inline = new ASMVisitor(rewriter, loader, methods);
        reader.accept(inline, 0);
        if (inline.rewrite) {
            ClassWriter writer = new LoaderWriter(ClassWriter.COMPUTE_FRAMES, loader);
            ASMUtil.copySymbolTable(rewriter, writer);
            new ClassReader(rewriter.toByteArray()).accept(writer, 0);
            return writer.toByteArray();
        }
        return bytes;
    }
}
