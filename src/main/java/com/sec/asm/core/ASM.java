package com.sec.asm.core;

import com.sec.asm.run.Runner;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.function.Consumer;

public interface ASM {
    static void __asm__internal__(String className) {
        if (!ASMConst.hasRewrite) {
            System.out.println("|----------------ASM----------------|");
            System.out.println("|  Please Restart Java Application  |");
            System.out.println("|-----------------------------------|");

            ClassLoader cl = ClassLoader.getSystemClassLoader();
            URL[] urls = ((URLClassLoader) cl).getURLs();
            URL targetUrl = null;
            for (URL u : urls) {
                if (!u.toString().endsWith(".jar")) {
                    targetUrl = u;
                }
            }
            if (targetUrl == null) {
                return;
            }
            try {
                Runner.run(new String[]{
                        className,
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

    static void __asm__(Consumer<ASMOpcodes> blocker) {
        __asm__internal__(
                blocker.getClass().getName().split("\\$\\$")[0]
        );
    }
}
