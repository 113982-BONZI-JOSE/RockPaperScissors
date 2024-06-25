package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.match.MatchDto;
import ar.edu.utn.frc.tup.lciii.dtos.play.PlayRequest;
import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.Play;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {

    List<Match> getMatchesByPlayer(Long playerId);

    Match createMatch(MatchDto matchDto);

    Match getMatchById(Long id);

    Play play(Long matchId, PlayRequest play);
}
