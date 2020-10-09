package com.geekbrains.spring.market.geekmarket.controllers;

import com.geekbrains.spring.market.geekmarket.entities.Role;
import com.geekbrains.spring.market.geekmarket.entities.User;
import com.geekbrains.spring.market.geekmarket.services.RoleService;
import com.geekbrains.spring.market.geekmarket.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showCartPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@RequestParam String login,
                                  @RequestParam String pass,
                                  @RequestParam String email) {
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleService.findByRoleName("ROLE_USER"));
        User u = new User(login, passwordEncoder.encode(pass), email, roleList);
        userService.saveNewUser(u);
        return "redirect:/login";
    }
}
