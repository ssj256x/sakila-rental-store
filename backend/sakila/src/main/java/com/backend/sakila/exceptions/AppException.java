package com.backend.sakila.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AppException extends RuntimeException {
    private final ErrorCodes errorCodes;
    private final Object details;

    public AppException(ErrorCodes errorCodes) {
        this.errorCodes = errorCodes;
        this.details = null;
    }
}
