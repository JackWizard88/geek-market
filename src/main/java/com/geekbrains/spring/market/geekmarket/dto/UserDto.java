package com.geekbrains.spring.market.geekmarket.dto;

import com.geekbrains.spring.market.geekmarket.entities.User;
import com.geekbrains.spring.market.geekmarket.utils.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String birthDate;
    private boolean sex;
    private String city;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getUserDetails().getFirstName();
        this.lastName = user.getUserDetails().getLastName();
        this.phoneNumber = user.getUserDetails().getPhoneNumber();
        this.birthDate = user.getUserDetails().getBirthDate();
        this.sex = user.getUserDetails().isSex();
        this.city = user.getUserDetails().getCity();
    }
}
