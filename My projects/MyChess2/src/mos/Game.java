package mos;

/**
 * Created by MOS on 2016-07-04.
 */
public class Game {

    private Board board;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private Player selectedPlayer;



    public Game(String player_White, String player_Black)
    {
        board = new Board();

        player1 = new Player(player_White, Piece.Color.WHITE);
        player2 = new Player(player_Black, Piece.Color.BLACK);

        activePlayer = getPlayer_White();

        board = player1.setUpPieces(board);
        board = player2.setUpPieces(board);

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


    public boolean move(Player player, int from_i, int from_j, int to_i, int to_j)
    {
        if (player == activePlayer)
        {
            if (getPlayer(to_i,to_j)==null)
            {
                if (board.getPieceOnTheSpot(from_i,from_j).isValidMove(board, to_i,to_j) && !isAnyPieceOnTheWay(from_i,from_j,to_i,to_j))
                {
                    board = player.move(board, from_i, from_j, to_i, to_j);
                    return true;
                }

            }
            else if (getPlayer(to_i,to_j)!=activePlayer)
            {
                if (board.getPieceOnTheSpot(from_i,from_j).isValidRemove(board, to_i,to_j) && !isAnyPieceOnTheWay(from_i,from_j,to_i,to_j))
                {
                    board = player.remove(board, from_i, from_j, to_i, to_j);
                    return true;
                }
            }

        }

        return false;

    }



    public Spot[] getSpotsOnTheWay(int from_i, int from_j, int to_i, int to_j)
    {
        Spot[] spots = new Spot[6];
        if (from_j == to_j)
        {
            int delta = Math.abs(from_i - to_i);
            if (delta > 1)
            {
                for (int k=0; k < delta-1; k++)
                {
                    int min_i = Math.min(from_i , to_i);
                    spots[k] = board.getSpot(min_i+k+1, from_j);
                }
            }
        }

        if (from_i == to_i)
        {
            int delta = Math.abs(from_j - to_j);
            if (delta > 1)
            {
                for (int k=0; k < delta-1; k++)
                {
                    int min_j = Math.min(from_j , to_j);
                    spots[k] = board.getSpot(from_i, min_j+k+1);
                }
            }
        }


        return spots;
    }

    public boolean isAnyPieceOnTheWay(int from_i, int from_j, int to_i, int to_j)
    {

        int delta_i = Math.abs(from_i - to_i);
        int delta_j = Math.abs(from_j - to_j);
        int max_delta = Math.max(delta_i, delta_j);
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

}

