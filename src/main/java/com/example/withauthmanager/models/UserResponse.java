package com.example.withauthmanager.models;

import com.example.withauthmanager.dataBase.models.AuthorityEntity;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {
    private String email;
    private String roleValue;
    private Set<AuthorityEntity> authorityEntityList;
}
