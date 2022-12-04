package com.sec.asm.core;

import java.lang.invoke.MethodType;

final class Constants {

    static final MethodType BLOCK_TYPE = MethodType.methodType(Void.TYPE, ASMOpcodes.class);
    static final String BLOCK_TYPE_DESC = BLOCK_TYPE.toMethodDescriptorString();

    private Constants() {
    }
}
