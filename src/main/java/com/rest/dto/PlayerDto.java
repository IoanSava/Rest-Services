package com.rest.dto;


import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "Details about the players." +
        " A player must have at least a name.")
public class PlayerDto {
    private Long id;
    private String name;
}
