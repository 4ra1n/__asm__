package com.chaitin.asm.test;

import com.chaitin.asm.define.Java8ClassDefiner;
import com.chaitin.asm.define.Java9ClassDefiner;

public class Test {
    public static void main(String[] args) {
        new Java8ClassDefiner().defineClass(Test.class.getClassLoader(), new byte[]{0x00},0,10);
    }
}
