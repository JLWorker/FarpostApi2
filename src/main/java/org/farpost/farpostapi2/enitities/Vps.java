package org.farpost.farpostapi2.enitities;

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

    @Column(length = 100, nullable = false)
    private String os;

    @OneToMany(mappedBy = "vps", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Client> clients;

    @OneToMany(mappedBy = "vps", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bot> bots;




}
