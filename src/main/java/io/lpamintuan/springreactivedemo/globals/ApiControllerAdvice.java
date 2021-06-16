package io.lpamintuan.springreactivedemo.globals;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ApiError> NotFoundExceptionHandler(EntityNotFoundException e) {
        return Mono.just(new ApiError(e.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    
}
