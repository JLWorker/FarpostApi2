package org.farpost.farpostapi2.configs.filters;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.exceptions_dto.SimpleResponseExceptionDto;
import org.farpost.farpostapi2.exceptions.security.BadAuthSwaggerRequestException;
import org.farpost.farpostapi2.exceptions.security.SwaggerAccessExpiredException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
public class CustomExceptionTranslationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        }
        catch (RuntimeException exception){
            if (exception instanceof SwaggerAccessExpiredException){
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.getWriter().write(objectConverter(responseCreator(HttpStatus.UNAUTHORIZED, request, exception)));
            }
            else if (exception instanceof BadAuthSwaggerRequestException){
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType("application/json");
                response.getWriter().write(objectConverter(responseCreator(HttpStatus.FORBIDDEN, request, exception)));
            }
        }
    }

    private String objectConverter(SimpleResponseExceptionDto responseExceptionDto){
        try {
            return objectMapper.writeValueAsString(responseExceptionDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private SimpleResponseExceptionDto responseCreator(HttpStatus httpStatus, HttpServletRequest request, RuntimeException exception){
        return new SimpleResponseExceptionDto(request.getServletPath(), httpStatus.name(), httpStatus.value(),
                exception.getMessage());
    }

}
