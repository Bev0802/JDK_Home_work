package org.example;


public class Game {
    private GameRound gameRound;

    public Game() {
        this.gameRound = new GameRound();
    }

    public GameRound play() {
        return gameRound;
    }
}


