package org.example.schedule_develop.domain.schedule.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.domain.schedule.model.dto.ScheduleDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleReadPageResponse {

    private Long id;
    private String title;
    private String content;
    private long commentCount;
    private String writer;
    private String createdAt;
    private String modifiedAt;

    public static ScheduleReadPageResponse from(ScheduleDto scheduleDto, long commentCount) {
        return new ScheduleReadPageResponse(
            scheduleDto.getId(),
            scheduleDto.getTitle(),
            scheduleDto.getContent(),
            commentCount,
            scheduleDto.getWriter().getUsername(),
            scheduleDto.getCreatedAt().toString(),
            scheduleDto.getModifiedAt().toString()
        );
    }
}
