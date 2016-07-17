package mos;

/**
 * Created by MOS on 2016-07-04.
 */

public class Player {

    private String name;
    private Piece.Color color;
    private Piece[][] pieces = new Piece[2][8];
    private Piece[] pieceBag = new Piece[16];

    public Player(String name, Piece.Color color)
    {
        this.name = name;
        this.color = color;

        makePieces();
    }

    public String getName(){ return name; }

    public Piece.Color getColor(){ return color; }

    private void makePieces()
    {
        pieces[0][0] = new Rook(color);
        pieces[0][1] = new Knight(color);
        pieces[0][2] = new Bishop(color);
        pieces[0][3] = new King(color);
        pieces[0][4] = new Queen(color);
        pieces[0][5] = new Bishop(color);
        pieces[0][6] = new Knight(color);
        pieces[0][7] = new Rook(color);

        for (int i=0; i<8; i++) { pieces[1][i] = new Pawn(color); }

    }



    public Board setUpPieces(Board board)
    {
        Board chessBoard = board;

        if (this.color == Piece.Color.WHITE)
        {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 8; j++) {
                    chessBoard.occupySpot(pieces[i][j], i, j);
                }
            }
        }
        else
        {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 8; j++) {
                    chessBoard.occupySpot(pieces[i][j], 7-i, 7-j);
                }
            }

            Piece tpPiece = chessBoard.getSpot(7,3).getPiece();
            chessBoard.occupySpot(chessBoard.getSpot(7,4).getPiece(),7,3);
            chessBoard.occupySpot(tpPiece, 7, 4);

        }

        return chessBoard;
    }




    public Board move(Board chessBoard, int from_i, int from_j, int to_i, int to_j)
    {
        Piece piece = chessBoard.getPieceOnTheSpot(from_i,from_j);

        if (piece.isValidMove(chessBoard, to_i,to_j))
        {
            chessBoard.removePiece(from_i,from_j);
            chessBoard.occupySpot(piece,to_i,to_j);
            piece.moveCount();
        }

        return chessBoard;
    }

    public Board undoMove(Board chessBoard, int from_i, int from_j, int to_i, int to_j)
    {
        Piece piece = chessBoard.getPieceOnTheSpot(to_i, to_j);
        chessBoard.removePiece(to_i,to_j);
        chessBoard.occupySpot(piece,from_i,from_j);
        piece.undoMoveCount();

        return chessBoard;

    }


    public Board remove(Board chessBoard, int from_i, int from_j, int to_i, int to_j)
    {
        Piece piece1 = chessBoard.getPieceOnTheSpot(from_i,from_j);
        Piece piece2 = chessBoard.getPieceOnTheSpot(to_i,to_j);


        if (piece1.isValidRemove(chessBoard, to_i,to_j))
        {
            chessBoard.removePiece(from_i,from_j);
            chessBoard.removePiece(to_i,to_j);
            chessBoard.occupySpot(piece1,to_i,to_j);
            piece1.moveCount();
            piece2.setAvailable(false);
        }

        return chessBoard;
    }


    public Piece[] getAllPieces()
    {
        Piece[] allPieces = new Piece[16];

        for (int i = 0; i<2; i++)
        {
            for (int j=0; j<8; j++)
            {
                    allPieces[(8*i+j)]= pieces[i][j];
            }
        }
        return allPieces;
    }


    public Piece[] getPieceBag()
    {

        return pieceBag;
    }

    public Spot getSpotOfKing()
    {
        Piece[] allPieces = getAllPieces();
        for (int i=0; i<allPieces.length; i++)
        {
            if (allPieces[i]!=null)
                if (allPieces[i].getPieceType()== Piece.Type.KING)
                    return allPieces[i].getSpot();
        }
        return null;
    }

}
