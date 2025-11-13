package org.example.schedule_develop.domain.schedule.model.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.domain.schedule.model.dto.ScheduleDto;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleCreateResponse {

    private Long id;
    private String writer;
    private String title;
    private String content;

    public static ScheduleCreateResponse from(ScheduleDto dto) {
        return new ScheduleCreateResponse(dto.getId(), dto.getWriter(), dto.getTitle(), dto.getContent());
    }

}
