package com.myself.reference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WeakReferenceDemo {
    private static List<WeakReference<ReferenceBean>> list = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ReferenceBean bean = new ReferenceBean();
            bean.init();
            WeakReference<ReferenceBean> weakReference = new WeakReference<>(bean);
            list.add(weakReference);
        }

        System.gc();

        for (int i = 0; i < 10; i++) {
            System.out.println(list.get(i).get());
        }
    }
}
