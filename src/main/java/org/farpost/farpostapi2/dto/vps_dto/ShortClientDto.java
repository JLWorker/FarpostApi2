package org.farpost.farpostapi2.dto.vps_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.farpost.farpostapi2.enitities.Client;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@NoArgsConstructor
public class ShortClientDto {

    private String name;

    private String boobs;

    private Integer id;

    public ShortClientDto (Client client){
        this.id = client.getId();
        this.boobs = client.getBoobs();
        this.name = client.getName();
    }

}
