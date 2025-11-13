package org.example.schedule_develop.domain.user.model.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.domain.user.model.dto.UserDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDeleteResponse {

    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static UserDeleteResponse from(UserDto dto) {
        return new UserDeleteResponse(
            dto.getId(),
            dto.getUsername(),
            dto.getEmail(),
            dto.getPassword(),
            dto.getCreatedAt(),
            dto.getModifiedAt()
        );
    }
}
