package ru.kata.spring.boot_security.demo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private  EntityManager entityManager;

    private final UserRepository userRepository;

    @Autowired
    public UserDaoImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> userList() {
        return userRepository.findAll();
    }

    @Override
    public User show(Long userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        entityManager.persist(user);
        return entityManager.find(User.class, user.getId());
    }

    @Override
    @Transactional
    public User updateUser(User user, Long userId) {
        user.setId(userId);
        entityManager.merge(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
