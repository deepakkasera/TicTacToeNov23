package org.example.strategies.winningStrategies;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Symbol;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    private Map<Integer, Map<Symbol, Integer>> rowMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        if (!rowMaps.containsKey(row)) {
            rowMaps.put(row, new HashMap<>());
        }

        Map<Symbol, Integer> currentRowMap = rowMaps.get(row);

        if (currentRowMap.containsKey(symbol)) {
            currentRowMap.put(symbol,
                    currentRowMap.get(symbol) + 1);
        } else {
            currentRowMap.put(symbol, 1);
        }

        return currentRowMap.get(symbol) == board.getDimension();
    }
}
