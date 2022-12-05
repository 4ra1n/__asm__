package org.sec.asm.utils;

import org.objectweb.asm.ClassWriter;

import java.lang.reflect.Field;

public final class ASMUtil {
    public static void copySymbolTable(ClassWriter source, ClassWriter target) {
        try {
            Field table = source.getClass().getDeclaredField("symbolTable");
            table.setAccessible(true);
            Object symbolTable = table.get(source);
            table.set(target, symbolTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
