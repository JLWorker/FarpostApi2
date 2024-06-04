package org.farpost.farpostapi2.configs.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.configs.SpringSecurityConfig;
import org.farpost.farpostapi2.exceptions.security.BadAuthSwaggerRequestException;
import org.farpost.farpostapi2.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class SwaggerAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final List<String> permitAllMatchers;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean matches = permitAllMatchers.stream().map(el -> el.replace("/**", "")).anyMatch(matcher -> request.getRequestURI().contains(matcher));
        if (!matches) {
            String accessToken = request.getHeader("Authorization");
            if (accessToken == null || accessToken.isBlank() || !accessToken.startsWith("Bearer"))
                throw new BadAuthSwaggerRequestException();
            else {
                Authentication authentication = jwtService.parseSwaggerToken(accessToken.substring(7));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }



}
