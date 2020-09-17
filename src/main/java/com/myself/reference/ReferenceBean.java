package com.myself.reference;

public class ReferenceBean {
    private String name;
    private byte[] buff;

    public ReferenceBean() {
    }

    public ReferenceBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init() {
        buff = new byte[1024 * 1024 * 1024];
    }
}
