package org.example.schedule_develop.domain.comment.model.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.domain.comment.model.dto.CommentDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateResponse {

    private long id;
    private long userId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static CommentUpdateResponse from(CommentDto dto) {
        return new CommentUpdateResponse(
            dto.getId(),
            dto.getUserId(),
            dto.getContent(),
            dto.getCreatedAt(),
            dto.getModifiedAt());
    }

}
