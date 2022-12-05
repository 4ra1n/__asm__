package org.sec.asm.core;

import org.objectweb.asm.ClassWriter;

final class LoaderWriter extends ClassWriter {

    private final ClassLoader loader;

    public LoaderWriter(int flags, ClassLoader loader) {
        super(flags);
        this.loader = loader;
    }

    @Override
    protected ClassLoader getClassLoader() {
        return loader;
    }
}
