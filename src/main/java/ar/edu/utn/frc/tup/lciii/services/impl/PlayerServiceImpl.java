package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.PlayerJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerJpaRepository playerJpaRepository;



    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Player getPlayerById(Long id) {

        PlayerEntity playerEntity = playerJpaRepository.getReferenceById(id);
        if(Objects.isNull(playerEntity.getUserName())){
            throw new EntityNotFoundException();
        }
        Player player = modelMapper.map(playerEntity, Player.class);
        return player;
    }

    @Override
    public Player savePlayer(Player player) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByUserNameOrEmail(
                player.getUserName(), player.getEmail()
        );
        if(playerEntityOptional.isEmpty()){
            PlayerEntity playerEntity = modelMapper.map(player, PlayerEntity.class);
            PlayerEntity playerEntitySaved = playerJpaRepository.save(playerEntity);
            return modelMapper.map(playerEntitySaved, Player.class);
        } else {
            return null;
        }

    }

    @Override
    public Player getPlayerByUserNameAndPassword(String userName, String password) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByUserNameAndPassword(userName, password);
        if (playerEntityOptional.isPresent()){
            return modelMapper.map(playerEntityOptional.get(), Player.class);
        } else {
            throw new EntityNotFoundException("Username or password invalid!");
        }
    }

    @Override
    public Player getPlayerByEmailAndPassword(String email, String password) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByEmailAndPassword(email, password);
        if (playerEntityOptional.isPresent()){
            return modelMapper.map(playerEntityOptional.get(), Player.class);
        } else {
            throw new EntityNotFoundException("Email or password invalid!");

        }
    }

    @Override
    public Player getPlayerByUserNameOrEmailAndPassword(String identity, String password) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByUserNameOrEmailAndPassword(identity, password);
        if (playerEntityOptional.isPresent()){
            return modelMapper.map(playerEntityOptional.get(), Player.class);
        } else {
            throw new EntityNotFoundException("Some parameters are incorrect!");

        }
    }


}
