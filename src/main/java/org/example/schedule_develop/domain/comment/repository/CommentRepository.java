package org.example.schedule_develop.domain.comment.repository;

import org.example.schedule_develop.common.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
