package org.sec.asm.run;

import org.sec.asm.core.Patch;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Runner {
    public static void run(String[] args) throws Exception {
        if (args.length != 1) {
            return;
        }
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{
                Paths.get(args[0]).toUri().toURL()});
        Path dir = Paths.get(args[0]);
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                FileVisitResult result = super.visitFile(file, attrs);
                if (file.getFileName().toString().endsWith(".class")) {
                    byte[] bytes = Files.readAllBytes(file);
                    byte[] rewrite = Patch.patchBytes(loader, bytes);
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
