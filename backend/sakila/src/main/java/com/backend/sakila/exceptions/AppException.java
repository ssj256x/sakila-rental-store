package com.backend.sakila.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AppException extends RuntimeException {
    private final ErrorCodes errorCode;
    private final Object details;

    public AppException(ErrorCodes errorCode) {
        this.errorCode = errorCode;
        this.details = null;
    }
}
