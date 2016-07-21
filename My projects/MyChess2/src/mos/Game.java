package mos;

import java.io.IOException;

/**
 * Created by MOS on 2016-07-04.
 */
public class Game {

    private Board board;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private Player selectedPlayer;

    private Game parent;
    private Game[] children;



    public Game(String player_White, String player_Black)
    {
        board = new Board();

        player1 = new Player(player_White, Piece.Color.WHITE);
        player2 = new Player(player_Black, Piece.Color.BLACK);

        activePlayer = getPlayer_White();

        //board = player1.setUpPieces(board);
        //board = player2.setUpPieces(board);

        player1.setUpPieces(board);
        player2.setUpPieces(board);


    }


    public Player getActivePlayer()
    {
        return activePlayer;
    }

    public void setSelectedPlayer(Player player)
    {
        selectedPlayer = player;
    }

    public void switchTheActivePlayer()
    {
        if (activePlayer == player1)
            activePlayer = player2;
        else
            activePlayer = player1;
    }

    public Player getPlayer_White() { return player1; }

    public Player getPlayer_Black() { return player2; }

    public Board getBoard() { return board; }

    public void setBoard(Board board) { this.board = board; }

    public boolean isActivePlayer(Player player)
    {
        return player==activePlayer;
    }


    public boolean move(Player player, int from_i, int from_j, int to_i, int to_j) throws IOException {

        if (player != activePlayer)
            return false;

        if (getPlayer(to_i,to_j)==null)
        {
                if (board.getPieceOnTheSpot(from_i,from_j).isValidMove(board, to_i,to_j) && !isAnyPieceOnTheWay(from_i,from_j,to_i,to_j))
                {
                    board = player.move(board, from_i, from_j, to_i, to_j);

                    if (isCheck())
                    {
                        board = player.undoMove(board, from_i, from_j, to_i, to_j);
                        return false;
                    }

                    return true;
                }

        }
        else if (getPlayer(to_i,to_j)!=activePlayer)
        {
                if (board.getPieceOnTheSpot(from_i,from_j).isValidRemove(board, to_i,to_j) && !isAnyPieceOnTheWay(from_i,from_j,to_i,to_j))
                {
                    board = player.remove(board, from_i, from_j, to_i, to_j);
                    Sounds.play(Sounds.SoundEffects.REMOVE);
                    return true;
                }
        }



        return false;

    }

    // =================================================================================================================

    public Spot[] getSpotsOnTheWay(int from_i, int from_j, int to_i, int to_j)
    {
        Spot[] spots = new Spot[6];
        int delta_i = Math.abs(to_i - from_i);
        int delta_j = Math.abs(to_j - from_j);
        int min_i = Math.min(from_i , to_i);
        int min_j = Math.min(from_j , to_j);

        //if (!isValidMove(from_i,from_j,to_i,to_j) || !isValidRemove(from_i,from_j,to_i,to_j) )
            //return spots;

        if (delta_j == 0)
        {
            if (delta_i > 1)
            {
                for (int k=0; k < delta_i-1; k++)
                {
                    spots[k] = board.getSpot(min_i+k+1, from_j);
                }
            }
        }

        else if (delta_i == 0)
        {
            if (delta_j > 1)
            {
                for (int k=0; k < delta_j-1; k++)
                {
                    spots[k] = board.getSpot(from_i, min_j+k+1);
                }
            }
        }

        else if (delta_i == delta_j)
        {
           // Report.report(" delta_i = delta_j ");
            if (delta_i > 1)
            {
                for (int k=0; k<delta_i-1; k++)
                {
                    if ((to_i-from_i)>0 && (to_j-from_j)>0)
                    {
                        spots[k] = board.getSpot(from_i+(k+1),from_j+(k+1));
                    }
                    else if ((to_i-from_i)>0 && (to_j-from_j)<0)
                    {
                        spots[k] = board.getSpot(from_i+(k+1),from_j-(k+1));
                        //Report.report(" i="+(from_i+(k+1))+" j="+(from_j-(k+1)));
                    }
                    else if ((to_i-from_i)<0 && (to_j-from_j)>0)
                    {
                        spots[k] = board.getSpot(from_i-(k+1),from_j+(k+1));
                    }
                    else if ((to_i-from_i)<0 && (to_j-from_j)<0)
                    {
                        spots[k] = board.getSpot(from_i-(k+1),from_j-(k+1));
                    }
                }
            }
        }

        //Report.report(spots);
        return spots;
    }

    // =================================================================================================================

    public boolean isAnyPieceOnTheWay(int from_i, int from_j, int to_i, int to_j)
    {

        int delta_i = Math.abs(from_i - to_i);
        int delta_j = Math.abs(from_j - to_j);
        int max_delta = Math.max(delta_i, delta_j);

        //if (getBoard().getPieceOnTheSpot(from_i,from_j).getPieceType()== Piece.Type.KNIGHT)
            //return true;

        if (max_delta>1)
        {
            Spot[] spots = getSpotsOnTheWay(from_i, from_j, to_i, to_j);
            for (int i = 0; i<max_delta-1; i++)
            {
                if (spots[i]!=null)
                    if (spots[i].isOccupied())
                         return true;
            }
        }
        //if (delta_i==0 || delta_j==0 || delta_i==delta_j)
        //{
        //    return false;
        //}
        return false;
    }


