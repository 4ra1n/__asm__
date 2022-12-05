package org.sec.asm.core;

import org.sec.asm.run.Runner;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public interface ASM {
    static void __asm__internal__() {
        if (!Constants.hasRewrite) {
            System.out.println("|----------------ASM----------------|");
            System.out.println("|  Please Restart Java Application  |");
            System.out.println("|-----------------------------------|");

            ClassLoader cl = ClassLoader.getSystemClassLoader();
            URL[] urls = ((URLClassLoader) cl).getURLs();
            List<URL> targetUrls = new ArrayList<>();
            for (URL u : urls) {
                if (!u.toString().endsWith(".jar") &&
                        u.toString().toLowerCase().contains("classes")) {
                    // this must be class path
                    targetUrls.add(u);
                }
            }
            if (targetUrls.size() == 0) {
                return;
            }
            try {
                for (URL targetUrl : targetUrls) {
                    String temp;
                    if(System.getProperty("os.name").toLowerCase().contains("windows")){
                        temp = targetUrl.toURI().getPath().substring(1);
                    }else{
                        temp = targetUrl.toURI().getPath();
                    }
                    Runner.run(new String[]{temp});
                }
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
            Constants.hasRewrite = true;
        }
    }

    static void __asm__(Consumer<ASMOpcodes> ignored) {
        __asm__internal__();
    }
}
