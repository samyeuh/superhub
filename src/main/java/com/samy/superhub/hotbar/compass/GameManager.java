package com.samy.superhub.hotbar.compass;

import com.samy.superhub.hotbar.compass.games.Hikabrain;

import java.util.HashMap;

public class GameManager {

    private final HashMap<String, AbstractGame> games;

    public GameManager(){
        this.games = new HashMap<>();
        this.registerGame(new Hikabrain());
    }

    private void registerGame(AbstractGame game){
        if (!this.games.containsKey(game.getGameId())){
            this.games.put(game.getGameId(), game);
        }
    }

//    public AbstractGame getGame(String gameId){
//        return games.getOrDefault(gameId, null);
//    }

    public HashMap<AbstractGame, Integer> getGamesInCompass(){
        HashMap<AbstractGame, Integer> gamesInCompass = new HashMap<>();
        for (AbstractGame game : games.values()){
            gamesInCompass.put(game, game.getSlotInCompass());
        }
        return gamesInCompass;
    }
}
