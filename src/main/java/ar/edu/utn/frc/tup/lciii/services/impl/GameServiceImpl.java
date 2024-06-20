package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.GameEntity;
import ar.edu.utn.frc.tup.lciii.models.Game;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.GameJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameJpaRepository gameJpaRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Game getGame(Long gameId) {
        GameEntity gameEntity = gameJpaRepository.getReferenceById(gameId);
        return modelMapper.map(gameEntity, Game.class);
    }
}
