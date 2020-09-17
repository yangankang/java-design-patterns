package com.myself.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 引用队列可以与软引用、弱引用以及虚引用一起配合使用，当垃圾回收器准备回收一个对象时，如果发现它还有引用，
 * 那么就会在回收对象之前，把这个引用加入到与之关联的引用队列中去。程序可以通过判断引用队列中是否已经加入了
 * 引用，来判断被引用的对象是否将要被垃圾回收，这样就可以在对象被回收之前采取一些必要的措施。
 */
public class ReferenceQueueDemo {
    private static List<WeakReference<ReferenceBean>> list = new ArrayList<>();

    public static void main(String[] args) {
        ReferenceQueue queue = new ReferenceQueue();
        for (int i = 0; i < 10; i++) {
            ReferenceBean bean = new ReferenceBean();
            bean.init();
            WeakReference<ReferenceBean> weakReference = new WeakReference<>(bean, queue);
            list.add(weakReference);
        }

        System.gc();

        for (int i = 0; i < 10; i++) {
            System.out.println(list.get(i).get());
            System.out.println(queue.poll());
        }
    }
}
