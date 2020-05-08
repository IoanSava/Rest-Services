package com.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "Details about the games." +
        " A game must contain information about the players," +
        " a text representing the content of the game, the date and the result.")
public class GameDto {
    private Long id;

    @ApiModelProperty(notes = "The content of the game e.g. 'P1-1-2 P2-2-3' means " +
            "that the first player placed a token at (1,2) tile and the second player " +
            "placed a token at (2,3) tile")
    private String content;

    @ApiModelProperty(notes = "The result of the game e.g. 'P1W' means " +
            "that the first player won the game")
    private String result;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale="ro", timezone = "Europe/Bucharest")
    private Date date;
}
