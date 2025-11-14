package org.example.schedule_develop.domain.comment.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.domain.comment.model.dto.CommentDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateResponse {

    private long id;
    private long userId;
    private String content;

    public static CommentCreateResponse from(CommentDto dto) {
        return new CommentCreateResponse(dto.getId(), dto.getUserId(), dto.getContent());
    }

}
