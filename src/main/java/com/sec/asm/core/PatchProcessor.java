package com.sec.asm.core;

import com.sec.asm.utils.ASMUtil;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.util.*;

public final class PatchProcessor {

    public PatchProcessor() {
    }

    public static byte[] processBytes(ClassLoader loader, byte[] bytes) {
        ClassReader reader = new ClassReader(bytes);
        Map<MethodInfo, ClassWriter> methods = new HashMap<>();
        reader.accept(new BlocksCollector(methods), 0);
        if (methods.isEmpty()) {
            return bytes;
        }
        ClassWriter rewriter = new ClassWriter(reader, 0);
        ASMInlineCore inline = new ASMInlineCore(rewriter, loader, methods);
        reader.accept(inline, 0);
        if (inline.rewrite) {
            ClassWriter writer = new LoaderBoundClassWriter(ClassWriter.COMPUTE_FRAMES, loader);
            ASMUtil.copySymbolTable(rewriter, writer);
            new ClassReader(rewriter.toByteArray()).accept(writer, 0);
            return writer.toByteArray();
        }
        return bytes;
    }
}
