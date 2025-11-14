package org.example.schedule_develop.domain.comment.service;

import static org.example.schedule_develop.common.exception.ErrorMessage.NOT_FOUND_COMMENT;
import static org.example.schedule_develop.common.exception.ErrorMessage.NOT_FOUND_SCHEDULE;
import static org.example.schedule_develop.common.exception.ErrorMessage.NOT_VALID_OWNER;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.common.entity.Comment;
import org.example.schedule_develop.common.entity.Schedule;
import org.example.schedule_develop.common.exception.CustomException;
import org.example.schedule_develop.domain.comment.model.dto.CommentDto;
import org.example.schedule_develop.domain.comment.model.request.request.CommentCreateRequest;
import org.example.schedule_develop.domain.comment.model.request.request.CommentUpdateRequest;
import org.example.schedule_develop.domain.comment.model.response.CommentCreateResponse;
import org.example.schedule_develop.domain.comment.model.response.CommentDeleteResponse;
import org.example.schedule_develop.domain.comment.model.response.CommentReadResponse;
import org.example.schedule_develop.domain.comment.model.response.CommentUpdateResponse;
import org.example.schedule_develop.domain.comment.repository.CommentRepository;
import org.example.schedule_develop.domain.schedule.repository.ScheduleRepository;
import org.example.schedule_develop.domain.schedule.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {

    private final ScheduleService scheduleService;
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentCreateResponse createComment(long userId, long scheduleId, CommentCreateRequest request) {

        Schedule schedule = scheduleRepository.findById(scheduleId)
            .orElseThrow(() -> new CustomException(NOT_FOUND_SCHEDULE));
        Comment comment = new Comment(schedule, userId, request.getContent());
        commentRepository.save(comment);
        schedule.addComment(comment);
        scheduleRepository.save(schedule);
        CommentDto dto = CommentDto.from(comment);

        return CommentCreateResponse.from(dto);
    }

    @Transactional(readOnly = true)
    public CommentReadResponse getComment(long commentId) {

        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));
        CommentDto dto = CommentDto.from(comment);

        return CommentReadResponse.from(dto);
    }

    public CommentUpdateResponse updateComment(long userId, long commentId, CommentUpdateRequest request) {

        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));
        isOwner(userId, comment.getUserId());
        comment.update(request);
        commentRepository.save(comment);
        CommentDto dto = CommentDto.from(comment);

        return CommentUpdateResponse.from(dto);
    }

    public CommentDeleteResponse deleteComment(long userId, long commentId) {

        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));
        isOwner(userId, comment.getUserId());
        commentRepository.delete(comment);
        CommentDto dto = CommentDto.from(comment);

        return CommentDeleteResponse.from(dto);
    }

    void isOwner(long nowLoginUserId, long commentsOwnerId) {

        if (nowLoginUserId != commentsOwnerId) {
            throw new CustomException(NOT_VALID_OWNER);
        }
    }
}
