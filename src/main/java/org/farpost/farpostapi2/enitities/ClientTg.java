package org.farpost.farpostapi2.enitities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client_tg")
@Getter
@Setter
public class ClientTg {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer tgId;

    @Column(length = 150, nullable = false)
    private String username;


}
