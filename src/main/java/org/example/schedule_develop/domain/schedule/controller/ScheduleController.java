package org.example.schedule_develop.domain.schedule.controller;

import static org.example.schedule_develop.common.exception.ErrorMessage.NOT_AUTHENTICATED;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.common.exception.CustomException;
import org.example.schedule_develop.common.model.SessionUser;
import org.example.schedule_develop.domain.schedule.model.requset.ScheduleCreateRequest;
import org.example.schedule_develop.domain.schedule.model.requset.ScheduleUpdateRequest;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleCreateResponse;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleDeleteResponse;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleReadPageResponse;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleReadResponse;
import org.example.schedule_develop.domain.schedule.model.response.ScheduleUpdateResponse;
import org.example.schedule_develop.domain.schedule.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleCreateResponse> createSchedule(
        @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
        @Valid @RequestBody ScheduleCreateRequest request) {

        checkLogin(sessionUser);

        return ResponseEntity.ok(scheduleService.createSchedule(sessionUser.getUserId(), request));
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleReadResponse> getSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.getSchedule(scheduleId));
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> updateSchedule(
        @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, @PathVariable Long scheduleId,
        @RequestBody ScheduleUpdateRequest request) {

        checkLogin(sessionUser);

        return ResponseEntity.ok(scheduleService.updateSchedule(sessionUser.getUserId(), scheduleId, request));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDeleteResponse> deleteSchedule(
        @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
        @PathVariable Long scheduleId) {

        checkLogin(sessionUser);

        return ResponseEntity.ok(scheduleService.deleteSchedule(sessionUser.getUserId(), scheduleId));
    }

    private void checkLogin(SessionUser sessionUser) {
        if (sessionUser == null) {
            throw new CustomException(NOT_AUTHENTICATED);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ScheduleReadPageResponse>> getSchedulePage(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        return ResponseEntity.ok(scheduleService.getSchedulePage(pageable));
    }
}
