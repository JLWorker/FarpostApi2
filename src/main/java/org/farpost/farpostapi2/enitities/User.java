package org.farpost.farpostapi2.enitities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "users")
@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 100)
    private String username;

    @Column(length = 20)
    private String role;

    @Column(length = 300)
    private String password;

    private boolean active;

}
