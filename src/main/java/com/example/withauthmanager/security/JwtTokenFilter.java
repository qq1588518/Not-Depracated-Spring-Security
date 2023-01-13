package com.example.withauthmanager.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.withauthmanager.services.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private JwtTokenService jwtTokenService;
    private final String TOKEN_HEADER = "token";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(TOKEN_HEADER);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String userEmail = jwtTokenService.getUserEmail(token);
            List<? extends GrantedAuthority> authorities = jwtTokenService.getAuthorities(token)
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userEmail,
                    null,
                    authorities
            );
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            filterChain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            filterChain.doFilter(request, response);
        }
    }
}
