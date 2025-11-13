package org.example.schedule_develop.domain.schedule.model.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.domain.schedule.model.dto.ScheduleDto;
import org.example.schedule_develop.domain.user.model.dto.UserDto;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleUpdateResponse {

    private Long id;
    private UserDto writer;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ScheduleUpdateResponse from(ScheduleDto dto) {
        return new ScheduleUpdateResponse(
            dto.getId(),
            dto.getWriter(),
            dto.getTitle(),
            dto.getContent(),
            dto.getCreatedAt(),
            dto.getModifiedAt()
        );
    }

}
