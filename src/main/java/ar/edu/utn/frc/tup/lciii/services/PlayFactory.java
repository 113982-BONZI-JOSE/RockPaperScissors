package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.play.PlayRequest;
import ar.edu.utn.frc.tup.lciii.dtos.play.PlayRpsDto;
import ar.edu.utn.frc.tup.lciii.models.Play;
import ar.edu.utn.frc.tup.lciii.models.rps.PlayRps;

public class PlayFactory {

    public static Play getPlayInstance(PlayRequest playRequest, String gameCode){
        switch (gameCode) {
            case "RPS":
                return getPlayRpsInstance(playRequest);
            default:
                return getPlayRpsInstance(playRequest);
        }
    }

    private static Play getPlayRpsInstance(PlayRequest playRequest){
        PlayRpsDto playRpsDto = (PlayRpsDto) playRequest;
        PlayRps playRps = new PlayRps();
        playRps.setShapeHandPlayer1(playRpsDto.getShapeHandPlayer1());
        playRps.setShapeHandPlayer2(playRpsDto.getShapeHandPlayer2());
        return playRps;
    }
}
