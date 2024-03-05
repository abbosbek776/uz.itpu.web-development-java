package org.itpu.service;

import org.itpu.domain.User;

public interface UserService {
    boolean checkUserExists(String login, char[] password);

    void registerUser(User user);

}
