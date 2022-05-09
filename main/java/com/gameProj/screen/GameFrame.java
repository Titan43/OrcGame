package com.gameProj.screen;

import com.gameProj.screen.settings.difficultySettings.IDifficultySettings;
import com.gameProj.screen.settings.windowSettings.IWindowSettings;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameFrame extends JFrame{

    public GameFrame(IDifficultySettings difficultySettings, IWindowSettings windowSettings){

        GameScreen panel = GameScreen.getInstance(difficultySettings, windowSettings);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.add(panel);

            //Ахтунг, костилі!!!
            this.setResizable(false);
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentMoved(ComponentEvent e) {
                    setLocation(0, 0);
                }
            });
            //Кінець костилів

            this.pack();

            this.setLocation(0,0);
            this.setVisible(true);

    }

}
