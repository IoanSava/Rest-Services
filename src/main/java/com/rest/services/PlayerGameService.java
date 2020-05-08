package com.rest.services;

import com.rest.dto.GameDto;
import com.rest.dto.PlayerDto;
import com.rest.dto.PlayerGameDto;
import com.rest.entities.Game;
import com.rest.entities.Player;
import com.rest.entities.PlayerGame;
import com.rest.exceptions.InvalidNumberOfPlayersForGameException;
import com.rest.exceptions.NotFoundException;
import com.rest.repositories.GameRepository;
import com.rest.repositories.PlayerGameRepository;
import com.rest.repositories.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerGameService {
    @Autowired
    private PlayerGameRepository playerGameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    private boolean checkIfGameExists(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.isPresent();
    }

    public List<PlayerDto> getPlayersByGame(Long gameId) {
        if (!checkIfGameExists(gameId)) {
            throw new NotFoundException("Game with id " + gameId + " not found");
        }

        return (playerGameRepository.findPlayersByGame(gameId))
                .stream()
                .map(player -> modelMapper.map(player, PlayerDto.class))
                .collect(Collectors.toList());
    }

    private boolean checkIfPlayerExists(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.isPresent();
    }

    public List<GameDto> getGamesByPlayer(Long playerId) {
        if (!checkIfPlayerExists(playerId)) {
            throw new NotFoundException("Player with id " + playerId + " not found");
        }

        return (playerGameRepository.findGamesByPlayer(playerId))
                .stream()
                .map(game -> modelMapper.map(game, GameDto.class))
                .collect(Collectors.toList());
    }

    private int getNumberOfPlayersOfGame(Long gameId) {
        return playerGameRepository.findPlayersByGame(gameId).size();
    }

    public void addPlayerGame(PlayerGameDto playerGameDto) {
        Long playerId = playerGameDto.getPlayer().getId();
        if (playerId != null && !checkIfPlayerExists(playerId)) {
            throw new NotFoundException("Player with id " + playerId + " not found");
        }

        Long gameId = playerGameDto.getGame().getId();
        if (gameId != null && !checkIfGameExists(gameId)) {
            throw new NotFoundException("Game with id " + gameId + " not found");
        }

        if (getNumberOfPlayersOfGame(gameId) > 1) {
            throw new InvalidNumberOfPlayersForGameException("The same game cannot have more than two players");
        }

        playerGameRepository.save(modelMapper.map(playerGameDto, PlayerGame.class));
    }
}