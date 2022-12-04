package com.sec.asm.core;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;

public class ASMInline {

  public static void main(String[] args) throws Exception{
    URLClassLoader loader = URLClassLoader.newInstance(new URL[]{
            Paths.get("target/classes").toUri().toURL()});
    String className = "com.sec.asm.test.Test";
    Path target = Paths.get(
            "target/classes/"+className.replace(".","/")+".class");
    byte[] bytes = Files.readAllBytes(target);
    byte[] data = PatchProcessor.processBytes(loader,bytes);
    Files.write(target,data);
  }
}
