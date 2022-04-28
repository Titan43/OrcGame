package com.gameProj.screen;

import com.gameProj.screen.settings.Config;
import com.gameProj.screen.settings.difficultySettings.EasyDifficulty;
import com.gameProj.screen.settings.difficultySettings.IDifficultySettings;
import com.gameProj.screen.settings.difficultySettings.MediumDifficulty;
import com.gameProj.screen.settings.windowSettings.BigWindow;
import com.gameProj.screen.settings.windowSettings.IWindowSettings;
import com.gameProj.screen.settings.windowSettings.MediumWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserSettingsScreen extends JFrame{

    private IDifficultySettings difficultySettings;
    private IWindowSettings windowSettings;
    private final ConfigurableApplicationContext context;

    private void checkSettings(){

        if(difficultySettings == null){

            difficultySettings = context.getBean(EasyDifficulty.class);

        }
        if(windowSettings == null){

            windowSettings = context.getBean(BigWindow.class);

        }

    }

    public UserSettingsScreen(){

        context = new AnnotationConfigApplicationContext(Config.class);

        JButton easyDifficulty = new JButton("Easy Difficulty");
        JButton mediumDifficulty = new JButton("Medium Difficulty");
        JButton startGame = new JButton("Start the Game");
        JButton bigWindow = new JButton("Window size: Big");
        JButton mediumWindow = new JButton("Window size: Medium");

        easyDifficulty.addActionListener(new ActionListener() {
            @Autowired
            @Override
            public void actionPerformed(ActionEvent e) {

                difficultySettings = context.getBean(EasyDifficulty.class);

            }
        });

        mediumDifficulty.addActionListener(new ActionListener() {
            @Autowired
            @Override
            public void actionPerformed(ActionEvent e) {

                difficultySettings = context.getBean(MediumDifficulty.class);

            }
        });

        bigWindow.addActionListener(new ActionListener() {
            @Autowired
            @Override
            public void actionPerformed(ActionEvent e) {

                windowSettings = context.getBean(BigWindow.class);

            }
        });

        mediumWindow.addActionListener(new ActionListener() {
            @Autowired
            @Override
            public void actionPerformed(ActionEvent e) {

                windowSettings = context.getBean(MediumWindow.class);

            }
        });

        startGame.addActionListener(new ActionListener() {
            @Autowired
            @Override
            public void actionPerformed(ActionEvent e) {

                checkSettings();

                new GameScreen(difficultySettings, windowSettings);

                context.close();
                dispose();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        easyDifficulty.setAlignmentX(Component.CENTER_ALIGNMENT);
        mediumDifficulty.setAlignmentX(Component.CENTER_ALIGNMENT);
        startGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        bigWindow.setAlignmentX(Component.CENTER_ALIGNMENT);
        mediumWindow.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(easyDifficulty);
        this.add(mediumDifficulty);
        this.add(new Label());
        this.add(mediumWindow);
        this.add(bigWindow);
        this.add(new Label());
        this.add(startGame);
        this.setVisible(true);
        this.setSize(400, 400);

    }

}
