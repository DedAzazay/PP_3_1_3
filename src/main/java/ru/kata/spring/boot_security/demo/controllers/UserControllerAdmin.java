package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping()
public class UserControllerAdmin {

    private final UserServices userServices;

    @Autowired
    public UserControllerAdmin(UserServices userServices) {
        this.userServices = userServices;
    }


    @GetMapping("/admin")
    public String adminUsers(Model model) {
        model.addAttribute("users", userServices.showByEmail(SecurityContextHolder
                .getContext().getAuthentication().getName()));
        model.addAttribute("listUser", userServices.userList());

        return "/admin";
    }

    @GetMapping("/admin/show")
    @ResponseBody
    public User oneUser(Long id) {
        return userServices.show(id);
    }


    @PostMapping("/admin")
    public String createUser(User user) {
        userServices.saveUser(user);
        return "redirect:/admin";
    }


    @PatchMapping("/admin/update")
    public String updateUser(User user) {
        userServices.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete")
    public String deleteUser(User user) {
        userServices.deleteUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String standardUser(Model model) {
        model.addAttribute("users",
                userServices.showByEmail(SecurityContextHolder
                        .getContext().getAuthentication().getName()));
        return "/user";
    }

    @ExceptionHandler(Exception.class)
    public String handle(Exception exc) {
        return exc.getMessage();
    }


}