package com.projectSam.projects.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

public class UrlShortnerExceptionHandler {

    @RestControllerAdvice
    public class UrlShortenerExceptionHandler {

        @ExceptionHandler(UrlNotFoundException.class)
        public ResponseEntity<UrlShortenerError> handleUrlNotFound(UrlNotFoundException ex) {
            UrlShortenerError errorResponse = UrlShortenerError.builder()
                    .timestamp(LocalDateTime.now())
                    .code(HttpStatus.NOT_FOUND.value())
                    .status(HttpStatus.NOT_FOUND.name())
                    .errors(List.of(ex.getMessage()))
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
