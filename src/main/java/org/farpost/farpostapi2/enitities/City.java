package org.farpost.farpostapi2.enitities;

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
    private Integer id;

    @Column(length = 250, nullable = false)
    private String name;

    @Column(name = "farpost_id", nullable = false, unique = true)
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
