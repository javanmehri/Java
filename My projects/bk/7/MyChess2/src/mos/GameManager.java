package mos;

import java.io.IOException;

/**
 * Created by MOS on 2016-07-13.
 */
public class GameManager {


    private static Game game;


    public static void startAGame() throws IOException {

        Sounds.play(Sounds.SoundEffects.START);

        game = new Game("Player 1", "Player 2");
    }


    public static void updateTheGame(Game newGame)
    {
        game = newGame;
    }


    public static Game getTheGame()
    {
        return game;
    }




}

