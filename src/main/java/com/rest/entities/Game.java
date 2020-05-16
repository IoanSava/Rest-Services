package com.rest.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ioan Sava
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "content cannot be null")
    private String content;

    @NotNull(message = "result cannot be null")
    private String result;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private List<PlayerGame> playerGames = new ArrayList<>();
}
