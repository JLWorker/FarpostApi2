package org.farpost.farpostapi2.dto.exceptions_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SimpleResponseExceptionDto {

        @JsonProperty
        private String instant;

        @JsonProperty
        private String path;

        @JsonProperty
        private String error;

        @JsonProperty
        private Integer status;

        @JsonProperty
        private String message;

        public SimpleResponseExceptionDto(String path, String error, Integer status, String message) {
            this.instant = String.valueOf(LocalDateTime.now());
            this.path = path;
            this.error = error;
            this.status = status;
            this.message = message;
        }

        public SimpleResponseExceptionDto(String message) {
            this.message = message;
        }


}
