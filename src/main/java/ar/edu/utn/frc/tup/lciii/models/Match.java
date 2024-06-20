package ar.edu.utn.frc.tup.lciii.models;

import ar.edu.utn.frc.tup.lciii.models.rps.MatchRps;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MatchRps.class),
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Match {

    private Long id;
    private Game game;
    private Player player;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdDate;
    private MatchStatus status;
}
