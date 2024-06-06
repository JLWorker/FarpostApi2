package org.farpost.farpostapi2.dto.timeweb_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
public class TimewebPresetDto {

    private Integer id;
    private String location;
    private Integer cpu;
    private Float cpuFrequency;
    private Integer ram;
    private Long disk;
    private String discType;

}
