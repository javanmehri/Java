package mos;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MainStageController {


    // ----------------------------------------
    @FXML
    private Button start_button = new Button();

    // --------------- 1 ----------------------
    @FXML
    private ImageView A1 = new ImageView();

    @FXML
    private ImageView B1 = new ImageView();

    @FXML
    private ImageView C1 = new ImageView();

    @FXML
    private ImageView D1 = new ImageView();

    @FXML
    private ImageView E1 = new ImageView();

    @FXML
    private ImageView F1 = new ImageView();

    @FXML
    private ImageView G1 = new ImageView();

    @FXML
    private ImageView H1 = new ImageView();

    // --------------- 2 ----------------------
    @FXML
    private ImageView A2 = new ImageView();

    @FXML
    private ImageView B2 = new ImageView();

    @FXML
    private ImageView C2 = new ImageView();

    @FXML
    private ImageView D2 = new ImageView();

    @FXML
    private ImageView E2 = new ImageView();

    @FXML
    private ImageView F2 = new ImageView();

    @FXML
    private ImageView G2 = new ImageView();

    @FXML
    private ImageView H2 = new ImageView();

    // --------------- 3 ----------------------
    @FXML
    private ImageView A3 = new ImageView();

    @FXML
    private ImageView B3 = new ImageView();

    @FXML
    private ImageView C3 = new ImageView();

    @FXML
    private ImageView D3 = new ImageView();

    @FXML
    private ImageView E3 = new ImageView();

    @FXML
    private ImageView F3 = new ImageView();

    @FXML
    private ImageView G3 = new ImageView();

    @FXML
    private ImageView H3 = new ImageView();

    // --------------- 4 ----------------------
    @FXML
    private ImageView A4 = new ImageView();

    @FXML
    private ImageView B4 = new ImageView();

    @FXML
    private ImageView C4 = new ImageView();

    @FXML
    private ImageView D4 = new ImageView();

    @FXML
    private ImageView E4 = new ImageView();

    @FXML
    private ImageView F4 = new ImageView();

    @FXML
    private ImageView G4 = new ImageView();

    @FXML
    private ImageView H4 = new ImageView();

    // --------------- 5 ----------------------
    @FXML
    private ImageView A5 = new ImageView();

    @FXML
    private ImageView B5 = new ImageView();

    @FXML
    private ImageView C5 = new ImageView();

    @FXML
    private ImageView D5 = new ImageView();

    @FXML
    private ImageView E5 = new ImageView();

    @FXML
    private ImageView F5 = new ImageView();

    @FXML
    private ImageView G5 = new ImageView();

    @FXML
    private ImageView H5 = new ImageView();

    // --------------- 6 ----------------------
    @FXML
    private ImageView A6 = new ImageView();

    @FXML
    private ImageView B6 = new ImageView();

    @FXML
    private ImageView C6 = new ImageView();

    @FXML
    private ImageView D6 = new ImageView();

    @FXML
    private ImageView E6 = new ImageView();

    @FXML
    private ImageView F6 = new ImageView();

    @FXML
    private ImageView G6 = new ImageView();

    @FXML
    private ImageView H6 = new ImageView();

    // --------------- 7 ----------------------
    @FXML
    private ImageView A7 = new ImageView();

    @FXML
    private ImageView B7 = new ImageView();

    @FXML
    private ImageView C7 = new ImageView();

    @FXML
    private ImageView D7 = new ImageView();

    @FXML
    private ImageView E7 = new ImageView();

    @FXML
    private ImageView F7 = new ImageView();

    @FXML
    private ImageView G7 = new ImageView();

    @FXML
    private ImageView H7 = new ImageView();


    // --------------- 8 ----------------------
    @FXML
    private ImageView A8 = new ImageView();

    @FXML
    private ImageView B8 = new ImageView();

    @FXML
    private ImageView C8 = new ImageView();

    @FXML
    private ImageView D8 = new ImageView();

    @FXML
    private ImageView E8 = new ImageView();

    @FXML
    private ImageView F8 = new ImageView();

    @FXML
    private ImageView G8 = new ImageView();

    @FXML
    private ImageView H8 = new ImageView();

    // ----------------------------------------------------

    private ImageView[][] imageViewsBoard = new ImageView[8][8];

    //private Board board = new Board();

    private Game game;


    private boolean isAnyPieceSelected = false;
    private ImageView selectedPiece;
    private ImageView moveToSopt;


    // ----------------------------------------------------
    @FXML
    private void initialize()
    {
        game = new Game("Player 1", "Player 2");

        setUpTheBoard();


    }

    @FXML
    private void mouseClick(Event event)
    {
        //System.out.println("click");
        ImageView selected = (ImageView)event.getSource();
        if (isAnyPieceSelected == false)
            select(event);

        else if (isAnyPieceSelected == true )
            if (selectedPiece == selected)
                de_select();

        else if (isAnyPieceSelected == true)
            if (selectedPiece != selected && isImageViewEmpty(selected))
            {
                select(event);
            }
    }


    @FXML
    private void mouseOn()
    {
    }

    @FXML
    private void moveOut()
    {

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
        imageViewsBoard[0][3] = D1;
        imageViewsBoard[0][4] = E1;
        imageViewsBoard[0][5] = F1;
        imageViewsBoard[0][6] = G1;
        imageViewsBoard[0][7] = H1;

        imageViewsBoard[1][0] = A2;
        imageViewsBoard[1][1] = B2;
        imageViewsBoard[1][2] = C2;
        imageViewsBoard[1][3] = D2;
        imageViewsBoard[1][4] = E2;
        imageViewsBoard[1][5] = F2;
        imageViewsBoard[1][6] = G2;
        imageViewsBoard[1][7] = H2;

        imageViewsBoard[2][0] = A3;
        imageViewsBoard[2][1] = B3;
        imageViewsBoard[2][2] = C3;
        imageViewsBoard[2][3] = D3;
        imageViewsBoard[2][4] = E3;
        imageViewsBoard[2][5] = F3;
        imageViewsBoard[2][6] = G3;
        imageViewsBoard[2][7] = H3;

        imageViewsBoard[3][0] = A4;
        imageViewsBoard[3][1] = B4;
        imageViewsBoard[3][2] = C4;
        imageViewsBoard[3][3] = D4;
        imageViewsBoard[3][4] = E4;
        imageViewsBoard[3][5] = F4;
        imageViewsBoard[3][6] = G4;
        imageViewsBoard[3][7] = H4;

        imageViewsBoard[4][0] = A5;
        imageViewsBoard[4][1] = B5;
        imageViewsBoard[4][2] = C5;
        imageViewsBoard[4][3] = D5;
        imageViewsBoard[4][4] = E5;
        imageViewsBoard[4][5] = F5;
        imageViewsBoard[4][6] = G5;
        imageViewsBoard[4][7] = H5;

        imageViewsBoard[5][0] = A6;
        imageViewsBoard[5][1] = B6;
        imageViewsBoard[5][2] = C6;
        imageViewsBoard[5][3] = D6;
        imageViewsBoard[5][4] = E6;
        imageViewsBoard[5][5] = F6;
        imageViewsBoard[5][6] = G6;
        imageViewsBoard[5][7] = H6;

        imageViewsBoard[6][0] = A7;
        imageViewsBoard[6][1] = B7;
        imageViewsBoard[6][2] = C7;
        imageViewsBoard[6][3] = D7;
        imageViewsBoard[6][4] = E7;
        imageViewsBoard[6][5] = F7;
        imageViewsBoard[6][6] = G7;
        imageViewsBoard[6][7] = H7;

        imageViewsBoard[7][0] = A8;
        imageViewsBoard[7][1] = B8;
        imageViewsBoard[7][2] = C8;
        imageViewsBoard[7][3] = D8;
        imageViewsBoard[7][4] = E8;
        imageViewsBoard[7][5] = F8;
        imageViewsBoard[7][6] = G8;
        imageViewsBoard[7][7] = H8;


        setUpTiles();
    }

    private void setUpTiles()
    {
        for (int i = 0; i<8; i++)
        {
            for(int j = 0; j<8; j++)
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


    private void updateTheBoard()
    {
        Spot spot;
        Piece piece;
        ImageView imageViewOfSpot;
        Piece.Type type;
        Piece.Color pieceColor;
        Piece.Color backgroundColor;


        for (int i = 0; i<8; i++)
        {
            for (int j = 0; j<8; j++)
            {
                spot = game.getBoard().getSpot(i,j);
                imageViewOfSpot = getImageViewOfSpot(spot);
                backgroundColor = Piece.Color.WHITE;
                //System.out.println(i%2);
                if ((i+j)%2 == 0)
                {
                    backgroundColor = Piece.Color.BLACK;
                }

                if (spot.isOccupied())
                {
                    piece = spot.getPiece();
                    type = piece.getPieceType();
                    pieceColor = piece.getColor();

                    update_Spot_On_Screen(imageViewOfSpot, type, pieceColor, backgroundColor);

                }
                else
                {
                    if(backgroundColor== Piece.Color.BLACK)
                        imageViewOfSpot.setImage(Img.Tile_Black);
                    else
                        imageViewOfSpot.setImage(Img.Tile_White);
                }

            }
        }


    }

    private void update_Spot_On_Screen(ImageView imageView, Piece.Type type, Piece.Color pieceColor, Piece.Color backgroundColor)
    {
        imageView.setImage(Img.getPiece(type,pieceColor,backgroundColor));
        //System.out.println(type);
    }

    private void select(Event event)
    {
        //System.out.println(" > Select :  i = "+get_XIndex(selectedPiece));

        if (!isAnyPieceSelected && !isImageViewEmpty((ImageView)event.getSource()))
        {
            selectedPiece = (ImageView)event.getSource();
            selectedPiece.setOpacity(0.5);
            isAnyPieceSelected = true;
            System.out.println("Select");
        }
        else if (isAnyPieceSelected && isImageViewEmpty((ImageView)event.getSource()))
        {
            moveToSopt = (ImageView)event.getSource();
            System.out.println("Select moveTo");
            System.out.println(" > Select : i ="+get_XIndex(selectedPiece)+ "  j = "+get_YIndex(selectedPiece) );
            System.out.println(" > Select move to : i ="+get_XIndex(moveToSopt)+ "  j = "+get_YIndex(moveToSopt) );

            //System.out.println(get_XIndex(moveToSopt));
            move();
            updateTheBoard();

        }
        //System.out.println(isImageViewEmpty((ImageView)event.getSource()));
        //System.out.println(isAnyPieceSelected);
        //System.out.println(selectedPiece.getImage());

    }

    private boolean isImageViewEmpty(ImageView imageView)
    {
        if (imageView.getImage()==Img.Tile_Black || imageView.getImage()==Img.Tile_White)
            return true;
        return false;
    }


    private void de_select()
    {
        selectedPiece.setOpacity(1);
        isAnyPieceSelected = false;
        System.out.println("DeSelect");
    }

    private void move()
    {
        if (isAnyPieceSelected && moveToSopt!=null)
        {
            //System.out.println(selectedPiece);
            Piece.Color color = get_Color_Of(selectedPiece);
            Player player;
            if (color == Piece.Color.WHITE)
                player = game.getPlayer_White();
            else
                player = game.getPlayer_Black();

            int from_i = get_XIndex(selectedPiece);
            int from_j = get_YIndex(selectedPiece);
            int to_i = get_XIndex(moveToSopt);
            int to_j = get_YIndex(moveToSopt);

            game.move(player, from_i, from_j, to_i, to_j);

            de_select();
        }
    }

    private int get_XIndex(ImageView imageView)
    {
        for (int i =0; i<8; i++)
            for (int j=0; j<8; j++)
                if (imageViewsBoard[i][j].equals(imageView))
                {
                    return i;
                }

        return -1;
    }

    private int get_YIndex(ImageView imageView)
    {
        for (int i =0; i<8; i++)
            for (int j=0; j<8; j++)
                if (imageViewsBoard[i][j].equals(imageView))
                {
                    return j;
                }

        return -1;
    }

    private Piece.Color get_Color_Of(ImageView imageView)
    {
        int i = get_XIndex(imageView);
        int j = get_YIndex(imageView);
        //System.out.println(" > get_Color_OF : i ="+i+"  j = "+j);
        //System.out.println(board.getSpot(i,j).getPiece());
        Piece.Color color = game.getBoard().getSpot(i,j).getPiece().getColor();

        return color;
    }




}
