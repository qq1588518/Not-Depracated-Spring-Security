package com.example.withauthmanager.models;

import lombok.Data;

@Data
public class UserRequest {
    private String email;
    private String password;
}
