package org.farpost.farpostapi2.enitities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebVpsResponseDto;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "vps")
@NoArgsConstructor
public class Vps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer timewebId;

    @Column(length = 200, nullable = false, unique = true)
    private String name;

    @Column(length = 40)
    private String ipv4;

    @Column(length = 40, nullable = false)
    private String ipv6;

    @Column(length = 300, unique = true)
    private String ring;

    private Boolean isActive;

    @OneToMany(mappedBy = "vps", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Client> clients;

    @OneToMany(mappedBy = "vps", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Bot> bots;

    public Vps(String name, TimewebVpsResponseDto timewebVpsResponseDto) {
        this.timewebId = timewebVpsResponseDto.getVpsTimewebId();
        this.name = name;
        this.ipv4 = timewebVpsResponseDto.getIpv4();
        this.ipv6 = timewebVpsResponseDto.getIpv6();
        this.isActive = false;
    }

}
