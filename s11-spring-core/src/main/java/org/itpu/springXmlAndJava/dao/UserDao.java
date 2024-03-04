package org.itpu.springXmlAndJava.dao;

import org.itpu.springXmlAndJava.domain.User;

import java.util.List;

public interface UserDao {
    User save(User user);

    List<User> getAll();

    User getById(Long id);

    User updateById(User user);

    boolean deleteById(Long id);
}
