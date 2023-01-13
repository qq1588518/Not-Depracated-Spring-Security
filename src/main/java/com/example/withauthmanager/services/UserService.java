package com.example.withauthmanager.services;


import com.example.withauthmanager.models.UserRequest;
import com.example.withauthmanager.models.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserRequest register(UserRequest userResponse);

    String login(String email, String password);

    UserResponse getUserRole(String userEmail);


}
