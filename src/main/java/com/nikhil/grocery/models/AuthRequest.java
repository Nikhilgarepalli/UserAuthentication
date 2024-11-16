package com.nikhil.grocery.models;


import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
