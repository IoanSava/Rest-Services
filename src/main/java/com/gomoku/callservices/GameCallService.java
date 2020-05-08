package com.gomoku.callservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gomoku.game.Game;
import com.rest.dto.GameDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class GameCallService {
    private final String URI = "http://localhost:8080/games";

    @GetMapping("/call_games")
    public List<GameDto> getPlayers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<GameDto>> response = restTemplate.exchange(
                URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    private String getCurrentDateToString() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    private String convertMovesToString(List<Map<String, String>> moves) {
        String movesToString = "";

        for (Map<String, String> move : moves) {
            movesToString = movesToString.concat(move.get("token") + "-" + move.get("move") + " ");
        }

        return movesToString;
    }

    private String convertLastMoveToResult(Map<String, String> move) {
        if (move.get("token").equals("x")) {
            return "P1-W";
        }
        return "P2-W";
    }

    @PostMapping("/call_games")
    public ResponseEntity<GameDto> createGame(Game game) {
        ResponseEntity<GameDto> gameDto = null;
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> bodyParams = new HashMap<>();
            bodyParams.put("content", convertMovesToString(game.getMoves()));
            bodyParams.put("result", convertLastMoveToResult(game.getLastMove()));
            bodyParams.put("date", getCurrentDateToString());

            String bodyData = new ObjectMapper().writeValueAsString(bodyParams);

            HttpEntity<String> request = new HttpEntity<>(bodyData, header);

            gameDto = restTemplate.postForEntity(URI, request, GameDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return gameDto;
    }
}
