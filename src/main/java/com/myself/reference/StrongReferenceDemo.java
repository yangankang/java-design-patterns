package com.myself.reference;

/**
 * 强引用
 * 只要强引用存在，垃圾回收器将永远不会回收被引用的对象，哪怕内存不足时，JVM也会直接抛出OutOfMemoryError，不会去回收。
 * 如果想中断强引用与对象之间的联系，可以显示的将强引用赋值为null，这样一来，JVM就可以适时的回收对象了
 */
public class StrongReferenceDemo {
    private ReferenceBean bean = new ReferenceBean("Strong Reference");
}
