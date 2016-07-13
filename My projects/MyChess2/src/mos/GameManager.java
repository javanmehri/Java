package mos;

/**
 * Created by MOS on 2016-07-13.
 */
public class GameManager {

    private static Game game;
    private static Player activePlayer;

    public static void startAGame()
    {
        game = new Game("Player 1", "Player 2");
        activePlayer = game.getPlayer_White();
    }

    public static void updateTheGame(Game newGame)
    {
        game = newGame;
        //System.out.println("Updat the Game!");
    }

    public static Game giveTheGame()
    {
        return game;
    }

}
