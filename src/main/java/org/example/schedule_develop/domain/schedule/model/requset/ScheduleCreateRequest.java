package org.example.schedule_develop.domain.schedule.model.requset;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleCreateRequest {

    private String title;
    private String content;

}
