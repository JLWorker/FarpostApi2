package org.farpost.farpostapi2.enitities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.farpost.farpostapi2.dto.bot_dto.BotDto;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "bots")
@NoArgsConstructor
public class Bot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "vps_id", nullable = false)
    @JsonBackReference
    private Vps vps;

    @OneToMany(mappedBy = "bot", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ad> ad;

    public Bot(BotDto botDto, Vps vps){
        this.name = botDto.getName();
        this.vps = vps;
    }

}
