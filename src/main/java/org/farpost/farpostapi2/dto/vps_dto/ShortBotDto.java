package org.farpost.farpostapi2.dto.vps_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.farpost.farpostapi2.enitities.Bot;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class ShortBotDto {

    private Integer id;

    private String name;

    ShortBotDto (Bot bot){
        this.id = bot.getId();
        this.name = bot.getName();
    }

}
