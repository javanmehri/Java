package mos;

/**
 * Created by MOS on 2016-09-10.
 */
public class PieceBag implements java.io.Serializable {

    private Piece[] pieceBag;

   public PieceBag()
   {
       pieceBag = new Piece[16];
   }

    public void add_toPieceBag(Piece piece)
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

    public void remove_lastPieceFromPieceBag()
    {
        for (int i=0; i<16; i++)
        {
            if (pieceBag[i]!=null && pieceBag[i+1]==null)
            {
                pieceBag[i]=null;
            }
        }
    }

    public Piece[] get_pieces()
    {
        return pieceBag;
    }


}
