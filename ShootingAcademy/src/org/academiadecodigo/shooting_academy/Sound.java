package org.academiadecodigo.shooting_academy;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {

    private Clip clip;

    private final URL[] soundURL = new URL[2];

    public Sound(int chosenSound){

        init();
        setFile(chosenSound);
    }

    public void init(){
        soundURL[0] = getClass().getResource("AirWooshUnderwater.wav");
        soundURL[1] = getClass().getResource("backroundGameSound.wav");
    }

    public void setFile(int i) {

        try {
            AudioInputStream x = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(x);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {clip.stop();}


}
