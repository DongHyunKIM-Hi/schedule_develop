package org.example.schedule_develop.domain.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.domain.user.model.dto.UserDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponse {

    private Long id;
    private String username;
    private String email;

    public static UserCreateResponse from(UserDto dto) {
        return new UserCreateResponse(dto.getId(), dto.getUsername(), dto.getEmail());
    }
}
