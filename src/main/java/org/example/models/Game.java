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

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        return row < board.getDimension() && row >= 0 && col < getBoard().getDimension() && col >= 0 &&
                board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    private boolean checkWinner(Board board, Move move) {
        for (WinningStrategy winningStrategy : winningStrategies) {
            if (winningStrategy.checkWinner(move, board)) {
                return true;
            }
        }

        return false;
    }

    public void makeMove() {
        //Current player to make the move.
        Player currentPlayer = players.get(nextMovePlayerIndex);

        System.out.println("It is " + currentPlayer.getName() + "'s move.");
        Move move = currentPlayer.makeMove(this.board);

        System.out.println(currentPlayer.getName() + " has made a move at Row: " + move.getCell().getRow() +
                ", col: " + move.getCell().getCol() + ".");

        //Validate the move before we apply the move on Board.
        if (!validateMove(move)) {
            System.out.println("Invalid move by player: " + currentPlayer.getName());
            return;
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell finalCellToMakeMove = board.getBoard().get(row).get(col);
        finalCellToMakeMove.setCellState(CellState.FILLED);
        finalCellToMakeMove.setPlayer(currentPlayer);

        Move finalMove = new Move(finalCellToMakeMove, currentPlayer);
        moves.add(finalMove);

        nextMovePlayerIndex += 1;
        nextMovePlayerIndex %= players.size();

        //Once a player has made move, check the winner.
        //checkWinner.
        if (checkWinner(board, finalMove)) {
            gameState = GameState.ENDED;
            winner = currentPlayer;
        } else if (moves.size() == board.getDimension() * board.getDimension()) {
            //Game has DRAWN
            gameState = GameState.DRAW;
        }
    }
}
