package com.calmify.backend.dto;

public class LoginResponseDto {
    private Long id;
    private String token;
    private String email;
    private String name;

    public LoginResponseDto() {
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}