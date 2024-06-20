package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.dtos.match.MatchDto;
import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.entities.MatchRpsEntity;
import ar.edu.utn.frc.tup.lciii.models.Game;
import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.MatchStatus;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.MatchEntityFactory;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.MatchJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.GameService;
import ar.edu.utn.frc.tup.lciii.services.MatchFactory;
import ar.edu.utn.frc.tup.lciii.services.MatchService;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchJpaRepository matchJpaRepository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private ModelMapper modelMapper;

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
}
