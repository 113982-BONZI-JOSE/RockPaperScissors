package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.dtos.match.MatchDto;
import ar.edu.utn.frc.tup.lciii.dtos.play.PlayRequest;
import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.entities.MatchRpsEntity;
import ar.edu.utn.frc.tup.lciii.models.*;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.MatchEntityFactory;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.MatchJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    private static final Long APP_PLAYER_ID = 1000000L;

    @Autowired
    private MatchJpaRepository matchJpaRepository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlayStrategyFactory playStrategyFactory;

    @Override
    public List<Match> getMatchesByPlayer(Long playerId) {
        List<Match> matches = new ArrayList<>();
        Optional<List<MatchEntity>> optionalMatchEntities = matchJpaRepository.getAllByPlayerId(playerId);
        if (optionalMatchEntities.isPresent()){
            optionalMatchEntities.get().forEach(
                    //Agregue clasificación po factoria
                //me -> {matches.add(modelMapper.map(me, MatchFactory.createMatch(me.getGame().getCode()).getClass()));}
                me -> {matches.add(modelMapper.map(me, MatchFactory.getTypeOfMatch(me.getGame().getCode())));}
            );
//            for (MatchEntity me : optionalMatchEntities.get()){
//                matches.add(modelMapper.map(me, Match.class));
//            }
        }
        return matches;
    }

    @Override
    public Match createMatch(MatchDto matchDto) {
        Player player = playerService.getPlayerById(matchDto.getPlayerId());
        Game game = gameService.getGame(matchDto.getGameId());
        //Movi de lugar el match para usar la factoria. PREGUNTAR A JOSE SOBRE LA FACTORIA
//        Match match = MatchFactory.createMatch(game.getCode());
//        match.setPlayer(player);
//        match.setGame(game);
//        match.setCreatedDate(LocalDateTime.now());
//        match.setStatus(MatchStatus.STARTED);
        Match match = MatchFactory.createMatch(player, game);
        //MatchEntity matchEntity = matchJpaRepository.save(modelMapper.map(match, MatchEntity.class));
        MatchEntity matchEntity = matchJpaRepository.save(modelMapper.map(match, MatchEntityFactory.getTypeOfMatch(match)));
        return modelMapper.map(matchEntity, match.getClass());
    }

    @Override
    public Match getMatchById(Long id) {
        //MatchEntity me = matchJpaRepository.getReferenceById(id);
         //INFO: https://www.baeldung.com/hibernate-proxy-to-real-entity-object
         MatchEntity me = (MatchEntity) Hibernate.unproxy(matchJpaRepository.getReferenceById(id));
        //MatchEntity me = matchJpaRepository.getMatchById(id);
        if (me != null){
            Match match = modelMapper.map(me, MatchFactory.getTypeOfMatch(me.getGame().getCode()));
            return match;
        }else {
            throw new EntityNotFoundException();
        }
    }
    @Transactional
    @Override
    public Play play(Long matchId, PlayRequest playRequest) {
        Match match = this.getMatchById(matchId);
        if (match == null){
            throw new EntityNotFoundException();
        }
        if (match.getStatus() != MatchStatus.STARTED){
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("The match is %s", match.getStatus()));
        }
        Play play = PlayFactory.getPlayInstance(playRequest, match.getGame().getCode());
        PlayMatch playMatch = playStrategyFactory.getPlayStrategy(match.getGame().getCode());
        return playMatch.play(play, match);
    }
}
