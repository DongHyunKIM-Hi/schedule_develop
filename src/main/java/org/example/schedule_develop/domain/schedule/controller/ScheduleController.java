package org.example.schedule_develop.domain.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.domain.schedule.model.requset.ScheduleCreateRequest;
import org.example.schedule_develop.domain.schedule.model.requset.ScheduleUpdateRequest;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleCreateResponse;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleDeleteResponse;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleReadResponse;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleUpdateResponse;
import org.example.schedule_develop.domain.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // create
    @PostMapping("/{userId}")
    public ResponseEntity<ScheduleCreateResponse> createSchedule(@PathVariable long userId,
        @RequestBody ScheduleCreateRequest request) {
        return ResponseEntity.ok(scheduleService.createSchedule(userId, request));
    }


    // read
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleReadResponse> getSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.getSchedule(scheduleId));
    }

    // update
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> updateSchedule(@PathVariable Long scheduleId,
        @RequestBody ScheduleUpdateRequest request) {
        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, request));
    }

    // delete
    @GetMapping("/{scheduleId}/delete")
    public ResponseEntity<ScheduleDeleteResponse> deleteSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.deleteSchedule(scheduleId));
    }
}
