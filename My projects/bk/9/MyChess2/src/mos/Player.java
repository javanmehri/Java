package mos;

/**
 * Created by MOS on 2016-07-04.
 */

public class Player implements java.io.Serializable {

    private String name;
    private Piece.Color color;
    private Piece[][] pieces;
    private Piece[] pieceBag;
    private int totalMoves = 0;

    private Piece lastRemoved;
    private String KingRookNote = "";

    public Player(String name, Piece.Color color)
    {
        this.name = name;
        this.color = color;
        pieceBag= new Piece[16];
        pieces = new Piece[2][8];

        makePieces();
    }

    public String getName(){ return name; }

    public Piece.Color getColor(){ return color; }

    private void makePieces()
    {
        pieces[0][0] = new Rook(this);
        pieces[0][1] = new Knight(this);
        pieces[0][2] = new Bishop(this);
        pieces[0][3] = new Queen(this);
        pieces[0][4] = new King(this);
        pieces[0][5] = new Bishop(this);
        pieces[0][6] = new Knight(this);
        pieces[0][7] = new Rook(this);

        for (int i=0; i<8; i++) { pieces[1][i] = new Pawn(this); }

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
        Report.open_report(4, "Player.move(...)");
        Piece piece = chessBoard.getPieceOnTheSpot(from_i,from_j);

        if (piece.isValidMove(chessBoard, to_i,to_j))
        {
            chessBoard.removePiece(from_i,from_j);
            chessBoard.occupySpot(piece,to_i,to_j);
            piece.moveCount();
            totalMoves++;
        }

        Report.close_report(4);
        return chessBoard;
    }


    public Board KingRookMove(Board chessBoard, int from_i, int from_j, int to_i, int to_j) {

        // It is assumed that it is a valid king-Rook move!

        Piece fromPiece = chessBoard.getSpot(from_i,from_j).getPiece();
        Piece toPiece = chessBoard.getSpot(to_i,to_j).getPiece();

        Piece King ;
        Piece Rook ;

        if (from_j == 4)
        {
            King = chessBoard.getPieceOnTheSpot(from_i, from_j);
            Rook = chessBoard.getPieceOnTheSpot(to_i, to_j);
        }

        else {
            return chessBoard;
        }

        int K_i = King.getSpot().get_X();
        int K_j = King.getSpot().get_Y();
        int R_i = Rook.getSpot().get_X();
        int R_j = Rook.getSpot().get_Y();

        if (R_j == 0)
        {

            chessBoard.removePiece( K_i, K_j);
            chessBoard.occupySpot(King, K_i, K_j-2);

            chessBoard.removePiece(R_i,R_j);
            chessBoard.occupySpot(Rook, R_i , R_j+3);

            KingRookNote = "O-O-O";
        }
        else
        {
            chessBoard.removePiece( K_i, K_j);
            chessBoard.occupySpot(King, K_i, K_j+2);

            chessBoard.removePiece(R_i,R_j);
            chessBoard.occupySpot(Rook, R_i , R_j-2);

            KingRookNote = "O-O";
        }


        King.moveCount();
        Rook.moveCount();
        totalMoves++;

        return chessBoard;
    }

    /*
    public Board undo_KingRookMove(Board chessBoard, int from_i, int from_j, int to_i, int to_j) {

        //Report.report("  Player.KingRookMove "+"\n"+"  {");
        Report.open_report(4, "Player.KingRookMove(...)");

        Piece King ;
        Piece Rook ;

        if (from_j == 4)
        {
            King = chessBoard.getPieceOnTheSpot(from_i, from_j);
            Rook = chessBoard.getPieceOnTheSpot(to_i, to_j);
        }

        else if (to_j == 4)
        {
            King = chessBoard.getPieceOnTheSpot(to_i, to_j);
            Rook = chessBoard.getPieceOnTheSpot(from_i, from_j);
        }

        else {
            Report.close_report(4);
            //Report.report("  }");
            return chessBoard;
        }

        int i = King.getSpot().get_X();
        int j = King.getSpot().get_Y();

        chessBoard.removePiece( i, j);
        chessBoard.occupySpot(King, i, j-1);

        i = Rook.getSpot().get_X();
        j = Rook.getSpot().get_Y();

        chessBoard.removePiece(i,j);
        chessBoard.occupySpot(Rook, i , j+1);

        King.undoMoveCount();
        Rook.undoMoveCount();
        totalMoves--;

        //Report.report("  }");
        Report.close_report(4);
        return chessBoard;
    }

    */

