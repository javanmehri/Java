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

    public enum SoundEffects  { START, SELECT, MOVE, REMOVE, CHECK, CHECKMATE }
    private static SoundEffects sound = null;

    private final static String file_start = "./sounds/start.wav";
    private final static String file_remove = "./sounds/remove.wav";
    private final static String file_move = "./sounds/move.wav";
    private final static String file_check = "./sounds/check.wav";


    public Sounds() {

    }


    public static void play(Sounds.SoundEffects effect)
    {
        switch (effect)
        {
            case START:  play(file_start); break;
            case REMOVE: play(file_remove); break;
            case MOVE:   play(file_move); break;
            case CHECK:  play(file_check); break;
        }
    }

    public static void setSound(SoundEffects soundEffect)
    {
        sound = soundEffect;
    }

    public static void playSound()
    {
        if (sound !=null)
            play(sound);
            sound = null;
    }

    private static void play(String file)
    {

        try
        {
            InputStream in = new FileInputStream(file);
            // create an audiostream from the inputstream
            AudioStream audioStream = new AudioStream(in);
            // play the audio clip with the audioplayer class
            AudioPlayer.player.start(audioStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
