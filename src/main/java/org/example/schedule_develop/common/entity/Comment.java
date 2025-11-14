package org.example.schedule_develop.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.domain.comment.model.request.request.CommentUpdateRequest;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private long userId;

    public Comment(Schedule schedule, long userId, String content) {
        this.schedule = schedule;
        this.userId = userId;
        this.content = content;
    }

    protected void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void update(CommentUpdateRequest request) {
        this.content = request.getContent() != null ? request.getContent() : this.content;
    }
}
