package org.example.schedule_develop.domain.comment.model.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.domain.comment.model.dto.CommentDto;
import org.example.schedule_develop.domain.schedule.model.dto.ScheduleDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDeleteResponse {

    private long id;
    private long userId;
    private String content;
    private ScheduleDto schedule;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static CommentDeleteResponse from(CommentDto dto) {
        return new CommentDeleteResponse(
            dto.getId(),
            dto.getUserId(),
            dto.getContent(),
            dto.getSchedule(),
            dto.getCreatedAt(),
            dto.getModifiedAt());
    }

}
