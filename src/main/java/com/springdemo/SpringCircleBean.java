package com.springdemo;

import javax.annotation.Resource;
import java.util.List;

public class SpringCircleBean {

    List<SpringDemoBean> springDemoBean;

    @Resource
    public void setSpringDemoBean(List<SpringDemoBean> springDemoBean) {
        this.springDemoBean = springDemoBean;
    }

    public List<SpringDemoBean> getSpringDemoBean() {
        return springDemoBean;
    }

    public String getName() {
        return "2";
    }
}
