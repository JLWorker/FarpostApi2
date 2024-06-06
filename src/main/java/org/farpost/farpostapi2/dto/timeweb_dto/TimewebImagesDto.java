package org.farpost.farpostapi2.dto.timeweb_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimewebImagesDto {

    private String id;
    private String name;
    private Long size;
    private Long virtualSize;
    private String createdAt;

}
