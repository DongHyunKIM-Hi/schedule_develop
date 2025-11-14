package org.example.schedule_develop.domain.comment.controller;

import static org.example.schedule_develop.common.exception.ErrorMessage.NOT_AUTHENTICATED;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.common.exception.CustomException;
import org.example.schedule_develop.common.model.SessionUser;
import org.example.schedule_develop.domain.comment.model.request.request.CommentCreateRequest;
import org.example.schedule_develop.domain.comment.model.request.request.CommentUpdateRequest;
import org.example.schedule_develop.domain.comment.model.response.CommentCreateResponse;
import org.example.schedule_develop.domain.comment.model.response.CommentDeleteResponse;
import org.example.schedule_develop.domain.comment.model.response.CommentReadResponse;
import org.example.schedule_develop.domain.comment.model.response.CommentUpdateResponse;
import org.example.schedule_develop.domain.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @GetMapping("/{commentId}")
    public ResponseEntity<CommentReadResponse> getComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    @PostMapping("/{scheduleId}")
    public ResponseEntity<CommentCreateResponse> createComment(@PathVariable Long scheduleId,
        @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
        @RequestBody CommentCreateRequest request) {
        checkLogin(sessionUser);
        return ResponseEntity.ok(commentService.createComment(sessionUser.getUserId(), scheduleId, request));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentUpdateResponse> updateComment(
        @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
        @PathVariable Long commentId, @RequestBody CommentUpdateRequest request) {
        checkLogin(sessionUser);
        return ResponseEntity.ok(commentService.updateComment(sessionUser.getUserId(), commentId, request));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentDeleteResponse> deleteComment(
        @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
        @PathVariable Long commentId) {
        checkLogin(sessionUser);
        return ResponseEntity.ok(commentService.deleteComment(sessionUser.getUserId(), commentId));
    }

    private void checkLogin(SessionUser sessionUser) {
        if (sessionUser == null) {
            throw new CustomException(NOT_AUTHENTICATED);
        }
    }
}
