package com.j2v8demo;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Object;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class J2V8Main {

    public int add(int i1, int i2) {
        return i1 + i2;
    }

    public static void main(String[] args) throws IOException {
        String js = IOUtils.toString(J2V8Main.class.getResourceAsStream("/js/j2v8.js"));
        V8 runtime = V8.createV8Runtime();
        J2V8Main j2V8Main = new J2V8Main();
        V8Object v8Object = new V8Object(runtime);
        runtime.add("calc", v8Object);
        v8Object.registerJavaMethod(j2V8Main, "add", "add", new Class[]{int.class, int.class});
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            int result = runtime.executeIntegerScript(js);
            // System.out.println("Test JS result = " + result);
            // int i1 = 10, i2 = 20;
            // j2V8Main.add(i1, i2);
        }
        System.out.println("time " + (System.currentTimeMillis() - time));
        v8Object.release();
        runtime.release();
    }
}