    public Player getPlayer(int i, int j)
    {
        if (board.getSpot(i,j).isOccupied())
        {
            if (board.getPieceOnTheSpot(i,j).getColor()== Piece.Color.WHITE)
            {
                return getPlayer_White();
            }
            else
            {
                return getPlayer_Black();
            }
        }
        return null;

    }

    public boolean isActivePlayer(int i, int j)
    {
        return getPlayer(i,j)==activePlayer;
    }


    public boolean isValidMove(int from_i, int from_j, int to_i, int to_j)
    {

        if (board.getPieceOnTheSpot(from_i,from_j).isValidMove(board,to_i,to_j)==false)
            return false;

        //Game game2 = new Game("w", "b");
        //game2.setBoard(getActivePlayer().move(board,from_i,from_j,to_i,to_j));

        //if (isCheck(game2))
            //return false;

        return true;
    }


    public boolean isValidRemove(int from_i, int from_j, int to_i, int to_j)
    {

        if (board.getPieceOnTheSpot(from_i,from_j).isValidRemove(board,to_i,to_j)==false)
            return false;

        //Game game2 = new Game("w", "b");
        //game2.setBoard(getActivePlayer().remove(board,from_i,from_j,to_i,to_j));

        //if (isCheck(game2))
            //return false;

        return true;

    }


    public Player getUnactivePlayer()
    {
        Player player;
        if (getActivePlayer()==getPlayer_White())
            player = getPlayer_Black();
        else
            player = getPlayer_White();

        return player;
    }


    public boolean isCheck()
    {
        return isCheck(this);
    }

    private boolean isCheck(Game game)
    {
        Player player = game.getUnactivePlayer();
        Piece[] pieces = player.getAllPieces();
        Piece[] validRemoves;

        for (int i=0; i<16; i++)
        {
            if (pieces[i].isAvailable())
            {
                validRemoves = getAllValidRemoves(pieces[i]);
                for (int k=0; k<validRemoves.length; k++)
                {
                    if (validRemoves[k]!=null)
                    {
                        if (validRemoves[k].getPieceType()== Piece.Type.KING)
                        {
                            Report.report(" >> Check <<");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public boolean stillCheck()
    {
        return stillCheck(this);
    }


    private boolean stillCheck(Game game)
    {
        Player player = game.getActivePlayer();

        Piece[] pieces = player.getAllPieces();
        Piece[] validRemoves;

        for (int i=0; i<16; i++)
        {
            if (pieces[i].isAvailable())
            {
                validRemoves = getAllValidRemoves(pieces[i]);
                for (int k=0; k<validRemoves.length; k++)
                {
                    if (validRemoves[k]!=null)
                    {
                        if (validRemoves[k].getPieceType()== Piece.Type.KING)
                        {
                            Report.report(" >> Check <<");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public Spot[] getAllValidMoves(Spot from_spot)
    {
        Spot[] validSpots = new Spot[64];
        Spot[] allSpots = board.getAllSpots();
        Spot toSpot;
        int from_i = from_spot.get_X();
        int from_j = from_spot.get_Y();
        int to_i;
        int to_j;

        int counter = 0;

        for (int i=0; i<64; i++)
        {
            toSpot = allSpots[i];
            to_i = toSpot.get_X();
            to_j = toSpot.get_Y();
            if (!isAnyPieceOnTheWay(from_i,from_j,to_i,to_j) && (isValidRemove(from_i,from_j,to_i,to_j) || isValidMove(from_i,from_j,to_i,to_j)))
            {
                validSpots[counter] = board.getSpot(to_i,to_j);
                counter ++;
            }
        }
        return validSpots;
    }


    public Spot[] getAllValidMoves(Piece piece)
    {
        return getAllValidMoves(piece.getSpot());
    }


    public Piece[] getAllValidRemoves(Piece piece)
    {
        Piece[] validRemoves = new Piece[32];

        Spot[] allSpots = board.getAllSpots();
        Spot toSpot;

        int from_i = piece.getSpot().get_X();
        int from_j = piece.getSpot().get_Y();

        int to_i;
        int to_j;

        int counter = 0;

        for (int i=0; i<64; i++)
        {
            toSpot = allSpots[i];
            to_i = toSpot.get_X();
            to_j = toSpot.get_Y();
            if (!isAnyPieceOnTheWay(from_i,from_j,to_i,to_j) && isValidRemove(from_i,from_j,to_i,to_j))
            {
                validRemoves[counter] = board.getSpot(to_i,to_j).getPiece();
                counter ++;
            }
        }


        return validRemoves;
    }


    public Game clone()
    {
        Game newGame = new Game(this.getPlayer_White().getName(), this.getPlayer_Black().getName());
        newGame.setBoard(this.getBoard().clone());

        if (this.activePlayer==getPlayer_White())
                newGame.activePlayer = newGame.getPlayer_White();
        else
                newGame.activePlayer = newGame.getPlayer_Black();

        // set selected Payer
       // ...
        return newGame;
    }


    public void setParent(Game game)
    {
        parent = game;
    }


    public Game getParent()
    {
        return parent;
    }


    public void setChildren(Game[] chidrenGames)
    {
        children = chidrenGames;
    }


    public Game[] getChildren()
    {
        return children;
    }
}



