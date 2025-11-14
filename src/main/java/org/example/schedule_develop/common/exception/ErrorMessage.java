package org.example.schedule_develop.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    NOT_FOUND_SCHEDULE(HttpStatus.NOT_FOUND, "해당 일정을 찾을 수 없습니다."),
    NOT_VALID_LOGIN(HttpStatus.UNAUTHORIZED, "아이디나 비밀변호가 일치 하지 않습니다."),
    NOT_VALID_OWNER(HttpStatus.FORBIDDEN, "소유자가 아닙니다."),
    ;

    private final HttpStatus status;
    private final String message;

}
