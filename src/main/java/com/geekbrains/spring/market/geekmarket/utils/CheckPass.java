package com.geekbrains.spring.market.geekmarket.utils;

import com.geekbrains.spring.market.geekmarket.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Principal;

@Data
@RequiredArgsConstructor
public class CheckPass extends Check {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean check(Principal principal, String pass) {
        if (passwordEncoder.matches(pass, userService.findByUsername(principal.getName()).getPassword())) {
            return checkNext(principal, pass);
        }

        return false;
    }
}
