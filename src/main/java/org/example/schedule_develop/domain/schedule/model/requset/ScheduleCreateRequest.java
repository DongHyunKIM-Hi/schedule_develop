package org.example.schedule_develop.domain.schedule.model.requset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleCreateRequest {

    @NotBlank(message = "제목을 입력해주세요")
    @Size(max = 10, message = "10자 이하로 입력해주세요.")
    private String title;
    @NotBlank(message = "할일을 입력해주세요")
    @Size(max = 10, message = "10자 이하로 입력해주세요.")
    private String content;

}
