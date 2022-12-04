package com.sec.asm.run;

import com.sec.asm.core.PatchProcessor;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Runner {
    public static void main(String[] args) throws Exception {
        String className = "Test";
        String classesDir = "target/classes/";
        System.out.println("[*] start patch");
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{
                Paths.get(classesDir).toUri().toURL()});
        String finalPath = String.format("%s%s.class",
                classesDir, className.replace(".", "/"));
        Path target = Paths.get(finalPath);
        byte[] bytes = Files.readAllBytes(target);
        byte[] data = PatchProcessor.processBytes(loader, bytes);
        Files.write(target, data);
        System.out.println("[*] patch success");
        String java = System.getProperty("os.name")
                .toLowerCase().contains("windows") ? "java.exe" : "java";
        String javaExec = String.format("%s%sbin%s%s",
                System.getProperty("java.home"), File.separator, File.separator, java);
        System.out.println("[*] start run");
        System.out.println("[*] use java: " + javaExec);
        String cmd = String.format("%s %s", javaExec, className);
        Process p = Runtime.getRuntime().exec(cmd, new String[]{}, new File(classesDir));
        InputStream is = p.getInputStream();
        InputStream eis = p.getErrorStream();
        read(is);
        read(eis);
    }

    private static void read(InputStream is){
        try {
            int bufferSize = 1024;
            char[] buffer = new char[bufferSize];
            StringBuilder out = new StringBuilder();
            Reader in = new InputStreamReader(is, StandardCharsets.UTF_8);
            for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0; ) {
                out.append(buffer, 0, numRead);
            }
            if(!out.toString().trim().equals("")){
                System.out.println(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
