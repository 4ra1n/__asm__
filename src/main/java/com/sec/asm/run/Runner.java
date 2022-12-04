package com.sec.asm.run;

import com.sec.asm.core.PatchProcessor;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Runner {
    public static void run(String[] args) throws Exception {
        // arg0: full class name
        // arg1: class path
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{
                Paths.get(args[1]).toUri().toURL()});

        Path dir = Paths.get(args[1]);
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                FileVisitResult result = super.visitFile(file, attrs);
                if (file.getFileName().toString().endsWith(".class")) {
                    byte[] bytes = Files.readAllBytes(file);
                    byte[] rewrite = PatchProcessor.processBytes(loader, bytes);
                    if (rewrite != bytes) {
                        Files.write(file, rewrite, StandardOpenOption.TRUNCATE_EXISTING);
                    }
                }
                return result;
            }
        });
        loader.close();
    }
}
