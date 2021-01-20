package com.springdemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class SpringManualDemoBean implements BeanFactoryPostProcessor {

    public void print() {
        System.out.println("manual demo bean");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("run manual post process bean method");
    }
}
