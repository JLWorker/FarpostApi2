package org.farpost.farpostapi2.api;


import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.dto.exceptions_dto.ValidationExceptionDto;
import org.farpost.farpostapi2.dto.exceptions_dto.ValidationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

}
