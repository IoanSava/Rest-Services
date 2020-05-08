package com.rest.services;

import com.rest.dto.PlayerDto;
import com.rest.entities.Player;
import com.rest.exceptions.DuplicateEntityException;
import com.rest.exceptions.NotFoundException;
import com.rest.repositories.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    private final ModelMapper modelMapper = new ModelMapper();


    public List<PlayerDto> getAllPlayers() {
        return ((List<Player>) playerRepository.findAll())
                .stream()
                .map(player -> modelMapper.map(player, PlayerDto.class))
                .collect(Collectors.toList());
    }

    public void addPlayer(PlayerDto playerDto) {
        if (playerDto.getId() != null && checkIfPlayerExists(playerDto.getId())) {
            throw new DuplicateEntityException("Player with id " + playerDto.getId() + " already exists");
        }
        playerRepository.save(modelMapper.map(playerDto, Player.class));
    }

    private boolean checkIfPlayerExists(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.isPresent();
    }

    public void updatePlayerName(Long id, String name) {
        if (!checkIfPlayerExists(id)) {
            throw new NotFoundException("Player with id " + id + " not found");
        }
        playerRepository.updateName(id, name);
    }

    public void deletePlayer(Long id) {
        if (!checkIfPlayerExists(id)) {
            throw new NotFoundException("Player with id " + id + " not found");
        }
        playerRepository.deleteById(id);
    }
}
