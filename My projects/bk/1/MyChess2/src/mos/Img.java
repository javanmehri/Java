package mos;

//==============================================================================
// File         : Img.java
//
// Current Author: Mostafa Javanmehri
//
// Contact Info: m.javanmehri@gmail.com
//
// Purpose : This class is responsible for loading the needed images from their files.
//
// Dependencies:
//
// Modification Log :
//    --> Created Jul-03-2016
//    --> Updated Aug-21-2016
//
// =============================================================================

import javafx.scene.image.Image;

public class Img
{

    public static final Image Tile_Black = new Image("file:./img/Black.png");
    public static final Image Tile_White = new Image("file:./img/White.png");

    private static final Image Pawn_B_in_B = new Image("file:./img/Pawn_B_in_B.png");
    private static final Image Pawn_W_in_W = new Image("file:./img/Pawn_W_in_W.png");
    private static final Image Pawn_B_in_W = new Image("file:./img/Pawn_B_in_W.png");
    private static final Image Pawn_W_in_B = new Image("file:./img/Pawn_W_in_B.png");

    private static final Image King_B_in_B = new Image("file:./img/king_B_in_B.png");
    private static final Image King_W_in_W = new Image("file:./img/king_W_in_W.png");
    private static final Image King_B_in_W = new Image("file:./img/king_B_in_W.png");
    private static final Image King_W_in_B = new Image("file:./img/king_W_in_B.png");

    private static final Image Queen_B_in_B = new Image("file:./img/Queen_B_in_B.png");
    private static final Image Queen_W_in_W = new Image("file:./img/Queen_W_in_W.png");
    private static final Image Queen_B_in_W = new Image("file:./img/Queen_B_in_W.png");
    private static final Image Queen_W_in_B = new Image("file:./img/Queen_W_in_B.png");

    private static final Image Bishop_B_in_B = new Image("file:./img/Bishop_B_in_B.png");
    private static final Image Bishop_W_in_W = new Image("file:./img/Bishop_W_in_W.png");
    private static final Image Bishop_W_in_B = new Image("file:./img/Bishop_W_in_B.png");
    private static final Image Bishop_B_in_W = new Image("file:./img/Bishop_B_in_W.png");

    private static final Image Knight_B_in_B = new Image("file:./img/Knight_B_in_B.png");
    private static final Image Knight_W_in_W = new Image("file:./img/Knight_W_in_W.png");
    private static final Image Knight_W_in_B = new Image("file:./img/Knight_W_in_B.png");
    private static final Image Knight_B_in_W = new Image("file:./img/Knight_B_in_W.png");

    private static final Image Rook_B_in_B = new Image("file:./img/Rook_B_in_B.png");
    private static final Image Rook_W_in_W = new Image("file:./img/Rook_W_in_W.png");
    private static final Image Rook_W_in_B = new Image("file:./img/Rook_W_in_B.png");
    private static final Image Rook_B_in_W = new Image("file:./img/Rook_B_in_W.png");

