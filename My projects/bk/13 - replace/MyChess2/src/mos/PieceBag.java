package mos;

/**
 * Created by MOS on 2016-09-10.
 */
public class PieceBag implements java.io.Serializable {

    private Piece[] pieces;


    public PieceBag()
   {
       pieces = new Piece[16];
   }


    public void add_toPieceBag(Piece piece)
    {
        piece.setAvailable(false);

        for (int i=0; i<16 ; i++)
        {
            if (pieces[i]==null)
            {
                pieces[i] = piece;
                return;
            }
        }
    }


    public void remove_lastPieceFromPieceBag()
    {
        for (int i=0; i<16; i++)
        {
            if (pieces[i]!=null && pieces[i+1]==null)
            {
                pieces[i]=null;
            }
        }
    }


    public String[] get_types()
    {
        String[] types = new String[4];
        int i =0;

        if (hasAny(Piece.Type.QUEEN))
        {
            types[i] = "Queen";
            i++;
        }
        if (hasAny(Piece.Type.BISHOP))
        {
            types[i] = "Bishop";
            i++;
        }
        if (hasAny(Piece.Type.KNIGHT))
        {
            types[i] = "Knight";
            i++;
        }
        if (hasAny(Piece.Type.ROOK))
        {
            types[i] = "Rook";
            i++;
        }

        return types;
    }


    private Boolean hasAny(Piece.Type type)
    {
        boolean ans = false;

        for (int i=0; i<pieces.length; i++)
            if (pieces[i]!=null )
                if (pieces[i].getPieceType()== type)
                ans = true;

        return ans;
    }


    public Piece[] get_pieces()
    {
        return pieces;
    }


    public Piece pick(String selected)
    {
        Piece piece = null;

        if (selected.equals("Queen"))
            piece = pick(Piece.Type.QUEEN);

        if (selected.equals("Bishop"))
            piece = pick(Piece.Type.BISHOP);

        if (selected.equals("Knight"))
            piece = pick(Piece.Type.KNIGHT);

        if (selected.equals("Rook"))
            piece = pick(Piece.Type.ROOK);

        return piece;
    }


    public Piece pick(Piece.Type type)
    {
        Piece piece = null;

        for (int i=0; i<pieces.length; i++)
            if (pieces[i]!=null)
                if (pieces[i].getPieceType()== type)
                {
                    piece = pieces[i];
                    pieces[i] = null;
                    return piece;
                }

        return null;
    }





}
