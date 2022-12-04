package com.sec.asm.run;

import com.sec.asm.core.PatchProcessor;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;

public class Runner {
    public static void run(String[] args) throws Exception {
        // arg0: full class name
        // arg1: class path
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{
                Paths.get(args[1]).toUri().toURL()});
        String finalPath = String.format("%s%s.class",
                args[1], args[0].replace(".", "/"));
        Path target = Paths.get(finalPath);
        byte[] bytes = Files.readAllBytes(target);
        byte[] data = PatchProcessor.processBytes(loader, bytes);
        Files.write(target, data);
    }
}
