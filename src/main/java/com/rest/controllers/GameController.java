package com.rest.controllers;

import com.rest.dto.GameDto;
import com.rest.services.GameService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@Validated
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    @ApiOperation(value = "Retrieve all finished games",
            response = GameDto.class,
            responseContainer = "List")
    public List<GameDto> getAllGames() {
        return gameService.getAllGames();
    }

    @PostMapping
    @ApiOperation(value = "Add a new game")
    public ResponseEntity<String> addGame(@RequestBody GameDto gameDto) {
        gameService.addGame(gameDto);
        return new ResponseEntity<>("Game created", HttpStatus.CREATED);
    }
}
