package com.j2v8demo;

import org.apache.commons.io.IOUtils;

import javax.script.*;
import java.io.IOException;

/**
 * java自带的js引擎性能比j2v8更好为啥
 * 需要和java交互时java自带的js引擎更好
 * 不需要java交互时j2v8更好
 * nashorn.jar以后会被废弃所以不选用这个包
 */
public class JavaScriptEngine {
    public static void main(String[] args) throws IOException, ScriptException {
        String js = IOUtils.toString(J2V8Main.class.getResourceAsStream("/js/j2v8.js"));

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
        Bindings bindings = new SimpleBindings();
        J2V8Main j2V8Main = new J2V8Main();
        bindings.put("calc", j2V8Main);
        scriptEngine.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Object object = scriptEngine.eval(js);
            // System.out.println(object);
        }
        System.out.println("time " + (System.currentTimeMillis() - time));
    }
}
