package mos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MainStageController {


    // ----------------------------------------------------
    @FXML
    private Button start_button = new Button();

    @FXML
    private ImageView A1 = new ImageView();

    @FXML
    private ImageView B1 = new ImageView();

    @FXML
    private ImageView C1 = new ImageView();

    @FXML
    Rectangle rec_A1 = new Rectangle();

    @FXML
    Rectangle rec_B1 = new Rectangle();

    @FXML
    Rectangle rec_C1 = new Rectangle();

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

        //updateTheBoard();

        //game.getBoard().occupySpot(game.king_B, 0, 0);

    }

    @FXML
    private void mouseClick()
    {

        //getImageViewOfSpot(board.getSpot(0,0)).setImage(Img.Tile_Black);
        //rec.setStrokeWidth(5);
        //rec.setStroke(Paint.valueOf("Blue"));

        //System.out.println(rec.getFill().equals(Paint.valueOf("#e521ff1f")));
        if (rec_A1.getFill().equals(Paint.valueOf("#e521ff1f")))
        {
            rec_A1.setFill(Paint.valueOf("#e121ff00"));
        }
        else
        {
            rec_A1.setFill(Paint.valueOf("#e521ff1f"));
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

        if((int)rec_A1.getStrokeWidth()==3)
        {
            //rec.setStrokeWidth(1);

        }
        //getImageViewOfSpot(board.getSpot(0,0)).setImage(Img.Tile_White);
        //rec.setStrokeWidth(1);
        //rec.setStroke(Paint.valueOf("Black"));
    }

    @FXML
    private void start()
    {
        updateTheBoard();
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
        imageViewsBoard[0][1] = B1;
        imageViewsBoard[0][2] = C1;

        setUpTiles();
    }

    private void setUpTiles()
    {
        for (int i = 0; i<1; i++)
        {
            for(int j = 0; j<3; j++)
            {
                if (!board.getSpot(i,j).isOccupied())
                {
                    if ((i+j)%2 == 0)
                    {
                        imageViewsBoard[i][j].setImage(Img.Tile_Black);
                    }
                    else
                    {
                        imageViewsBoard[i][j].setImage(Img.Tile_White);
                    }

                }
            }
        }
    }


    private void updateTheBoard()
    {
        Spot spot;
        Piece piece;
        ImageView imageViewOfSpot;
        Piece.Type type;
        Piece.Color pieceColor;
        Piece.Color backgroundColor;

        for (int i = 0; i<3; i++)
        {
            spot = game.getBoard().getSpot(0,i);
            imageViewOfSpot = getImageViewOfSpot(spot);
            piece = spot.getPiece();
            type = piece.getPieceType();
            pieceColor = piece.getColor();
            backgroundColor = Piece.Color.WHITE;

            //System.out.println(i%2);
            if ((i%2) == 0)
            {
                backgroundColor = Piece.Color.BLACK;
            }

            update_Spot_On_Screen(imageViewOfSpot, type, pieceColor, backgroundColor);


        }


    }

    private void update_Spot_On_Screen(ImageView imageView, Piece.Type type, Piece.Color pieceColor, Piece.Color backgroundColor)
    {
        imageView.setImage(Img.getPiece(type,pieceColor,backgroundColor));
    }


}
