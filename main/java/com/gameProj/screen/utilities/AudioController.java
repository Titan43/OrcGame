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

        if(i>-1 && i<clips.size()) {
            FloatControl settings = (FloatControl) clips.get(i).getControl(FloatControl.Type.MASTER_GAIN);
            settings.setValue(volume);
        }
    }

    public AudioController(String... sounds){

        for(String sound :sounds){

            clips.add(createClip(sound));

        }

    }

    public boolean clipEnded(int choice){

        if (clips.size()>choice-1 && choice > 0) {
            return clips.get(choice - 1).getMicrosecondLength() == clips.get(choice - 1).getMicrosecondPosition() || clips.get(choice-1).getMicrosecondPosition() == 0;
        }
        return false;

    }

    public void playEffectSound(int choice) {

        if (clips.size()>choice-1 && choice > 0) {
            clips.get(choice-1).setMicrosecondPosition(0);
            clips.get(choice-1).start();
        }

    }
}
