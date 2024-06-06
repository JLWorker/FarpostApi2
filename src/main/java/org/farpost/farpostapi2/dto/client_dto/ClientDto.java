package org.farpost.farpostapi2.dto.client_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@AllArgsConstructor
public class ClientDto {

    @NotNull(message = "Name cannot be null")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]{3,50}$", message = "Invalid name parameter")
    private String name;

    @NotEmpty(message = "Boobs cannot be empty")
    @NotNull(message = "Boobs cannot be null")
    private String boobs;

    private Integer vpsId;

    @Valid
    @JsonProperty("client_vps")
    private ClientVpsDto clientVpsDto;

//    @Valid
//    @JsonProperty("client_tgs")
//    private List<ClientTgDto> listTgId;

}
