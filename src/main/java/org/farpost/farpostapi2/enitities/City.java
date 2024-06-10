package org.farpost.farpostapi2.enitities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.farpost.farpostapi2.dto.city_viewdir_dto.ViewDrCityDto;

@Entity
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1")
    private Integer id;

    @Column(length = 250, nullable = false)
    @Schema(example = "vladivostok")
    private String name;

    @Column(name = "farpost_id", nullable = false, unique = true)
    @Schema(example = "2")
    private Integer farpostId;

    public City(String name, Integer farpost_id) {
        this.name = name;
        this.farpostId = farpost_id;
    }

    public City(ViewDrCityDto viewDrCityDto){
        this.name = viewDrCityDto.getName();
        this.farpostId = viewDrCityDto.getFarpostId();
    }
}
