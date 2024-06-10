package org.farpost.farpostapi2.dto.farpost_ad_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class FarpostAdDto {

    @NotNull(message = "Ad id cannot be null")
    private Integer adId;
    private Integer viewDirId;
    private Integer price;



}
