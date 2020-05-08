package com.gomoku.callservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gomoku.game.Player;
import com.rest.dto.PlayerDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class PlayerCallService {
    private final String URI = "http://localhost:8080/players";

    @GetMapping("/call_players")
    public List<PlayerDto> getPlayers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<PlayerDto>> response = restTemplate.exchange(
                URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    @PostMapping("/call_players")
    public ResponseEntity<PlayerDto> createPlayer(Player player) {
        ResponseEntity<PlayerDto> playerDto = null;
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> bodyParams = new HashMap<>();
            bodyParams.put("name", player.getName());

            String bodyData = new ObjectMapper().writeValueAsString(bodyParams);

            HttpEntity<String> request = new HttpEntity<>(bodyData, header);

            playerDto = restTemplate.postForEntity(URI, request, PlayerDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return playerDto;
    }
}
