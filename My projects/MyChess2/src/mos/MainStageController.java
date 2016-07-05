package mos;

import javafx.fxml.FXML;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
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
        game = new Game();

        //game.start();

        setUpTheBoard();

        updateTheBoard();

        //game.getBoard().occupySpot(game.king_B, 0, 0);

    }

    @FXML
    private void moveIn()
    {

        getImageViewOfSpot(board.getSpot(0,0)).setImage(Img.Tile_Black);
        rec.setStrokeWidth(3);
        rec.setStroke(Paint.valueOf("Blue"));
        //getImageViewOfSpot(board.getSpot(0,0)).setSmooth(true);
        //A1.setImage(Img.white_Rook);
        //A1.setLayoutX(5);

    }

    @FXML
    private void moveOut()
    {

        getImageViewOfSpot(board.getSpot(0,0)).setImage(Img.Tile_White);
        rec.setStrokeWidth(1);
        rec.setStroke(Paint.valueOf("Black"));
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

        System.out.println(piece.getPieceType());

        if (piece.getPieceType() == Piece.Type.ROOK)
        {
            imageViewOfSpot.setImage(Img.Rook_W_in_W);
        }

    }


}
