package com.library.exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private record ErrorResponse(
            Instant timestamp,
            int status,
            String error,
            String message,
            String path
    ) {}

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            NotFoundException ex,
            WebRequest request
    ) {
        log.warn("NotFoundException at {}: {}", request.getDescription(false), ex.getMessage());
        return buildResponse(HttpStatus.NOT_FOUND, "Resource not found", ex.getMessage(), request);
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<ErrorResponse> handleInvalidField(
            InvalidFieldException ex,
            WebRequest request
    ) {
        log.warn("InvalidFieldException at {}: {}", request.getDescription(false), ex.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, "Invalid field", ex.getMessage(), request);
    }

    @ExceptionHandler(BookUnavailableException.class)
    public ResponseEntity<ErrorResponse> handleBookUnavailable(
            BookUnavailableException ex,
            WebRequest request
    ) {
        log.info("BookUnavailableException at {}: {}", request.getDescription(false), ex.getMessage());
        return buildResponse(HttpStatus.CONFLICT, "Book unavailable", ex.getMessage(), request);
    }

    @ExceptionHandler(UserLoanLimitException.class)
    public ResponseEntity<ErrorResponse> handleUserLoanLimit(
            UserLoanLimitException ex,
            WebRequest request
    ) {
        log.info("UserLoanLimitException at {}: {}", request.getDescription(false), ex.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, "Loan limit exceeded", ex.getMessage(), request);
    }

    @ExceptionHandler(UserNotEligibleForLoanException.class)
    public ResponseEntity<ErrorResponse> handleUserNotEligible(
            UserNotEligibleForLoanException ex,
            WebRequest request
    ) {
        log.warn("UserNotEligibleForLoanException at {}: {}", request.getDescription(false), ex.getMessage());
        return buildResponse(HttpStatus.FORBIDDEN, "User not eligible", ex.getMessage(), request);
    }

    @ExceptionHandler(IllegalDeletionException.class)
    public ResponseEntity<ErrorResponse> handleIllegalDeletion(
            IllegalDeletionException ex,
            WebRequest request
    ) {
        log.warn("IllegalDeletionException at {}: {}", request.getDescription(false), ex.getMessage());
        return buildResponse(HttpStatus.CONFLICT, "Illegal deletion", ex.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(
            MethodArgumentNotValidException ex,
            WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        log.warn("Validation error at {}: {}", request.getDescription(false), errors);

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Validation failed");
        body.put("message", errors);
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception ex,
            WebRequest request
    ) {
        log.error("Unexpected exception at {}: {}", request.getDescription(false), ex.getMessage(), ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex.getMessage(), request);
    }

    private ResponseEntity<ErrorResponse> buildResponse(
            HttpStatus status,
            String error,
            String message,
            WebRequest request
    ) {
        String path = request.getDescription(false).replace("uri=", "");
        ErrorResponse body = new ErrorResponse(
                Instant.now(),
                status.value(),
                error,
                message,
                path
        );
        return new ResponseEntity<>(body, status);
    }
}
