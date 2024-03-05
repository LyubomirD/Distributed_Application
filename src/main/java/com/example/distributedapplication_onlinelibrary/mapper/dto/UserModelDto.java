package com.example.distributedapplication_onlinelibrary.mapper.dto;

import lombok.Data;

@Data
public class UserModelDto {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final int age;
    private final char sex;
}
