package org.farpost.farpostapi2.dto.timeweb_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVpsResponseDto {

    private Integer vpsTimewebId;
    private String ipv6;
    private String ipv4;
}
