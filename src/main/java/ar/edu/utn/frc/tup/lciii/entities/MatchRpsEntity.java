package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "matchRps")
@Data
@AllArgsConstructor
public class MatchRpsEntity extends MatchEntity {

    private Long id;

    private Integer numberOfPlays;

    private Integer remainderPlays;

    private  Integer player1Score;

    private  Integer player2Score;

    @OneToMany(mappedBy = "matchRps")
    private List<PlayRpsEntity> plays;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private PlayerEntity winner;
}
