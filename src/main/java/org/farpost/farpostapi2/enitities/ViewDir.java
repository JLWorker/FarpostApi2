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

    @Column
    private String name;

    @Column(name = "farpost_id")
    private Integer farpost_id;

    public ViewDir(String name, Integer farpost_id) {
        this.name = name;
        this.farpost_id = farpost_id;
    }

    public ViewDir(ViewDrCityDto viewDrCityDto) {
        this.name = viewDrCityDto.getName();
        this.farpost_id = viewDrCityDto.getFarpostId();
    }

}
