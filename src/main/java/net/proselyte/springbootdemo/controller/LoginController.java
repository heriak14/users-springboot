package net.proselyte.springbootdemo.controller;

import net.proselyte.springbootdemo.service.JpaUserService;
import net.proselyte.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginAs() {
        return "user-login";
    }

    @PostMapping("/login")
    public String loginAs(String email, String password) {
        return "redirect:/users";
    }
}