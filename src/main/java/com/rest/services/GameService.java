package com.rest.services;

import com.rest.dto.GameDto;
import com.rest.entities.Game;
import com.rest.exceptions.DuplicateEntityException;
import com.rest.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<GameDto> getAllGames() {
        return ((List<Game>) gameRepository.findAll())
                .stream()
                .map(game -> modelMapper.map(game, GameDto.class))
                .collect(Collectors.toList());
    }

    private boolean checkIfGameExists(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.isPresent();
    }

    public void addGame(GameDto gameDto) {
        if (gameDto.getId() != null && checkIfGameExists(gameDto.getId())) {
            throw new DuplicateEntityException("Game with id " + gameDto.getId() + " already exists");
        }
        gameRepository.save(modelMapper.map(gameDto, Game.class));
    }
}
