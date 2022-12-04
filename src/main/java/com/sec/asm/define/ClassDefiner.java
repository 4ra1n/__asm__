package com.sec.asm.define;

import com.sec.asm.utils.VersionUtil;

public interface ClassDefiner {

  ClassDefiner INSTANCE = VersionUtil.java9 ? new Java9ClassDefiner() : new Java8ClassDefiner();

  Class<?> defineClass(ClassLoader loader, byte[] classBytes, int off, int len);
}
