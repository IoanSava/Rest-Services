package com.gomoku.game;

import com.gomoku.exceptions.TooMuchPlayersException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Game of Gomoku (Five in a row)
 *
 * @author Ioan Sava
 * @see <a href="https://en.wikipedia.org/wiki/Gomoku">Gomoku</a>
 */
@Getter
@Setter
public class Game {
    /**
     *
     */
    private final int CHAIN_SIZE = 3;

    /**
     * The token of the player who
     * has the current turn.
     */
    private char currentTurn;

    private Board board;
    private List<Player> players;

    /**
     * A representation of the game which consists
     * of a list of moves made by players.
     */
    private List<Map<String, String>> moves;

    public Game() {
        this.currentTurn = 'x';
        this.board = new Board();
        this.players = new ArrayList<>();
        this.moves = new ArrayList<>();
    }

    public synchronized boolean isAvailable() {
        return players.size() < 2;
    }

    /**
     * Add a player to the game.
     * In order to start, the game needs
     * exactly two players.
     */
    public boolean addPlayer(Player player) {
        try {
            if (players.size() > 2) {
                throw new TooMuchPlayersException();
            }
            players.add(player);
            return true;
        } catch (TooMuchPlayersException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void updateTurn() {
        if (this.currentTurn == 'x') {
            this.currentTurn = 'o';
        } else {
            this.currentTurn = 'x';
        }
    }

    public synchronized void addMove(Player player, String move) {
        Map<String, String> currentMove = new HashMap<>();
        String token = String.valueOf(player.getToken());
        currentMove.put("token", token);
        currentMove.put("move", move);
        this.moves.add(currentMove);
    }

    public synchronized Map<String, String> getLastMove() {
        return this.moves.get(this.moves.size() - 1);
    }

    private final int[] xDirections = {1, 0, 1, 1};
    private final int[] yDirections = {0, 1, 1, -1};

    public boolean checkWon(char token, int x, int y) {
        for (int direction = 0; direction < 4; ++direction) {
            int currentChainSize = 1;

            for (int i = 1; i < CHAIN_SIZE; ++i) {
                int currentX = x + xDirections[direction] * i;
                int currentY = y + yDirections[direction] * i;

                if (this.board.getPiece(currentX, currentY) == token) {
                    ++currentChainSize;
                } else {
                    break;
                }
            }

            for (int i = 1; i < CHAIN_SIZE; ++i) {
                int currentX = x - xDirections[direction] * i;
                int currentY = y - yDirections[direction] * i;

                if (this.board.getPiece(currentX, currentY) == token) {
                    ++currentChainSize;
                } else {
                    break;
                }
            }

            if (currentChainSize >= CHAIN_SIZE) {
                return true;
            }
        }
        return false;
    }
}
