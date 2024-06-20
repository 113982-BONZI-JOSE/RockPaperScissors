package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.Game;
import org.springframework.stereotype.Service;

@Service
public interface GameService {

    Game getGame(Long gameId);
}
