package org.example.schedule_develop.domain.user.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateRequest {
    private String email;
    private String username;

}
