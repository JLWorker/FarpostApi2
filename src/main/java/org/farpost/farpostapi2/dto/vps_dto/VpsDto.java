package org.farpost.farpostapi2.dto.vps_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.farpost.farpostapi2.dto.client_dto.ClientDto;
import org.farpost.farpostapi2.dto.client_dto.ClientTgDto;
import org.farpost.farpostapi2.enitities.Bot;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.enitities.Vps;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@AllArgsConstructor
public class VpsDto {

    private Integer id;

    @Schema(example = "24234")
    private Integer timewebId;

    @Schema(example = "Test1")
    private String name;

    @Schema(example = "32.23.22.12")
    private String ipv4;

    @Schema(example = "2a03:6f01:1:2::8162")
    private String ipv6;

    @Schema(example = "sadadshf455...")
    private String ring;

    private List<ShortClientDto> clients;

    private List<ShortBotDto> bots;

    public VpsDto(Vps vps){
        this.id = vps.getId();
        this.name = vps.getName();
        this.timewebId = vps.getTimewebId();
        this.ipv4 = vps.getIpv4();
        this.ipv6 = vps.getIpv6();
        this.ring = vps.getRing();
        this.clients = vps.getClients().stream().map(ShortClientDto::new).toList();
        this.bots = vps.getBots().stream().map(ShortBotDto::new).toList();
    }

}
