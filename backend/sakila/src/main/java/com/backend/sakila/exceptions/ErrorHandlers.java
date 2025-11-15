package com.backend.sakila.exceptions;

import com.backend.sakila.api.model.Error;

public class ErrorHandlers {
    private ErrorHandlers() {
        throw new UnsupportedOperationException("Cannot instantiate utility class");
    }

    /**
     * Handle not found exception.
     *
     * @param e - AppException
     * @return Error
     */
    public static Error handleNotFound(AppException e) {
        var code = e.getErrorCode();
        return new Error()
                .code(code.name())
                .message(code.getMessage());
    }
}