package org.farpost.farpostapi2.api;


import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.dto.exceptions_dto.RemoteRequestExceptionDto;
import org.farpost.farpostapi2.dto.exceptions_dto.SimpleResponseExceptionDto;
import org.farpost.farpostapi2.dto.exceptions_dto.ValidationExceptionDto;
import org.farpost.farpostapi2.dto.exceptions_dto.ValidationResponseDto;
import org.farpost.farpostapi2.exceptions.RemoteInvalidRequestException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class CustomControllerAdvice {

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationResponseDto validationExceptionHandler(BindException exception){
        List<ValidationExceptionDto> exceptionDtos = new ArrayList<>();
        exception.getAllErrors().forEach(objectError -> {
            ValidationExceptionDto validationExceptionDto = new ValidationExceptionDto(String.format("Exception throw in class - %s", objectError.getObjectName()),
                    objectError.getDefaultMessage(), objectError.getClass().getName());
            exceptionDtos.add(validationExceptionDto);
        });
        return new ValidationResponseDto(exceptionDtos);
    }

    @ExceptionHandler(value = RemoteInvalidRequestException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RemoteRequestExceptionDto remoteRequestExceptionHandler(RemoteInvalidRequestException exception){
        return new RemoteRequestExceptionDto(exception);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public SimpleResponseExceptionDto remoteRequestExceptionHandler(ConstraintViolationException exception, HttpServletRequest request){
        return new SimpleResponseExceptionDto(request.getServletPath(), HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.value(),
                exception.getMessage());
    }



}
