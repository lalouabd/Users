package com.bandg.users.jwt;

import com.bandg.users.config.JwtConfig;
import com.bandg.users.models.User;
import com.bandg.users.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public JwtUsernameAndPasswordAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JwtConfig jwtConfig,
            SecretKey secretKey,
            PasswordEncoder passwordEncoder,
            UserService userService) {
            this.authenticationManager = authenticationManager;
            this.jwtConfig = jwtConfig;
            this.secretKey = secretKey;
            this.passwordEncoder = passwordEncoder;
            this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            System.out.println( "pass " +authenticationRequest.getPassword());
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );

            Authentication authenticate = authenticationManager.authenticate(authentication);
            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String
                token = Jwts.builder()
                .setSubject(authResult.getName())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .claim("authorities", authResult.getAuthorities())
                .signWith(secretKey)
                .compact();
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix()+ " " + token);
 }
}


