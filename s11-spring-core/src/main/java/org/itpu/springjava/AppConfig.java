package org.itpu.springjava;


import org.itpu.springjava.dao.UserDao;
import org.itpu.springjava.dao.UserDaoImpl;
import org.itpu.springjava.dao.UserDaoV2Impl;
import org.itpu.springjava.service.UserService;
import org.itpu.springjava.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;

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
