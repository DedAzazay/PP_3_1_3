package ru.kata.spring.boot_security.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.dao.UserDao;

import java.util.List;

@Service
public class UserServicesImp implements UserServices {

    private final UserDao userDao;

    @Autowired
    public UserServicesImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User updateUser(User user, Long userId) {
        userDao.updateUser(user, userId);
        return user;
    }

    @Override
    public List<User> userList() {
        return userDao.userList();
    }

    @Override
    public User show(Long userId) {
        return userDao.show(userId);
    }

    @Override
    public void deleteUserById(Long userId) {
        userDao.deleteUserById(userId);
    }
}
