package com.nikhil.grocery.controller;


import com.nikhil.grocery.service.CustomUserDetailsService;
import com.nikhil.grocery.Security.JwtUtil;
import com.nikhil.grocery.models.AuthRequest;
import com.nikhil.grocery.models.AuthResponse;
import com.nikhil.grocery.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsService.saveUser(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public AuthResponse loginUser(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new Exception("Invalid email or password");
        }
        String token = jwtUtil.generateToken(authRequest.getEmail());
        return new AuthResponse(token);
    }
}
