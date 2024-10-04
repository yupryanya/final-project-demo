package ru.demo.models.auth;

import lombok.Data;

@Data
public class LoginRequestModel {
    private String username,
            password;
}
