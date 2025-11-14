package org.example.schedule_develop.common.model.response;

import lombok.Getter;
import org.example.schedule_develop.common.exception.ErrorMessage;
import org.springframework.http.HttpStatusCode;

@Getter
public class ErrorResponse {

    private final int status;
    private final String code;
    private final String message;

    public ErrorResponse(ErrorMessage errorCode) {
        this.status = errorCode.getStatus().value();
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
    }

    public ErrorResponse(HttpStatusCode statusCode, String message) {
        this.status = statusCode.value();
        this.code = statusCode.toString();
        this.message = message;
    }

}
