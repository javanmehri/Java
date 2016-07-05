package mos;

/**
 * Created by MOS on 2016-07-04.
 */
public class Player {

    public static final int Black = 0;
    public final int White = 1;

    private String name;
    private boolean isWhite;
    private int color;

    public Player(String name, int color)
    {
        this.name = name;
        if (color==Black || color == White) {this.color = color;}
    }


    public String getName(){ return name; }

    public int getColor() {return color; }

}
