package com.backend.sakila.exceptions;

import com.backend.sakila.api.model.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * Handle AppException.
     *
     * @param e - AppException
     * @return The error response.
     */
    @ExceptionHandler(AppException.class)
    public ResponseEntity<Error> handleAppException(AppException e) {
        var code = e.getErrorCode();
        return new ResponseEntity<>(
                code.getErrorHandler().apply(e),
                code.getHttpStatus()
        );
    }
}
