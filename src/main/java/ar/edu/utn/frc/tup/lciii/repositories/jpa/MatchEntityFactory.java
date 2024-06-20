package ar.edu.utn.frc.tup.lciii.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.entities.MatchRpsEntity;
import ar.edu.utn.frc.tup.lciii.models.Match;

public class MatchEntityFactory {

    public static Class<? extends MatchEntity> getTypeOfMatch(Match match){
        switch (match.getGame().getCode()){
            case "RPS":
                return MatchRpsEntity.class;
            default:
                return MatchRpsEntity.class;
        }
    }
}
