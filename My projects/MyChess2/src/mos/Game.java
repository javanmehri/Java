package mos;

/**
 * Created by MOS on 2016-07-04.
 */
public class Game {

    private Board board;

    private Player player1;
    private Player player2;

    private King king_B = new King(Piece.Black);
    private King king_W = new King(Piece.White);

    public Game()
    {
        super();
        board = new Board();
        //king_B.setPieceType(Piece.Type.KING);

    }

    public void setPlayer1(Player player) { this.player1 = player; }

    public void setPlayer2(Player player) { this.player2 = player; }

    public Player getPlayer1() { return player1; }

    public Player getPlayer2() { return player2; }

    public Board getBoard() { return board; }

    public void setBoard(Board board) { this.board = board; }

    public void start()
    {
        board.occupySpot(king_B, 0 ,0 );
        //System.out.println(king_B.getPieceType());
        //System.out.println(board.getSpot(0,0).getPiece());
    }

}

