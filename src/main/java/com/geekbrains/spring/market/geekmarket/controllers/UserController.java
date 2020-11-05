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

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public void registerNewUser(@RequestParam String login,
                                  @RequestParam String pass,
                                  @RequestParam String email) {
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleService.findByRoleName("ROLE_USER"));
        User u = new User(login, passwordEncoder.encode(pass), email, roleList);
        userService.saveNewUser(u);
    }
}
