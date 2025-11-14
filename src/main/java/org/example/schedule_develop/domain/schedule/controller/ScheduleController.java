package org.example.schedule_develop.domain.schedule.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.common.model.SessionUser;
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
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // create
    @PostMapping
    public ResponseEntity<ScheduleCreateResponse> createSchedule(
        @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
        @Valid @RequestBody ScheduleCreateRequest request) {
        return ResponseEntity.ok(scheduleService.createSchedule(sessionUser.getUserId(), request));
    }


    // read
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleReadResponse> getSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.getSchedule(scheduleId));
    }

    // update
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> updateSchedule(
        @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, @PathVariable Long scheduleId,
        @RequestBody ScheduleUpdateRequest request) {
        return ResponseEntity.ok(scheduleService.updateSchedule(sessionUser.getUserId(), scheduleId, request));
    }

    // delete
    @GetMapping("/{scheduleId}/delete")
    public ResponseEntity<ScheduleDeleteResponse> deleteSchedule(
        @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
        @PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.deleteSchedule(sessionUser.getUserId(), scheduleId));
    }
}
