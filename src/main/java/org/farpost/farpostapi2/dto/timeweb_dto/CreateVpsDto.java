package org.farpost.farpostapi2.dto.timeweb_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.farpost.farpostapi2.dto.client_dto.ClientVpsDto;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class CreateVpsDto {

    private Integer presetId;
    private Integer osId;
    private boolean isDdosGuard;
    private String name;
    private String avatarId;
    private String comment;
    private String availabilityZone;
    private String cloudInit;

    public CreateVpsDto(ClientVpsDto clientVpsDto){
        this.osId = clientVpsDto.getOsId();
        this.name = clientVpsDto.getVpsName();
        this.isDdosGuard = clientVpsDto.isDdosGuard();
        this.presetId = clientVpsDto.getPresetId();
        this.availabilityZone = clientVpsDto.getAvailabilityZone();
        this.avatarId = "avatar";
        this.comment = "comment";
        this.cloudInit = "#cloud-config";
    }

}
