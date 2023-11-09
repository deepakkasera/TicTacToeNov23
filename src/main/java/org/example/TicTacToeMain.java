package org.example;

import org.example.controllers.GameController;
import org.example.models.*;
import org.example.strategies.playingStrategy.BotPlayingStrategy;
import org.example.strategies.playingStrategy.EasyBotPlayingStrategy;
import org.example.strategies.winningStrategies.ColWinningStrategy;
import org.example.strategies.winningStrategies.DiagonalWinningStrategy;
import org.example.strategies.winningStrategies.RowWinningStrategy;
import org.example.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeMain {
    public static void main(String[] args) throws Exception {
        GameController gameController = new GameController();
        //System.out.println("Enter the game dimension");
        int dimension = 3;
        int numberOfPlayers = dimension - 1;
        List<Player> players = new ArrayList<>();

        players.add(
                new Player("Mudit", new Symbol('X'), PlayerType.HUMAN, 1L)
        );

        players.add(
                new Bot("BotPlayer", new Symbol('O'), PlayerType.BOT, 2L,
                        BotDifficultyLevel.EASY, new EasyBotPlayingStrategy())
        );

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        //Start the game.
        Game game = gameController.startGame(dimension, players, winningStrategies);

        //Start playing the game.
        while (gameController.getGameState(game).equals(GameState.IN_PROGRESS)) {
            //Display the board.
            System.out.println("This is the current state of Board");
            gameController.displayBoard(game);

            //Do you want to UNDO ? If yes, call the UNDO functionality else call the makeMove.

            gameController.makeMove(game);
        }

        if (gameController.getGameState(game).equals(GameState.ENDED)) {
            //Somebody has won the game.
            gameController.displayBoard(game);
            System.out.println("Winner is " + gameController.getWinner(game).getName());
        } else {
            System.out.println("Game has DRAWN");
        }
    }
}