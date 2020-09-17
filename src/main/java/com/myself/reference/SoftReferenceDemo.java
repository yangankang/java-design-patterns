package com.myself.reference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 软引用
 * 在内存足够的时候，软引用对象不会被回收，只有在内存不足时，系统则会回收软引用对象，如果回收了软引用对象之后仍然没有足够的内存，
 * 才会抛出内存溢出异常。
 */
public class SoftReferenceDemo {
    private static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {
        testSoftReference();
    }

    private static void testSoftReference() {
        for (int i = 0; i < 10; i++) {
            ReferenceBean referenceBean = new ReferenceBean();
            referenceBean.init();
            SoftReference<ReferenceBean> sr = new SoftReference<ReferenceBean>(referenceBean);
            list.add(sr);
        }

        System.gc(); //主动通知垃圾回收

        for (int i = 0; i < list.size(); i++) {
            Object obj = ((SoftReference) list.get(i)).get();
            System.out.println(obj);
        }

    }
}
