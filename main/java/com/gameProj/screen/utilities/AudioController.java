package com.gameProj.screen.utilities;

import com.gameProj.screen.GameScreen;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

public class AudioController{

    private final ArrayList<Clip> clips = new ArrayList<>();

    private Clip createClip(String sound){
        Clip tmpClip = null;
        try {
            File effectSound = new File(Objects.requireNonNull(GameScreen.class.getResource("/Sounds/" + sound)).toURI());
            tmpClip = AudioSystem.getClip();
            tmpClip.open(AudioSystem.getAudioInputStream(effectSound));

        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return tmpClip;
    }

    public void changeVolume(int i, float volume){

        FloatControl settings = (FloatControl) clips.get(i).getControl(FloatControl.Type.MASTER_GAIN);
        settings.setValue(volume);

    }

    public AudioController(String... sounds){

        for(String sound :sounds){

            clips.add(createClip(sound));

        }

    }

    public boolean playEffectSound(int choice) {

        if (choice == 1 && clips.size()>0) {
            clips.get(0).setMicrosecondPosition(0);
            clips.get(0).start();
        }
        else if(choice == 2 && clips.size()>1){

            clips.get(1).setMicrosecondPosition(0);
            clips.get(1).start();

        }
        return false;
    }
}
