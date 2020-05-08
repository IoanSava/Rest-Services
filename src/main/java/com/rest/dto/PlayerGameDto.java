package com.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PlayerGameDto {
    private Long id;
    private PlayerDto player;
    private GameDto game;
}
