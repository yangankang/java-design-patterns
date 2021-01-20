package com.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDemoConfiguration {

    @Bean
    public SpringDemoBean getBean() {
        return new SpringDemoBean();
    }
}
