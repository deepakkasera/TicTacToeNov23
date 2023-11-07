package org.example.models;

import org.example.strategies.playingStrategy.BotPlayingStrategy;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, Symbol symbol, PlayerType playerType, Long id,
               BotDifficultyLevel difficultyLevel,
               BotPlayingStrategy playingStrategy) {
        super(name, symbol, playerType, id);
        this.botDifficultyLevel = difficultyLevel;
        this.botPlayingStrategy = playingStrategy;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
