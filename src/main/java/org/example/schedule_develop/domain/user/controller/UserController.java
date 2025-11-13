package org.example.schedule_develop.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.domain.user.model.request.UserCreateRequest;
import org.example.schedule_develop.domain.user.model.request.UserUpdateRequest;
import org.example.schedule_develop.domain.user.model.response.UserCreateResponse;
import org.example.schedule_develop.domain.user.model.response.UserDeleteResponse;
import org.example.schedule_develop.domain.user.model.response.UserReadResponse;
import org.example.schedule_develop.domain.user.model.response.UserUpdateResponse;
import org.example.schedule_develop.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserReadResponse> readUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping
    public ResponseEntity<UserCreateResponse> createUser(@RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserUpdateResponse> updateUser(@PathVariable Long userId,
        @RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

}
