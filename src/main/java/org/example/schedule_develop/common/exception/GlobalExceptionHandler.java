package org.example.schedule_develop.common.exception;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.example.schedule_develop.common.model.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        log.error("MethodArgumentNotValidException 발생 {} : ", e.getMessage());

        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();

        return ResponseEntity
            .status(e.getStatusCode())
            .body(new ErrorResponse(e.getStatusCode(), message));
    }


    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("CustomException 발생 {} : ", e.getMessage());

        return ResponseEntity
            .status(e.getErrorMessage().getStatus())
            .body(new ErrorResponse(e.getErrorMessage()));
    }

}
