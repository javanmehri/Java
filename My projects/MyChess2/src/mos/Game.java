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
            if (board.getPieceOnTheSpot(from_i,from_j).isValidMove(board, to_i,to_j))
            {
                board = player.move(board, from_i, from_j, to_i, to_j);
                return true;
            }

        }


        return false;


    }


}

