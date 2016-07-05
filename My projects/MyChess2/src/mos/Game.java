package mos;

/**
 * Created by MOS on 2016-07-04.
 */
public class Game {

    private Board board;

    private Player player_W;
    private Player player_B;

    public King king_B = new King(Piece.Black);
    public King king_W = new King(Piece.White);

    public Game()
    {
        super();
        board = new Board();
        //king_B.setPieceType(Piece.Type.KING);

    }

    public void setBlackPlayer(Player player) { this.player_B = player; }

    public void setWhitePlayer(Player player) { this.player_W = player; }

    public Player getBlackPlayer() { return player_B; }

    public Player getWhitePlayer() { return player_W; }

    public Board getBoard() { return board; }

    public void setBoard(Board board) { this.board = board; }

    public void start()
    {
        board.occupySpot(king_B, 0 ,0 );
        //System.out.println(king_B.getPieceType());
        //System.out.println(board.getSpot(0,0).getPiece());
    }

}

