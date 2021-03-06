package com.myself.designpatterns.prototype;

public class Zmain {

    /**
     * @param args
     * @author daniel
     * @time 2016-5-20 上午11:19:39
     */
    public static void main(String[] args) {
        ConcretePrototype1 p1 = new ConcretePrototype1();
        ConcretePrototype1 c1 = (ConcretePrototype1) p1.Clone();

        // System.out.println(c1.getId());

        AbstractPrototype a = new PrototypeImpl1();

        AbstractPrototype b = (AbstractPrototype) a.clone();
        System.out.println(b.getName());
        System.out.println(a.getName());
        a = new PrototypeImpl2();

        System.out.println(a.getName());

    }

}
