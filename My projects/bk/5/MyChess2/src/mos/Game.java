package mos;

import java.io.IOException;
import java.util.Date;

/**
 * Created by MOS on 2016-07-04.
 */
public class Game {

    Date date = new Date();

    private Board board;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private Player selectedPlayer;

    private Game parent;
    private Game[] children;

    public String note;
    private String comment;


    public Game(String player_White, String player_Black) {
        board = new Board();

        player1 = new Player(player_White, Piece.Color.WHITE);
        player2 = new Player(player_Black, Piece.Color.BLACK);

        activePlayer = getPlayer_White();

        //board = player1.setUpPieces(board);
        //board = player2.setUpPieces(board);

        player1.setUpPieces(board);
        player2.setUpPieces(board);

        note = " Start Time: " + date+"\n\n"+
                " White: "+ player1.getName()+"\t"+" Black: "+player2.getName()+"\n\n";

    }


    public Player getActivePlayer()
    {
        return activePlayer;
    }


    public void setSelectedPlayer(Player player)
    {
        selectedPlayer = player;
    }


    public void switchTheActivePlayer() {
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

    public boolean isValidMove(int from_i, int from_j, int to_i, int to_j) {

        // -----------------------------------------------------------------------------
        // The following block manages the validation of the (KING and ROOK) move.
        // -----------------------------------------------------------------------------
        if (isAValidKingRook(from_i, from_j, to_i, to_j))
            return true;

        /*
        if (getPlayer(to_i,to_j)!=null)
        {
            if (getActivePlayer() == getPlayer(to_i, to_j))
            {
                Piece piece1 = getPiece(from_i, from_j);
                Piece piece2 = getPiece(to_i, to_j);
                //Report.report(" << Both pices are not null >> ");
                //Report.report(" >> piece1 : "+piece1.getPieceType() + "  piece2 : "+ piece2.getPieceType());
                if ((piece1.getPieceType() == Piece.Type.KING && piece2.getPieceType() == Piece.Type.ROOK) ||
                        (piece2.getPieceType() == Piece.Type.KING && piece1.getPieceType() == Piece.Type.ROOK))
                {
                    //Report.report(" << Pices are King and Rook >> ");
                    if (!piece1.isPlayed() && !piece2.isPlayed()) {
                        //Report.report(" << Both King and Rook are not moved before >> ");
                        return true;
                    }
                    else
                        return false;
                }
                else
                    return false;

            }

        }

        */

        else if (getPlayer(from_i, from_j) == getPlayer(to_i, to_j))
            return false;

        else if (board.getPieceOnTheSpot(from_i,from_j).isValidMove(board,to_i,to_j)==false || isAnyPieceOnTheWay(from_i,from_j,to_i,to_j))
            return false;


        else
            return true;
    }


    public boolean move(Player player, int from_i, int from_j, int to_i, int to_j) throws IOException {

        Report.open_report(3,"Came.move(...)");

        if (player != activePlayer)
        {
            Report.report(3, "Player is not active!");
            Report.close_report(3);
            return false;
        }

        // -----------------------------------------------------------------------------
        // The following block manages Moves.
        // -----------------------------------------------------------------------------
        if (isValidMove(from_i, from_j, to_i,to_j))
        {

            // -----------------------------------------------------------------------------
            // The following block manages the King-Rook Move if it a valid one.
            // -----------------------------------------------------------------------------
            if (isAValidKingRook(from_i, from_j, to_i, to_j))
            {
                // ...
                //Report.report(">>> *** King and Rook *** <<<");
                comment = "*** KING-ROOK ***";
                board = player.KingRookMove(board, from_i, from_j, to_i, to_j);

                if (stillCheck())
                {
                    board = player.undo_KingRookMove(board, from_i, from_j, to_i, to_j);
                    comment = " ! Not a Valid Move !";
                    Report.report(3, " Not a valid move! It will cause a CHECK!");
                    Report.close_report(3);

                    return false;
                }

                noteKingRook(player);
                Report.report(3, "*** KING-ROOK ***");
                Report.close_report(3);
                switchTheActivePlayer();

                return true;
            }

            // -----------------------------------------------------------------------------
            // The following block manages an ordinary Move, If it's a valid one.
            // -----------------------------------------------------------------------------
            else if (getPlayer(from_i, from_j) != getPlayer(to_i, to_j))
            {
                board = player.move(board, from_i, from_j, to_i, to_j);

                if (isCheck()) {
                    board = player.undoMove(board, from_i, from_j, to_i, to_j);
                    comment = " ! Not a Valid Move !";
                    Report.report(3, " Not a valid move! It will cause a CHECK!");
                    Report.close_report(3);

                    return false;
                }

                noteThePlay(player, to_i, to_j);
                Report.report(3, "The Move is safe and done");
                Report.close_report(3);
                switchTheActivePlayer();

                return true;
            }
        }

        // -----------------------------------------------------------------------------
        // The following block manages Removes.
        // -----------------------------------------------------------------------------
        else if (getPlayer(to_i,to_j)!=activePlayer)
        {
                if (isValidRemove(from_i, from_j, to_i,to_j))
                {
                    board = player.remove(board, from_i, from_j, to_i, to_j);

                    if (isCheck())
                    {
                        board = player.undoRemove(board, from_i, from_j, to_i, to_j);
                        comment = " ! Not Valid Remove !";
                        Report.report(3, " Not a valid Remove! It will cause a CHECK!");
                        Report.close_report(3);

                        return false;
                    }

                    noteThePlay(player, to_i, to_j);
                    Sounds.play(Sounds.SoundEffects.REMOVE);
                    Report.report(3, "The Remove is safe and done");
                    Report.close_report(3);
                    switchTheActivePlayer();

                    return true;
                }
        }


        Report.close_report(3);
        return false;

    }

    // =================================================================================================================

    public Spot[] getSpotsOnTheWay(int from_i, int from_j, int to_i, int to_j) {
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

    public boolean isAnyPieceOnTheWay(int from_i, int from_j, int to_i, int to_j) {

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


    public boolean isAnyPieceOnTheWay(Piece piece, Spot spot)
    {
        int from_i = piece.getSpot().get_X();
        int from_j = piece.getSpot().get_Y();
        int to_i = spot.get_X();
        int to_j = spot.get_Y();

        return isAnyPieceOnTheWay(from_i, from_j, to_i, to_j);
    }


    public Player getPlayer(int i, int j) {
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



    public boolean isValidRemove(int from_i, int from_j, int to_i, int to_j) {

        if (getPlayer(from_i,from_j)==getPlayer(to_i,to_j))
            return false;

        else if (board.getPieceOnTheSpot(from_i,from_j).isValidRemove(board,to_i,to_j)==false || isAnyPieceOnTheWay(from_i,from_j,to_i,to_j))
            return false;

        else
            return true;

    }


    public Player getUnactivePlayer() {
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


    private boolean isCheck(Game game) {

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
                            //comment = "CHECK";
                            //Report.report(" >> Check <<");
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


    private boolean stillCheck(Game game) {
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
                            //Report.report(" >> Check <<");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public Spot[] getAllValidMoves(Spot from_spot) {
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


    public Piece[] getAllValidRemoves(Piece piece) {
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


    public Game clone() {


        Game newGame = new Game(this.getPlayer_White().getName(), this.getPlayer_Black().getName());
        newGame.setBoard(this.getBoard().clone());

        if (this.activePlayer==getPlayer_White())
                newGame.activePlayer = newGame.getPlayer_White();
        else
                newGame.activePlayer = newGame.getPlayer_Black();

        // set selected Payer
       // ...
        return newGame;

        //return (Game)super.clone();
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

    private void noteThePlay(Player player, int to_i, int to_j)
    {
        note = note+ player.getTotalMoves()+". " +player.getColor().toString().substring(0,1)+" :  ";
        Piece piece = board.getSpot(to_i,to_j).getPiece();
        note = note + piece.getPieceType().toString().substring(0,2) + " to ";

        switch (to_j)
        {
            case 0: note = note + "A"; break;
            case 1: note = note + "B"; break;
            case 2: note = note + "C"; break;
            case 3: note = note + "D"; break;
            case 4: note = note + "E"; break;
            case 5: note = note + "F"; break;
            case 6: note = note + "G"; break;
            case 7: note = note + "H"; break;
        }
        note = note + (to_i+1);

        if (player.getColor()== Piece.Color.WHITE)
            note = note + "\t";
        else
            note = note +"\n";

    }


    private void noteKingRook(Player player)
    {
        note = note+ player.getTotalMoves()+". " +player.getColor().toString().substring(0,1)+" :  ";
        note = note + "K-R";

        if (player.getColor()== Piece.Color.WHITE)
            note = note + "\t\t";
        else
            note = note +"\n";
    }


    public void comment(String str)
    {
        comment = str;
    }

    public String getComment()
    {
        return comment;
    }

    public Piece getPiece(int i, int j)
    {
        if (board.getSpot(i, j).isOccupied())
            return board.getSpot(i , j).getPiece();

        return null;
    }


    private boolean isAValidKingRook(int from_i, int from_j, int to_i, int to_j)
    {
        if (getPlayer(to_i,to_j)!=null)
        {
            if (getActivePlayer() == getPlayer(to_i, to_j))
            {
                Piece piece1 = getPiece(from_i, from_j);
                Piece piece2 = getPiece(to_i, to_j);
                //Report.report(" << Both pices are not null >> ");
                //Report.report(" >> piece1 : "+piece1.getPieceType() + "  piece2 : "+ piece2.getPieceType());
                if ((piece1.getPieceType() == Piece.Type.KING && piece2.getPieceType() == Piece.Type.ROOK) ||
                        (piece2.getPieceType() == Piece.Type.KING && piece1.getPieceType() == Piece.Type.ROOK))
                {
                    //Report.report(" << Pices are King and Rook >> ");
                    if (!piece1.isPlayed() && !piece2.isPlayed() && Math.abs(from_j-to_j)==4) {
                        // apply distance condition here !
                        Report.report(" << ! King and Rook move validated ! >> ");
                        return true;
                    }

                }


            }

        }

        return false;
    }


}




