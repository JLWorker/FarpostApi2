package org.farpost.farpostapi2.enitities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.farpost.farpostapi2.dto.city_viewdir_dto.ViewDrCityDto;

@Table(name = "view_dirs")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ViewDir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "farpost_id", nullable = false, unique = true)
    private Integer farpostId;

    public ViewDir(String name, Integer farpostId) {
        this.name = name;
        this.farpostId = farpostId;
    }

    public ViewDir(ViewDrCityDto viewDrCityDto) {
        this.name = viewDrCityDto.getName();
        this.farpostId = viewDrCityDto.getFarpostId();
    }

}
