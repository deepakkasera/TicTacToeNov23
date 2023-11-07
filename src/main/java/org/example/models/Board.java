package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimension;
    private List<List<Cell>> board;

    public Board(int n) {
        //Initialize a board of size * size.
        this.dimension = n;
        board = new ArrayList<>(); // ROWS

        for (int i = 0; i < n; i++) {
            board.add(new ArrayList<>()); //COLS

            for (int j = 0; j < n; j++) {
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Cell cell = board.get(i).get(j);
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    System.out.println("|  |");
                } else {
                    System.out.println("|" + cell.getPlayer().getSymbol() + "|");
                }
            }
            System.out.println();
        }
    }
}
