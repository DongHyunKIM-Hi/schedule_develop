package org.example.schedule_develop.domain.user.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {

    private String email;
    private String username;
    private String password;
}
