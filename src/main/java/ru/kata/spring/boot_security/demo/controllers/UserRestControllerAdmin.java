package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.services.UserServices;

@RestController
public class UserRestControllerAdmin {

    private final UserServices userServices;

    @Autowired
    public UserRestControllerAdmin(UserServices userServices) {
        this.userServices = userServices;
    }


}
