package org.example.schedule_develop.domain.user.model.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.domain.user.model.dto.UserDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateResponse {

    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static UserUpdateResponse from(UserDto dto) {
        return new UserUpdateResponse(dto.getId(), dto.getUsername(), dto.getEmail(), dto.getCreatedAt(),
            dto.getModifiedAt());
    }
}
