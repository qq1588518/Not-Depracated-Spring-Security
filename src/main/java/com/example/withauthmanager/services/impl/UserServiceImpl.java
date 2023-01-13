package com.example.withauthmanager.services.impl;

import com.example.withauthmanager.dataBase.models.RoleEntity;
import com.example.withauthmanager.dataBase.models.UserEntity;
import com.example.withauthmanager.dataBase.repositories.RoleRepository;
import com.example.withauthmanager.dataBase.repositories.UserRepository;
import com.example.withauthmanager.models.UserRequest;
import com.example.withauthmanager.models.UserResponse;
import com.example.withauthmanager.services.JwtTokenService;
import com.example.withauthmanager.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;
    private ModelMapper mapper;

    @Override
    public UserRequest register(UserRequest userResponse) {
        UserEntity userEntity = mapper.map(userResponse, UserEntity.class);

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        Optional<RoleEntity> roleOptional = roleRepository.findById(RoleRepository.ID_OF_DEFAULT_ROLE);
        RoleEntity role = roleOptional.orElseThrow(() -> new IllegalArgumentException("Can't get default role"));
        userEntity.setRole_id(role);

        userRepository.save(userEntity);
        return mapper.map(userEntity, UserRequest.class);
    }

    @Override
    public String login(String email, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(token); // если пользователя нет, то выбросит ошибку!

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> authoritiesString = authorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return jwtTokenService.createToken(email, authoritiesString);
    }

    @Override
    public UserResponse getUserRole(String userEmail) {
        Optional<UserEntity> userOptional = userRepository.findUserEntityByEmail(userEmail);

        UserEntity user = userOptional.orElseThrow(() -> new IllegalArgumentException("Wrong email"));

        UserResponse userResponse = mapper.map(user, UserResponse.class);

        RoleEntity roleEntity = user.getRole_id();
        userResponse.setAuthorityEntityList(roleEntity.getAuthorityEntityList());
        return userResponse;
    }
}
