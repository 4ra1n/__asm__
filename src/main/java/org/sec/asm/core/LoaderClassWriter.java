package org.sec.asm.core;

import org.objectweb.asm.ClassWriter;

final class LoaderClassWriter extends ClassWriter {

    private final ClassLoader loader;

    public LoaderClassWriter(int flags, ClassLoader loader) {
        super(flags);
        this.loader = loader;
    }

    @Override
    protected ClassLoader getClassLoader() {
        return loader;
    }
}
