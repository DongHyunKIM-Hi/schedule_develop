package org.example.schedule_develop.domain.schedule.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.common.entity.Schedule;
import org.example.schedule_develop.domain.user.model.dto.UserDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {

    private Long id;
    private UserDto writer;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ScheduleDto from(Schedule schedule) {
        return new ScheduleDto(
            schedule.getId(),
            UserDto.from(schedule.getWriter()),
            schedule.getTitle(),
            schedule.getContent(),
            schedule.getCreatedAt(),
            schedule.getModifiedAt()
        );
    }

}
