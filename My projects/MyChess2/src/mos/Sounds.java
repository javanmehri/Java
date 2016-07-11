package mos;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by MOS on 2016-07-06.
 */
public class Sounds {

    public static enum SoundEffects  { START, SELECT, MOVE, REMOVE, CHECK, CHECKMATE }
    private final static String file_start = "./sounds/start.wav";
    private final static String file_remove = "./sounds/remove.wav";
    private final static String file_move = "./sounds/move.wav";





    public Sounds() throws IOException {


    }

    public static void play(SoundEffects effect) throws IOException
    {

        if (effect == SoundEffects.START)
            play(file_start);

        if (effect == SoundEffects.REMOVE)
            play(file_remove);

        if (effect == SoundEffects.MOVE)
            play(file_move);


    }


    private static void play(String file) throws IOException
    {
        InputStream in = new FileInputStream(file);

        // create an audiostream from the inputstream
        AudioStream audioStream = new AudioStream(in);

        // play the audio clip with the audioplayer class
        AudioPlayer.player.start(audioStream);
    }


}
