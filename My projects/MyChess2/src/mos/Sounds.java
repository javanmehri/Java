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



    public Sounds() throws IOException {


    }

    public static void play() throws IOException {

        String gongFile = "./start.wav";
        InputStream in = new FileInputStream(gongFile);


        // create an audiostream from the inputstream
        AudioStream audioStream = new AudioStream(in);

        // play the audio clip with the audioplayer class
        AudioPlayer.player.start(audioStream);

    }


}