        public void move(Board chessBoard, Spot from, Spot to)
    {
        int from_i = from.get_X();
        int from_j = from.get_Y();
        int to_i = to.get_X();
        int to_j = to.get_Y();
        move(chessBoard, from_i, from_j, to_i, to_j);
    }


    public Board undoMove(Board chessBoard, int from_i, int from_j, int to_i, int to_j)
    {
        //Report.report("> Player.undoMove ");
        Report.open_report(4, "Player.undoMove(...)");

        Piece piece = chessBoard.getPieceOnTheSpot(to_i, to_j);
        chessBoard.removePiece(to_i,to_j);
        chessBoard.occupySpot(piece,from_i,from_j);
        piece.undoMoveCount();
        totalMoves --;

        Report.close_report(4);
        return chessBoard;

    }

    public Board undoRemove(Board chessBoard, int from_i, int from_j, int to_i, int to_j)
    {
        //Report.report("> Player.undoRemove ");
        Report.open_report(4, "Player.undoRemove(...)");

        Piece piece = chessBoard.getPieceOnTheSpot(to_i, to_j);
        chessBoard.removePiece(to_i,to_j);
        chessBoard.occupySpot(piece,from_i,from_j);
        lastRemoved.setAvailable(true);
        chessBoard.occupySpot(lastRemoved, to_i, to_j);
        piece.undoMoveCount();

        remove_lastPieceFromPieceBag();
        totalMoves --;

        Report.close_report(4);
        return chessBoard;

    }

    public Board remove(Board chessBoard, int from_i, int from_j, int to_i, int to_j)
    {
        //Report.report("> Player.remove ");
        Report.open_report(4, "Player.remove(...)");

        Piece piece1 = chessBoard.getPieceOnTheSpot(from_i,from_j);
        Piece piece2 = chessBoard.getPieceOnTheSpot(to_i,to_j);

        if (piece1.isValidRemove(chessBoard, to_i,to_j))
        {
            chessBoard.removePiece(from_i,from_j);
            chessBoard.removePiece(to_i,to_j);
            chessBoard.occupySpot(piece1,to_i,to_j);
            piece1.moveCount();
            piece2.setAvailable(false);

            add_toPieceBag(piece2);
            lastRemoved = piece2;
            totalMoves ++;
        }

        Report.close_report(4);
        return chessBoard;
    }

    private void add_toPieceBag(Piece piece)
    {
        for (int i=0; i<16 ; i++)
        {
            if (pieceBag[i]==null)
            {
                pieceBag[i] = piece;
                return;
            }
        }
    }

    private void remove_lastPieceFromPieceBag()
    {
        for (int i=0; i<16; i++)
        {
            if (pieceBag[i]!=null && pieceBag[i+1]==null)
            {
                pieceBag[i]=null;
            }
        }
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


    public Piece[] getAvailablePieces()
    {
        Piece[] availablePieces = new Piece[16];
        Piece[] allPieces = getAllPieces();
        int count = 0;

        for (int i=0; i<16; i++)
        {
            if (allPieces[i].isAvailable())
                availablePieces[count] = allPieces[i];
        }

        return availablePieces;
    }


    public Piece[] get_RemovedPieces()
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

    public int getTotalMoves()
    {
        return totalMoves;
    }


    public String getKingRookNote()
    {
        return KingRookNote;
    }

}
