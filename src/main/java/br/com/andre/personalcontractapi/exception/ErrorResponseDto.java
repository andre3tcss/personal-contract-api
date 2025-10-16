package br.com.andre.personalcontractapi.exception;

import java.time.Instant;
import java.util.List;

public record ErrorResponseDto(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path,
        List<ValidationError> errors
) {
    public record ValidationError(String field, String message) {}
}