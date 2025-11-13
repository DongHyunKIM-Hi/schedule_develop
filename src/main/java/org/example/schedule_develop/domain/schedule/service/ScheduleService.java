package org.example.schedule_develop.domain.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.common.entity.Schedule;
import org.example.schedule_develop.common.entity.User;
import org.example.schedule_develop.domain.schedule.model.dto.ScheduleDto;
import org.example.schedule_develop.domain.schedule.model.requset.ScheduleCreateRequest;
import org.example.schedule_develop.domain.schedule.model.requset.ScheduleUpdateRequest;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleCreateResponse;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleDeleteResponse;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleReadResponse;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleUpdateResponse;
import org.example.schedule_develop.domain.schedule.repository.ScheduleRepository;
import org.example.schedule_develop.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleCreateResponse createSchedule(long userId, ScheduleCreateRequest request) {

        User user = userRepository.findById(userId).orElseThrow();

        Schedule schedule = new Schedule(user, request.getTitle(), request.getContent());
        scheduleRepository.save(schedule);
        ScheduleDto dto = ScheduleDto.from(schedule);

        return ScheduleCreateResponse.from(dto);
    }

    public ScheduleUpdateResponse updateSchedule(long userId, long scheduleId, ScheduleUpdateRequest request) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();
        isOwner(userId, schedule.getWriter().getId());
        schedule.update(request);
        scheduleRepository.save(schedule);
        ScheduleDto dto = ScheduleDto.from(schedule);

        return ScheduleUpdateResponse.from(dto);
    }

    public ScheduleDeleteResponse deleteSchedule(long userId, long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();
        isOwner(userId, schedule.getWriter().getId());
        scheduleRepository.delete(schedule);
        ScheduleDto dto = ScheduleDto.from(schedule);

        return ScheduleDeleteResponse.from(dto);
    }

    @Transactional(readOnly = true)
    public ScheduleReadResponse getSchedule(long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();
        ScheduleDto dto = ScheduleDto.from(schedule);

        return ScheduleReadResponse.from(dto);
    }

    void isOwner(long nowLoginUserId, long scheduleOwnerId) {
        if (nowLoginUserId != scheduleOwnerId) {
            throw new IllegalArgumentException("현재 로그인한 사용자와 수정 및 삭제 하려는 대상의 소유자가 일치하지 않습니다.");
        }
    }
}
