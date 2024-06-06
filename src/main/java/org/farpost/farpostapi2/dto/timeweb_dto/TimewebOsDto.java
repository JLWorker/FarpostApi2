package org.farpost.farpostapi2.dto.timeweb_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimewebOsDto {

    private Integer id;
    private String family;
    private String name;
    private String version;
    private String versionCodename;
    private String description;

}
