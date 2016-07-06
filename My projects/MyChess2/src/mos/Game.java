package mos;

/**
 * Created by MOS on 2016-07-04.
 */
public class Game {

    private Board board;
    private Player player1;
    private Player player2;


    public Game(String player_White, String player_Black)
    {
        board = new Board();

        player1 = new Player(player_White, Piece.Color.WHITE);
        player2 = new Player(player_Black, Piece.Color.BLACK);

        board = player1.setUpPieces(board);
        board = player2.setUpPieces(board);

    }


    public Player getPlayer_White() { return player1; }

    public Player getPlayer_Black() { return player2; }

    public Board getBoard() { return board; }

    public void setBoard(Board board) { this.board = board; }

    public void start()
    {
        //System.out.println(king_B.getPieceType());
        //System.out.println(board.getSpot(0,0).getPiece());
    }

    public void move(Player player, int from_i, int from_j, int to_i, int to_j)
    {
        board = player.move(board, from_i, from_j, to_i,to_j);
    }

}

