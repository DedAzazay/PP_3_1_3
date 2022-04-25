package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;


@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserServices userServices;

    @Autowired
    public UserRestController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('users:read')")
    public List<User> allUsers() {
        return userServices.userList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public User oneUser(@PathVariable("id") Long id) {
        return userServices.show(id);
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('users:write')")
    public User createUser(@RequestBody User user)
    {
        return userServices.saveUser(user);
    }


    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public User updateUser(@RequestBody User user, @PathVariable("id") Long userId)
    {
        return userServices.updateUser(user, userId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteUser(@PathVariable("id") Long id) {
        userServices.deleteUserById(id);
    }

    @ExceptionHandler(BindException.class)
    public String handle(Exception exc) {
        return exc.getMessage();
    }
}
