package mos;

import java.io.IOException;

/**
 * Created by MOS on 2016-07-13.
 */
public class GameManager {


    private static Game game = null;


    public static void startAGame()
    {
        game = new Game("Player 1", "Player 2");
    }



    public static Game getTheGame()
    {
        return game;
    }




}

