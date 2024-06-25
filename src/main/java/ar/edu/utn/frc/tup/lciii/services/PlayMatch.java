package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.Play;
import org.springframework.stereotype.Service;

/**
 *
 * @param <P> The Play response.
 * @param <M> The Match to play.
 */
@Service
public interface PlayMatch<P extends Play, M extends Match> {

    P play(P play, M match);
}
