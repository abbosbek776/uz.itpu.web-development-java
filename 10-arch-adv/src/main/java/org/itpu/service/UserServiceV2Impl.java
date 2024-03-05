package org.itpu.service;

import org.itpu.domain.User;

import static java.util.Objects.isNull;

public class UserServiceV2Impl implements UserService {

    private static UserService instance;

    private Object dao = new Object();

    private UserServiceV2Impl(){}

    public static UserService getInstance() {
        if (instance == null) {
            synchronized(UserServiceV2Impl.class) {
                UserService inst = instance;
                if (inst == null) {
                    synchronized(UserServiceV2Impl.class) {
                        instance = new UserServiceV2Impl();
                    }
                }
            }
        }
        return instance;
    }

    @Override
    public boolean checkUserExists(String login, char[] password) {
        // valide login and password
        validate(login, password);

        // ask dao to check the existence by login and password

        boolean isUserExist = true;
//        isUserExist = dao.checkUser(login, password);

        return isUserExist;
    }

    private void validate(String login, char[] password) {
        if (isNull(login)
                || login.isBlank()
                || isNull(password)
                || password.length == 0) {
            throw new RuntimeException("Parameter validation failed during checkUserExists(login, password)");
        }
    }

    @Override
    public void registerUser(User user) {
        // validation step for user param

        // ask dao to save user
    }
}
