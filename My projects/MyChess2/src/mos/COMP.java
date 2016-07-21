package mos;

/**
 * Created by MOS on 2016-07-20.
 */
public class COMP {

    //private int level=1;



    public static Game Play(Game game, int level)
    {


        Game[] allpossiblePlays = allPossiblePlays(game);

        if (level == 2)
        {
            for (int i=0; i< allpossiblePlays.length; i++)
            {
                Game play = allpossiblePlays[i];
                play.setParent(game);
                play.setChildren(allPossiblePlays(play));
            }
        }


        if (level==3)
        {
            for (int i=0; i< allpossiblePlays.length; i++)
            {
                Game play = allpossiblePlays[i];
                play.setParent(game);
                Game[] plays_level2 = allPossiblePlays(play);
                play.setChildren(plays_level2);

                for (int j=0; j<plays_level2.length; j++)
                {
                    Game play2 = plays_level2[j];
                    play2.setParent(play);
                    play2.setChildren(allPossiblePlays(play2));
                }
            }
        }

        return bestPlay(allpossiblePlays);
    }




    private static Game bestPlay(Game[] plays)
    {

        return null;
    }


    private static Game[] allPossiblePlays(Game game)
    {
        Player activePlayer = game.getActivePlayer();
        Piece[] pieces = activePlayer.getAvailablePieces();
        Spot[] possibleMoves = new Spot[63];

        Game[] possiblePlays = new Game[16*63];

        //Board bestPlay = new Board();

        for (int i=0; i< pieces.length; i++)
        {
            // for each piece

            possibleMoves = possibleMoves(game, pieces[i]);

            for (int k=0; k<possibleMoves.length; k++)
            {
                possiblePlays[16*i+k] = game.clone();

                activePlayer.move(possiblePlays[16*i+k].getBoard(), pieces[i].getSpot(), possibleMoves[k]);

            }

        }
        return possiblePlays;
    }



    private static Spot[] possibleMoves(Game game, Piece piece)
    {
        Spot[] possibleSpots = new Spot[63];

        return  possibleSpots;
    }



}
