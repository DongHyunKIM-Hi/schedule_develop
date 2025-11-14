package org.example.schedule_develop.domain.comment.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.common.entity.Comment;
import org.example.schedule_develop.domain.schedule.model.dto.ScheduleDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;
    private String content;
    private ScheduleDto schedule;
    private long userId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static CommentDto from(Comment comment) {
        return new CommentDto(
            comment.getId(),
            comment.getContent(),
            ScheduleDto.from(comment.getSchedule()),
            comment.getUserId(),
            comment.getCreatedAt(),
            comment.getModifiedAt());
    }
}
