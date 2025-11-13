package org.example.schedule_develop.domain.user.model.request;


import lombok.Getter;

@Getter
public class LoginRequest {

    private String email;
    private String password;
}
