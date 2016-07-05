package mos;

/**
 * Created by MOS on 2016-07-04.
 */

public class Player {

    public static enum Color { BLACK, WHITE }
    private String name;
    private Color color;
    private Piece[] pieces = new Piece[16];

    public Player(String name, Color color)
    {
        this.name = name;
        this.color = color;
    }

    public String getName(){ return name; }

    public Color getColor(){ return color; }

}
