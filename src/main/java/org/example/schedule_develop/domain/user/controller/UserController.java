package org.example.schedule_develop.domain.user.controller;

import static org.example.schedule_develop.common.exception.ErrorMessage.NOT_AUTHENTICATED;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.common.exception.CustomException;
import org.example.schedule_develop.common.model.SessionUser;
import org.example.schedule_develop.domain.user.model.request.LoginRequest;
import org.example.schedule_develop.domain.user.model.request.UserCreateRequest;
import org.example.schedule_develop.domain.user.model.request.UserUpdateRequest;
import org.example.schedule_develop.domain.user.model.response.UserCreateResponse;
import org.example.schedule_develop.domain.user.model.response.UserDeleteResponse;
import org.example.schedule_develop.domain.user.model.response.UserReadResponse;
import org.example.schedule_develop.domain.user.model.response.UserUpdateResponse;
import org.example.schedule_develop.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

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
    public ResponseEntity<UserCreateResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserUpdateResponse> updateUser(@PathVariable Long userId,
        @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
        @RequestBody UserUpdateRequest request) {

        checkLogin(sessionUser);

        return ResponseEntity.ok(userService.updateUser(sessionUser.getUserId(), userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable Long userId,
        @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser) {

        checkLogin(sessionUser);

        return ResponseEntity.ok(userService.deleteUser(sessionUser.getUserId(), userId));
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request, HttpSession session) {
        SessionUser sessionUser = userService.login(request);
        session.setAttribute("loginUser", sessionUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser
        , HttpSession session) {

        if (sessionUser == null) {
            return ResponseEntity.badRequest().build();
        }

        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private void checkLogin(SessionUser sessionUser) {
        if (sessionUser == null) {
            throw new CustomException(NOT_AUTHENTICATED);
        }
    }

}
