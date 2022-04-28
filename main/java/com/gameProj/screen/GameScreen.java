package com.gameProj.screen;

import com.gameProj.screen.settings.difficultySettings.IDifficultySettings;
import com.gameProj.screen.settings.windowSettings.IWindowSettings;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameScreen extends JFrame{

    public GameScreen(IDifficultySettings difficultySettings, IWindowSettings windowSettings){

        MyPanel panel = MyPanel.getInstance(difficultySettings, windowSettings);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.add(panel);
            this.setResizable(false);
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentMoved(ComponentEvent e) {
                    setLocation(0, 0);
                }
            });

            this.pack();

            this.setLocation(0,0);
            this.setVisible(true);

    }

}
