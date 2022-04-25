package ru.kata.spring.boot_security.demo.dao;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    User saveUser(User user);

    User updateUser(User user, Long userId);

    List<User> userList();

    User show(Long userId);

    void deleteUserById(Long userId);
}
