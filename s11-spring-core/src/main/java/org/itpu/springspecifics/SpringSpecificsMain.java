package org.itpu.springspecifics;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("org.itpu.springspecifics")
@PropertySource("classpath:/application.properties")
@Configuration
public class SpringSpecificsMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringSpecificsMain.class);

        SomeBean someBean = context.getBean("someBean", SomeBean.class);
        someBean.doSomething();
        someBean = null;

        // this will release resources
        context.registerShutdownHook();

    }
}
