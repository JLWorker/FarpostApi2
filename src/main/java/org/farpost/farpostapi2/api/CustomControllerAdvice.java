package org.farpost.farpostapi2.api;


import jakarta.servlet.http.HttpServletRequest;
import org.farpost.farpostapi2.dto.exceptions_dto.RemoteRequestExceptionDto;
import org.farpost.farpostapi2.dto.exceptions_dto.SimpleResponseExceptionDto;
import org.farpost.farpostapi2.dto.exceptions_dto.ValidationExceptionDto;
import org.farpost.farpostapi2.dto.exceptions_dto.ValidationResponseDto;
import org.farpost.farpostapi2.exceptions.security.BadAuthSwaggerRequestException;
import org.farpost.farpostapi2.exceptions.security.SwaggerAccessExpiredException;
import org.farpost.farpostapi2.exceptions.ssh.SshConnectionException;
import org.farpost.farpostapi2.exceptions.system.*;
import org.farpost.farpostapi2.exceptions.users.InvalidUserCredentials;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationResponseDto validationExceptionHandler(MethodArgumentNotValidException exception){
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

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public SimpleResponseExceptionDto remoteRequestExceptionHandler(ConstraintViolationException exception, HttpServletRequest request){
        return new SimpleResponseExceptionDto(request.getServletPath(), HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.value(),
                exception.getMessage());
    }

    @ExceptionHandler(value = InvalidUserCredentials.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponseExceptionDto invalidUserExceptionHandler(InvalidUserCredentials exception, HttpServletRequest request){
        return new SimpleResponseExceptionDto(request.getServletPath(), HttpStatus.UNAUTHORIZED.name(), HttpStatus.UNAUTHORIZED.value(),
                exception.getMessage());
    }

    @ExceptionHandler(value = ElementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SimpleResponseExceptionDto elemNotFoundExceptionHandler(ElementNotFoundException exception, HttpServletRequest request){
        return new SimpleResponseExceptionDto(request.getServletPath(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
                exception.getMessage());
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SimpleResponseExceptionDto elemNotFoundExceptionHandler(BadRequestException exception, HttpServletRequest request){
        return new SimpleResponseExceptionDto(request.getServletPath(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(),
                exception.getMessage());
    }

    @ExceptionHandler(value = ElementAlreadyExist.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SimpleResponseExceptionDto elemAlreadyExistExceptionHandler(ElementAlreadyExist exception, HttpServletRequest request){
        return new SimpleResponseExceptionDto(request.getServletPath(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(),
                exception.getMessage());
    }

    @ExceptionHandler(value = SshConnectionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SimpleResponseExceptionDto sshExceptionHandler(SshConnectionException exception, HttpServletRequest request){
        return new SimpleResponseExceptionDto(request.getServletPath(), HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage());
    }


}
