package org.example.schedule_develop.domain.schedule.model.requset;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleUpdateRequest {

    private String title;
    private String content;

}
