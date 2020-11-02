package com.myself.bigdecimal;

import java.math.BigDecimal;
import java.util.HashMap;

public class BigDecimalDemo {
    public static void main(String[] args) {
        HashMap map = new HashMap(15);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.put(6, 6);
        map.put(7, 6);
        map.put(8, 6);
        map.put(9, 6);
        map.put(10, 6);
        map.put(11, 6);
        map.put(12, 6);
        map.put(13, 6);
        map.put(14, 6);
        map.put(15, 6);
        map.put(16, 6);
        map.put(17, 6);
        map.put(18, 6);
        map.put(19, 6);
        BigDecimal a = new BigDecimal(1.1);
        BigDecimal c = a.add(new BigDecimal(1.2));
        BigDecimal d = a.multiply(new BigDecimal(1.2));
        System.out.println(c.toPlainString());
        System.out.println(d.toPlainString());
        BigDecimal e = new BigDecimal("99999999999999999999999999999999999999999999999999999999999.9999");
        System.out.println(e.toPlainString());
    }
}
