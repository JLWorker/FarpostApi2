package org.farpost.farpostapi2.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.farpost.farpostapi2.configs.filters.CustomExceptionTranslationFilter;
import org.farpost.farpostapi2.configs.filters.SwaggerAuthFilter;
import org.farpost.farpostapi2.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig{

    @Autowired
    private JwtService jwtService;

    private final List<String> permitAllMatchers = List.of("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/admin/**");
    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .authorizeHttpRequests(managerRequestRegistry ->
                    managerRequestRegistry
                      .requestMatchers(permitAllMatchers.toArray(new String[0])).permitAll()
                      .anyRequest().hasRole("ADMIN"))
                .addFilterBefore(new SwaggerAuthFilter(jwtService, permitAllMatchers), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CustomExceptionTranslationFilter(new ObjectMapper()), SwaggerAuthFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

//    @Bean
//    public FilterRegistrationBean<SwaggerAuthFilter> swaggerAuthFilterFilterRegistrationBean(SwaggerAuthFilter swaggerAuthFilter) {
//        FilterRegistrationBean<SwaggerAuthFilter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(swaggerAuthFilter);
//        registration.setEnabled(false);
//        return registration;
//    }

}
