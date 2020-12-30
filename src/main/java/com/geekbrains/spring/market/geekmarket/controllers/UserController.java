package com.geekbrains.spring.market.geekmarket.controllers;

import com.geekbrains.spring.market.geekmarket.dto.UserDto;
import com.geekbrains.spring.market.geekmarket.entities.Role;
import com.geekbrains.spring.market.geekmarket.entities.User;
import com.geekbrains.spring.market.geekmarket.entities.UserDetails;
import com.geekbrains.spring.market.geekmarket.services.RoleService;
import com.geekbrains.spring.market.geekmarket.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.security.Principal;
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
        try {
            roleList.add(roleService.findByRoleName("ROLE_USER"));
        } catch (RoleNotFoundException e) {
            e.printStackTrace();
        }
        User u = new User(login, passwordEncoder.encode(pass), email, roleList);
        userService.saveUser(u);
    }

    @GetMapping("/profile")
    public UserDto getUserData(Principal principal) {
        return new UserDto(userService.findByUsername(principal.getName()));
    }

    @PutMapping("/save")
    public ResponseEntity<?> saveDetails(@RequestBody UserDetails userDetails,
                                @RequestParam String password,
                                Principal principal) {
        if (passwordEncoder.matches(password, userService.findByUsername(principal.getName()).getPassword())) {
            User u = userService.findByUsername(principal.getName());
            u.setUserDetails(userDetails);
            userService.saveUser(u);
            return ResponseEntity.ok(HttpStatus.OK);
        } else return ResponseEntity.ok(HttpStatus.FORBIDDEN);
    }

}
