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
        model.addAttribute("dao", userServices);
        return "/admin";
    }

    @GetMapping("/admin/show")
    @ResponseBody
    public User oneUser(Long id) {
        return userServices.show(id);
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "/new";
    }

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/new";
        }
        userServices.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userServices.show(id));
        return "/edit";
    }

    @PatchMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                                @PathVariable("id") Long userId) {
        userServices.updateUser(user, userId);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServices.deleteUserById(id);
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