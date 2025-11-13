package org.example.schedule_develop.domain.schedule.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.domain.schedule.model.dto.ScheduleDto;
import org.example.schedule_develop.domain.user.model.dto.UserDto;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleCreateResponse {

    private Long id;
    private UserDto writer;
    private String title;
    private String content;

    public static ScheduleCreateResponse from(ScheduleDto dto) {
        return new ScheduleCreateResponse(
            dto.getId(),
            dto.getWriter(),
            dto.getTitle(),
            dto.getContent()
        );
    }

}
