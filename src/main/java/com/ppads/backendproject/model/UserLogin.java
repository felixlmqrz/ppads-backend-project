package com.ppads.backendproject.model;

import lombok.Data;

@Data
public class UserLogin {

    private String email;

    private String senha;

    private String token;
}
