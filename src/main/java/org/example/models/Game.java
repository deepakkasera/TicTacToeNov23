package org.example.models;

import org.example.exceptions.GameInvalidationException;
import org.example.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private GameState gameState;
    private Player winner;
    private int nextMovePlayerIndex; //which player has the turn.
    private List<Move> moves;
    private List<WinningStrategy> winningStrategies;

    public static Builder getBuilder() {
        return new Builder();
    }

    //Static Inner Builder bClass
    public static class Builder {
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

//        private Builder() {
//            this.players = new ArrayList<>();
//            this.winningStrategies = new ArrayList<>();
//            this.dimension = 0;
//        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private boolean validate() {
            //Validations. TODO
            //1. Only one Bot is allowed per game.
            //2. NO 2 players should have the same symbol.
            return true;
        }

        public Game build() throws Exception {
            //validate.
            if (!validate()) {
                throw new GameInvalidationException("Invalid game");
            }

            //create the Game object.
            return new Game(dimension, players, winningStrategies);
        }
    }

    private Game(int dimension, List<Player> players,
                List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.gameState = GameState.IN_PROGRESS;
        this.nextMovePlayerIndex = 0;
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategies;
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public void printBoard() {
        board.displayBoard();
    }

    public void undo() {

    }

    public void makeMove(Board board) {
        //Current player to make the move.
        Player currentPlayer = players.get(nextMovePlayerIndex);

        System.out.println("It is " + currentPlayer.getName() + "'s move.");
        Move move = currentPlayer.makeMove(board);

        System.out.println(currentPlayer.getName() + " has made a move at Row: " + move.getCell().getRow() +
                ", col: " + move.getCell().getCol() + ".");
    }
}
