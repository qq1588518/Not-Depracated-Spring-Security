package com.example.withauthmanager.services;


import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface JwtTokenService {
    String createToken(String userMail, List<String> grantedAuthorities);

    String getUserEmail(String token);

    List<String> getAuthorities(String token);
}
