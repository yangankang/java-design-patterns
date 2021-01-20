package com.j2v8demo;

import sun.management.VMManagement;

import java.lang.management.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InfoMain {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        Field jvm = runtime.getClass().getDeclaredField("jvm");
        jvm.setAccessible(true);
        VMManagement mgmt = (VMManagement) jvm.get(runtime);
        Method pid_method = mgmt.getClass().getDeclaredMethod("getProcessId");
        pid_method.setAccessible(true);

        int pid = (Integer) pid_method.invoke(mgmt);
        System.out.println(pid);

        OperatingSystemMXBean osMxBean = ManagementFactory.getOperatingSystemMXBean();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo ti : threadInfos) {
            System.out.println(ti.getThreadName() + " " + ti.getThreadId());
            System.out.println("Cpu Time " + threadMXBean.getThreadCpuTime(ti.getThreadId()));
        }
    }
}
