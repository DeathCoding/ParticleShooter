package com.pixelfrax.ParticleShooter;

public enum GameState {

    LOBBY(true),
    WARMUP(true),
    INGAME(true),
    END(false);

    private static GameState currentstate;
    private boolean joinable;

    GameState(boolean joinable) {
        this.joinable = joinable;
    }

    /**
     * sets the GameState
     * @param state
     */
    public static void setGameState(GameState state) {
        currentstate = state;
    }

    /**
     * checks if is in a GameState
     * @param state
     * @return
     */
    public static boolean isGameState(GameState state) {
        return currentstate == state;
    }

    /**
     *
     * @return GameState
     */
    public static GameState getGameState() {
        return currentstate;
    }

    /**
     *
     * @return joinable
     */
    public boolean joinable() {
        return joinable;
    }
}
