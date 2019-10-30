package com.ioc.springIoc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class BeanTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Bean bean = applicationContext.getBean("bean", Bean.class);
        System.out.println(bean);
    }

}