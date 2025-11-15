package com.backend.sakila.exceptions;

import com.backend.sakila.api.model.Error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum ErrorCodes {
    DATA_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "Requested data is not found",
            ErrorHandlers::handleNotFound
    );

    private final HttpStatus httpStatus;
    private final String message;
    private final Function<AppException, Error> errorHandler;
}
