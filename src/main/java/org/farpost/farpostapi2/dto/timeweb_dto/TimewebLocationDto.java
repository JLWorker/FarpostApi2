package org.farpost.farpostapi2.dto.timeweb_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.List;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TimewebLocationDto {

    private String location;
    private String locationCode;
    private List<String> availabilityZones;

}
