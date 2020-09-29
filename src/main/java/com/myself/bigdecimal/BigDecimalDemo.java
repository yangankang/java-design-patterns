package com.myself.bigdecimal;

import java.math.BigDecimal;

public class BigDecimalDemo {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(1.1);
        BigDecimal c = a.add(new BigDecimal(1.2));
        BigDecimal d = a.multiply(new BigDecimal(1.2));
        System.out.println(c.toPlainString());
        System.out.println(d.toPlainString());
        BigDecimal e = new BigDecimal("99999999999999999999999999999999999999999999999999999999999.9999");
        System.out.println(e.toPlainString());
    }
}
