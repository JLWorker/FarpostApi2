package org.farpost.farpostapi2.enitities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.farpost.farpostapi2.dto.client_dto.ClientTgDto;

import java.util.Objects;

@Entity
@Table(name = "client_tg")
@Getter
@Setter
@NoArgsConstructor
public class ClientTg {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tg_id", nullable = false, unique = true)
    private Integer tgId;

    @Column(length = 150, nullable = false)
    private String username;

    @ManyToOne()
    @JoinColumn(name = "client_id", nullable = false)
    @JsonBackReference
    private Client client;

    public ClientTg(ClientTgDto clientTgDto) {
        this.tgId = clientTgDto.getTgId();
        this.username = clientTgDto.getUsername();
    }

    public ClientTg(ClientTgDto clientTgDto, Client client) {
        this.tgId = clientTgDto.getTgId();
        this.username = clientTgDto.getUsername();
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientTg clientTg = (ClientTg) o;
        return Objects.equals(tgId, clientTg.tgId) && Objects.equals(username, clientTg.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tgId, username);
    }
}
