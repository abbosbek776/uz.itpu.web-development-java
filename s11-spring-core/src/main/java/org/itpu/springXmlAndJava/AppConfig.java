package org.itpu.springXmlAndJava;

import org.itpu.springXmlAndJava.dao.UserDao;
import org.itpu.springXmlAndJava.dao.UserDaoImpl;
import org.itpu.springXmlAndJava.dao.UserDaoV2Impl;
import org.itpu.springXmlAndJava.service.UserService;
import org.itpu.springXmlAndJava.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UserService userServiceImpl(UserDao userDaoImpl) {
        return new UserServiceImpl(userDaoImpl);
    }

    @Bean
    public UserDao userDaoImpl() {
        return new UserDaoImpl();
    }

    @Bean
    public UserDao userDaoV2Impl() {
        return new UserDaoV2Impl();
    }
}
