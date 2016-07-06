package mos;

import javafx.fxml.FXML;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MainStageController {


    // ----------------------------------------------------
    @FXML
    private ImageView A1 = new ImageView(Img.Tile_White);

    @FXML
    Rectangle rec = new Rectangle();

    // ----------------------------------------------------

    private ImageView[][] imageViewsBoard = new ImageView[8][8];

    private Board board = new Board();

    private Game game;

    ColorAdjust colorAdjustSelect = new ColorAdjust();

    // ----------------------------------------------------
    @FXML
    private void initialize()
    {
        game = new Game("Player 1", "Player 2");

        setUpTheBoard();

        updateTheBoard();

        //game.getBoard().occupySpot(game.king_B, 0, 0);

    }

    @FXML
    private void mouseClick()
    {

        //getImageViewOfSpot(board.getSpot(0,0)).setImage(Img.Tile_Black);
        //rec.setStrokeWidth(5);
        //rec.setStroke(Paint.valueOf("Blue"));

        //System.out.println(rec.getFill().equals(Paint.valueOf("#e521ff1f")));
        if (rec.getFill().equals(Paint.valueOf("#e521ff1f")))
        {
            rec.setFill(Paint.valueOf("#e121ff00"));
        }
        else
        {
            rec.setFill(Paint.valueOf("#e521ff1f"));
        }
        //getImageViewOfSpot(board.getSpot(0,0)).setSmooth(true);
        //A1.setImage(Img.white_Rook);
        //A1.setLayoutX(5);

    }

    @FXML
    private void mouseOn()
    {
        //rec.setStrokeWidth(3);
        //rec.setStroke();
    }

    @FXML
    private void moveOut()
    {

        if((int)rec.getStrokeWidth()==3)
        {
            //rec.setStrokeWidth(1);

        }
        //getImageViewOfSpot(board.getSpot(0,0)).setImage(Img.Tile_White);
        //rec.setStrokeWidth(1);
        //rec.setStroke(Paint.valueOf("Black"));
    }

    // ----------------------------------------------------
    private ImageView getImageViewOfSpot(Spot spot)
    {
        int x = spot.get_X();
        int y = spot.get_Y();
        return imageViewsBoard[x][y];
    }


    private void setUpTheBoard()
    {
        imageViewsBoard[0][0] = A1;
    }


    private void updateTheBoard()
    {
        Spot spot;
        Piece piece;
        ImageView imageViewOfSpot;

        spot = game.getBoard().getSpot(0,0);
        imageViewOfSpot = getImageViewOfSpot(spot);
        piece = spot.getPiece();


        //game.getBoard().getSpot(0,0).getPiece().getPieceType();

        //System.out.println(piece.getPieceType());

        if (piece.getPieceType() == Piece.Type.ROOK)
        {
            imageViewOfSpot.setImage(Img.Rook_W_in_W);
        }

    }


}
