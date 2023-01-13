package com.example.withauthmanager.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.withauthmanager.services.JwtTokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenServiceImp implements JwtTokenService {
    private static final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
    private static final JWTVerifier jwtVerifier = JWT.require(algorithm).build();

    @Override
    public String createToken(String userMail, List<String> grantedAuthorities) {
        return JWT.create()
                .withSubject(userMail)
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withClaim("authorities", grantedAuthorities)
                .sign(algorithm);
    }

    @Override
    public String getUserEmail(String token) {
        return jwtVerifier.verify(token).getSubject();
    }

    @Override
    public List<String> getAuthorities(String token) {
        return jwtVerifier.verify(token).getClaim("authorities").asList(String.class);
    }

}