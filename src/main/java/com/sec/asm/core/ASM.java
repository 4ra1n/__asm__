package com.sec.asm.core;

import com.sec.asm.run.Runner;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.function.Consumer;

public interface ASM {
    static void __asm__internal__() {
        if (!ASMConst.hasRewrite) {
            System.out.println("|----------------ASM----------------|");
            System.out.println("|  Please Restart Java Application  |");
            System.out.println("|-----------------------------------|");

            ClassLoader cl = ClassLoader.getSystemClassLoader();
            URL[] urls = ((URLClassLoader) cl).getURLs();
            URL targetUrl = null;
            for (URL u : urls) {
                if (!u.toString().endsWith(".jar") &&
                        u.toString().toLowerCase().contains("classes")) {
                    // this must be class path
                    targetUrl = u;
                }
            }
            if (targetUrl == null) {
                return;
            }
            try {
                Runner.run(new String[]{
                        targetUrl.toURI().getPath().substring(1)
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.setOut(new PrintStream(new OutputStream() {
                @Override
                public void write(int b) {
                }
            }));
            System.setErr(new PrintStream(new OutputStream() {
                @Override
                public void write(int b) {
                }
            }));
            ASMConst.hasRewrite = true;
        }
    }

    static void __asm__(Consumer<ASMOpcodes> _) {
        __asm__internal__();
    }
}
