package org.farpost.farpostapi2.dto.timeweb_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.farpost.farpostapi2.dto.client_dto.ClientVpsDto;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Setter
@NoArgsConstructor
public class TimewebVpsDto {

    private Integer presetId;
    private String imageId;
    @JsonProperty(value = "is_ddos_guard")
    private boolean isDdosGuard;
    private String name;
    private String avatarId;
    private String comment;
    private String availabilityZone;
    private String cloudInit;

    public TimewebVpsDto(ClientVpsDto clientVpsDto){
        this.name = clientVpsDto.getVpsName();
        this.isDdosGuard = clientVpsDto.isDdosGuard();
        this.presetId = clientVpsDto.getPresetId();
        this.availabilityZone = clientVpsDto.getAvailabilityZone();
        this.imageId = clientVpsDto.getImageId();
        this.avatarId = "avatar";
        this.comment = "comment";
        this.cloudInit = "#cloud-config";
    }

}
