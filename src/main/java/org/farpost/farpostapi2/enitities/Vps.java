package org.farpost.farpostapi2.enitities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "vps")
public class Vps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer timewebId;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 40, nullable = false)
    private String ip;

    @Column(nullable = false, length = 300, unique = true)
    private String ring;

    @OneToMany(mappedBy = "vps", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Client> clients;

    @OneToMany(mappedBy = "vps", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bot> bots;


}
