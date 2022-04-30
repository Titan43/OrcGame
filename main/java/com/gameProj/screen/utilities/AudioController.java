package com.gameProj.screen.utilities;

import com.gameProj.screen.GameScreen;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class AudioController{

    private Clip shotClip;

    public AudioController(String sound){

        try {
            File effectSound = new File(Objects.requireNonNull(GameScreen.class.getResource("/Sounds/" + sound)).toURI());
            shotClip = AudioSystem.getClip();
            shotClip.open(AudioSystem.getAudioInputStream(effectSound));
            FloatControl volume1 = (FloatControl) shotClip.getControl(FloatControl.Type.MASTER_GAIN);
            volume1.setValue(-9);

        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public void playEffectSound(int choice) {

        if (choice == 1) {
            shotClip.setMicrosecondPosition(0);
            shotClip.start();
        }
    }
}
