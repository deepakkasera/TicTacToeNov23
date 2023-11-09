package org.example.models;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private Long id;

    public Player(String name, Symbol symbol, PlayerType playerType, Long id) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Move makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);

        //Ask the Player to provide the index to make a move.
        System.out.println("Please tell the row index to make a move");
        int rowNumber = scanner.nextInt();

        System.out.println("Please tell the col index to make a move");
        int colNumber = scanner.nextInt();

        return new Move(new Cell(rowNumber, colNumber), this);
    }
}
