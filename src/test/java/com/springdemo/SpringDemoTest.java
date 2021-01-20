package com.springdemo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemoTest {

    @Test
    public void testXml() {
        System.setProperty("spring.profiles.active", "dev");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        SpringDemoBean bean1 = context.getBean(SpringDemoBean.class);
        SpringCircleBean bean2 = context.getBean(SpringCircleBean.class);
        // SpringManualDemoBean bean2 = context.getBean(SpringManualDemoBean.class);
        bean1.getName();
        bean2.getName();
        bean1.getSpringCircleBean().getName();
        bean2.getSpringDemoBean().get(0).getName();
        // bean2.print();
    }

    @Test
    public void testAnnotation() {
        //从java注解的配置中加载配置到容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringDemoConfiguration.class);
        //从容器中获取对象实例
        SpringDemoBean man = context.getBean(SpringDemoBean.class);
        man.getName();
    }
}
