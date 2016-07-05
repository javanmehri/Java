package mos;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class MainStageController {




    @FXML
    private ImageView A1_ImageView = new ImageView(Img.black_Rook);

    private ImageView[][] imageViewsBoard = new ImageView[8][8];

    private Board board = new Board();

    private Game game;

    @FXML
    private void initialize()
    {

        game = new Game();
        game.start();

        updateTheBoard();

        //game.getBoard().occupySpot(game.king_B, 0, 0);

    }

    @FXML
    private void moveIn()
    {

        getImageViewOfSpot(board.getSpot(0,0)).setImage(Img.white_Rook);
        //A1.setImage(Img.white_Rook);
        //A1.setLayoutX(5);

    }

    @FXML
    private void moveOut()
    {
        getImageViewOfSpot(board.getSpot(0,0)).setImage(Img.black_Rook);
    }

    private ImageView getImageViewOfSpot(Spot spot)
    {
        int x = spot.get_X();
        int y = spot.get_Y();
        return imageViewsBoard[x][y];
    }



    private void updateTheBoard()
    {
        imageViewsBoard[0][0] = A1_ImageView;

        //game.getBoard().getSpot(0,0).getPiece().getPieceType();

        if (game.getBoard().getSpot(0,0).getPiece().getPieceType() == Piece.Type.KING)
        {
            imageViewsBoard[0][0].setImage(Img.black_King);
        }


    }

    //private void setUpTheBoard()  { }

}
