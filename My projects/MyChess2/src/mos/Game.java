package mos;

/**
 * Created by MOS on 2016-07-04.
 */
public class Game {

    private Board board;

    private Player player1;
    private Player player2;

    private King king_B = new King(Piece.Color.BLACK);
    private King king_W = new King(Piece.Color.WHITE);

    public Game()
    {
        board = new Board();

        player1 = new Player("Player 1", Piece.Color.WHITE);
        player2 = new Player("Player 2", Piece.Color.BLACK);

        board = player1.setUpPieces(board);
        board= player2.setUpPieces(board);

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
        //board.occupySpot(king_B, 0 ,0 );
        //System.out.println(king_B.getPieceType());
        //System.out.println(board.getSpot(0,0).getPiece());
    }

}

