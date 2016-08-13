package mos;

import javafx.event.Event;

/**
 * Created by MOS on 2016-07-20.
 */
public class COMP {

    //private int level=1;



    public static Game Play(Game game, int level) throws CloneNotSupportedException {


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


    private static Game[] allPossiblePlays(Game game) throws CloneNotSupportedException {
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


    private static Spot[] possibleMoves(Game game, Piece piece) throws CloneNotSupportedException {
        Spot[] possibleSpots = new Spot[63];

        Board board = game.getBoard();
        Spot[] spots = board.getAllSpots();
        int counter = 0;

        for (int i=0; i<spots.length; i++)
        {
            if (isValid(game, piece, spots[i]))
            {
                possibleSpots[counter]= spots[i].clone();
                counter ++;
            }

        }

        return  possibleSpots;
    }


    private static Boolean isValid(Game game, Piece piece, Spot spot)
    {
        int toX = spot.get_X();
        int toY = spot.get_Y();
        Board board = game.getBoard();

        return (( piece.isValidMove(board, toX, toY) || piece.isValidRemove(board, toX, toY) ) &&
                !game.isAnyPieceOnTheWay(piece, spot) );

        //return false;
        //game.getActivePlayer().
    }


    public static int possibleMoveSize(Game game, Piece piece) throws CloneNotSupportedException {
        Spot[] moves = possibleMoves(game, piece);
        int counter = 0;
        for (int i = 0; i<moves.length; i++ )
        {
            if (moves[i] != null)
                counter ++;
        }
        return counter;
    }

    public static int possibleMoveSize(Game game, Event event) throws CloneNotSupportedException {
        Piece piece = ScreenBoard.getSpot(event).getPiece();
        return possibleMoveSize(game, piece);
    }

}
