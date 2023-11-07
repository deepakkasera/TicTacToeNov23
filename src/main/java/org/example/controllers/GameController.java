package org.example.controllers;

import org.example.models.Game;
import org.example.models.Player;
import org.example.strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int boardDimension, List<Player> players,
                          List<WinningStrategy> winningStrategies) {
        return null;
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void displayBoard(Game game) {
        game.printBoard();
    }

    public Player checkWinner(Game game) {
        return game.getWinner();
    }

    public void undo(Game game) {
        game.undo();
    }
}
