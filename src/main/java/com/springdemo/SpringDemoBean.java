package com.springdemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;

import javax.annotation.Resource;

public class SpringDemoBean /*implements BeanDefinitionRegistryPostProcessor*/ {

    SpringCircleBean springCircleBean;

    @Resource
    public void setSpringCircleBean(SpringCircleBean springCircleBean) {
        this.springCircleBean = springCircleBean;
    }

    public SpringCircleBean getSpringCircleBean() {
        return springCircleBean;
    }

    public String getName() {
        System.out.println("call get name");
        return "spring-demo-bean";
    }

    //    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        registry.registerBeanDefinition("manualBean", new RootBeanDefinition(SpringManualDemoBean.class));
    }

    //    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
