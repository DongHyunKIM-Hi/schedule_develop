package org.example.schedule_develop.domain.schedule.model.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.domain.schedule.model.dto.ScheduleDto;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDeletedResponse {

    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ScheduleDeletedResponse from(ScheduleDto dto) {
        return new ScheduleDeletedResponse(dto.getId(), dto.getWriter(), dto.getTitle(), dto.getContent(), dto.getCreatedAt(), dto.getModifiedAt());
    }
}
