package ru.demo.models.users;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseModel {
    private String email,
            name,
            id,
            state;
    private List<String> roles;
}