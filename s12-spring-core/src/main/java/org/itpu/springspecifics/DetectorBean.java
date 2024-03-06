package org.itpu.springspecifics;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DetectorBean  implements InitializingBean, DisposableBean, ApplicationContextAware, BeanFactoryAware, BeanNameAware {

    @Value("${my.custom.prop.1}")
    private String propVal1;

    @Value("${my.custom.prop.2}")
    private String propVal2;

    public DetectorBean() {
        System.out.println("step-0 - some-constructor");
        printPropsIfSet();
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("step-1 - BeanNameAwareImpl # setBeanName()");
        printPropsIfSet();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("step-2 - BeanFactoryAwareImpl # setBeanFactory()");
        printPropsIfSet();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("step-3 - ApplicationContextAwareImpl # setApplicationContext()");
        printPropsIfSet();
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("step-4 - @PostConstruct");
        printPropsIfSet();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("step-5 - InitializingBean # afterPropertiesSet()");
        printPropsIfSet();
    }

    public void customInit() {
        System.out.println("step-6 - customInit");
        System.out.println("propVal1 = " + propVal1 + " ... propVal2 = " + propVal2);
    }

    public void detect() {
        System.out.println();
        System.out.println("step-7 - keep patience and watch logs");
        printPropsIfSet();
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("step-destroy-1 - @PreDestroy");
        printPropsIfSet();
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("step-destroy-2 - DisposableBean # destroy()");
        printPropsIfSet();
    }

    private void printPropsIfSet() {
        if (propVal1 != null) {
            System.out.println("pros are set");
        } else {
            System.out.println("NO PROPS YET");
        }
        System.out.println();
    }
}
