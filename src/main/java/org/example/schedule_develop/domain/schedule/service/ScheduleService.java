package org.example.schedule_develop.domain.schedule.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.common.entity.Schedule;
import org.example.schedule_develop.domain.schedule.model.dto.ScheduleDto;
import org.example.schedule_develop.domain.schedule.model.requset.ScheduleCreateRequest;
import org.example.schedule_develop.domain.schedule.model.requset.ScheduleUpdateRequest;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleCreateResponse;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleDeletedResponse;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleReadResponse;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleUpdateResponse;
import org.example.schedule_develop.domain.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleCreateResponse createSchedule(ScheduleCreateRequest request) {

        Schedule schedule = new Schedule(request.getWriter(), request.getTitle(), request.getContent());
        scheduleRepository.save(schedule);
        ScheduleDto dto = ScheduleDto.from(schedule);

        return ScheduleCreateResponse.from(dto);
    }

    public ScheduleUpdateResponse updateSchedule(long id, ScheduleUpdateRequest request) {

        Schedule schedule = scheduleRepository.findById(id).orElseThrow();
        schedule.update(request);
        scheduleRepository.save(schedule);
        ScheduleDto dto = ScheduleDto.from(schedule);

        return ScheduleUpdateResponse.from(dto);
    }

    public ScheduleDeletedResponse deleteSchedule(long id) {

        Schedule schedule = scheduleRepository.findById(id).orElseThrow();
        scheduleRepository.delete(schedule);
        ScheduleDto dto = ScheduleDto.from(schedule);

        return ScheduleDeletedResponse.from(dto);
    }

    public ScheduleReadResponse getSchedule(long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow();
        ScheduleDto dto = ScheduleDto.from(schedule);

        return ScheduleReadResponse.from(dto);
    }
}
