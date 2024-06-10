package org.farpost.farpostapi2.enitities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.farpost.farpostapi2.dto.client_dto.ClientDto;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "clients")
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    @Schema(example = "Ivan")
    private String name;

    @Column(nullable = false, length = 300, unique = true)
    @Schema(example = "asdasdd45....")
    private String boobs;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    @JsonManagedReference
    private List<ClientTg> clientTgs;

    @ManyToOne
    @JoinColumn(name = "vps_id")
    @JsonBackReference
    @Schema(example = "1")
    private Vps vps;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    @JsonManagedReference
    private List<Ad> ads;

    public Client(ClientDto clientDto) {
        this.name = clientDto.getName();
        this.boobs = clientDto.getBoobs();
    }

}