    public static Image getPiece(Piece.Type type, Piece.Color pieceColor, Piece.Color backgroundColor)
    {
        Image piece=null;
        // -------------------------------------- KING  ------------------------------------------------------
        if (type == Piece.Type.KING && pieceColor == Piece.Color.WHITE && backgroundColor == Piece.Color.WHITE)
        {
            piece = King_W_in_W;
        }

        if (type == Piece.Type.KING && pieceColor == Piece.Color.WHITE && backgroundColor == Piece.Color.BLACK)
        {
            piece = King_W_in_B;
        }

        if (type == Piece.Type.KING && pieceColor == Piece.Color.BLACK && backgroundColor == Piece.Color.WHITE)
        {
            piece = King_B_in_W;
        }

        if (type == Piece.Type.KING && pieceColor == Piece.Color.BLACK && backgroundColor == Piece.Color.BLACK)
        {
            piece = King_B_in_B;
        }

        // -------------------------------------- QUEEN  ------------------------------------------------------
        if (type == Piece.Type.QUEEN && pieceColor == Piece.Color.WHITE && backgroundColor == Piece.Color.WHITE)
        {
            piece = Queen_W_in_W;
        }

        if (type == Piece.Type.QUEEN && pieceColor == Piece.Color.WHITE && backgroundColor == Piece.Color.BLACK)
        {
            piece = Queen_W_in_B;
        }

        if (type == Piece.Type.QUEEN && pieceColor == Piece.Color.BLACK && backgroundColor == Piece.Color.WHITE)
        {
            piece = Queen_B_in_W;
        }

        if (type == Piece.Type.QUEEN && pieceColor == Piece.Color.BLACK && backgroundColor == Piece.Color.BLACK)
        {
            piece = Queen_B_in_B;
        }

        // -------------------------------------- BISHOP  ------------------------------------------------------
        if (type == Piece.Type.BISHOP && pieceColor == Piece.Color.WHITE && backgroundColor == Piece.Color.WHITE)
        {
            piece = Bishop_W_in_W;
        }

        if (type == Piece.Type.BISHOP && pieceColor == Piece.Color.WHITE && backgroundColor == Piece.Color.BLACK)
        {
            piece = Bishop_W_in_B;
        }

        if (type == Piece.Type.BISHOP && pieceColor == Piece.Color.BLACK && backgroundColor == Piece.Color.WHITE)
        {
            piece = Bishop_B_in_W;
        }

        if (type == Piece.Type.BISHOP && pieceColor == Piece.Color.BLACK && backgroundColor == Piece.Color.BLACK)
        {
            piece = Bishop_B_in_B;
        }

        // -------------------------------------- KNIGHT  ------------------------------------------------------
        if (type == Piece.Type.KNIGHT && pieceColor == Piece.Color.WHITE && backgroundColor == Piece.Color.WHITE)
        {
            piece = Knight_W_in_W;
        }

        if (type == Piece.Type.KNIGHT && pieceColor == Piece.Color.WHITE && backgroundColor == Piece.Color.BLACK)
        {
            piece = Knight_W_in_B;
        }

        if (type == Piece.Type.KNIGHT && pieceColor == Piece.Color.BLACK && backgroundColor == Piece.Color.WHITE)
        {
            piece = Knight_B_in_W;
        }

        if (type == Piece.Type.KNIGHT && pieceColor == Piece.Color.BLACK && backgroundColor == Piece.Color.BLACK)
        {
            piece = Knight_B_in_B;
        }

        // -------------------------------------- ROOK  ------------------------------------------------------
        if (type == Piece.Type.ROOK && pieceColor == Piece.Color.WHITE && backgroundColor == Piece.Color.WHITE)
        {
            piece = Rook_W_in_W;
        }

        if (type == Piece.Type.ROOK && pieceColor == Piece.Color.WHITE && backgroundColor == Piece.Color.BLACK)
        {
            piece = Rook_W_in_B;
        }

        if (type == Piece.Type.ROOK && pieceColor == Piece.Color.BLACK && backgroundColor == Piece.Color.WHITE)
        {
            piece = Rook_B_in_W;
        }

        if (type == Piece.Type.ROOK && pieceColor == Piece.Color.BLACK && backgroundColor == Piece.Color.BLACK)
        {
            piece = Rook_B_in_B;
        }

        // -------------------------------------- PAWN  ------------------------------------------------------
        if (type == Piece.Type.PAWN && pieceColor == Piece.Color.WHITE && backgroundColor == Piece.Color.WHITE)
        {
            piece = Pawn_W_in_W;
        }

        if (type == Piece.Type.PAWN && pieceColor == Piece.Color.WHITE && backgroundColor == Piece.Color.BLACK)
        {
            piece = Pawn_W_in_B;
        }

        if (type == Piece.Type.PAWN && pieceColor == Piece.Color.BLACK && backgroundColor == Piece.Color.WHITE)
        {
            piece = Pawn_B_in_W;
        }

        if (type == Piece.Type.PAWN && pieceColor == Piece.Color.BLACK && backgroundColor == Piece.Color.BLACK)
        {
            piece = Pawn_B_in_B;
        }
        return piece;
    }







}
