package com.gameProj.screen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import com.gameProj.constants.IGameProjectConstants;
import com.gameProj.gameObjects.Scopes.IScope;
import com.gameProj.gameObjects.Scopes.Scope1;
import com.gameProj.gameObjects.Scopes.SimpleCursor;
import com.gameProj.gameObjects.background.Background;
import com.gameProj.gameObjects.background.IBackground;
import com.gameProj.gameObjects.gameObjectsWithBehavior.enemy.Enemy;
import com.gameProj.gameObjects.gameObjectsWithBehavior.IGameObject;
import com.gameProj.gameObjects.player.IPlayer;
import com.gameProj.gameObjects.player.Player;
import com.gameProj.screen.gui.IGUI;
import com.gameProj.screen.gui.UI;
import com.gameProj.screen.settings.difficultySettings.IDifficultySettings;
import com.gameProj.screen.settings.windowSettings.IWindowSettings;
import com.gameProj.screen.utilities.AudioController;
import com.gameProj.screen.utilities.ImageResizer;

public class GameScreen extends JPanel implements Runnable, IGameProjectConstants, MouseListener {

    private final ArrayList<IGameObject> enemies = new ArrayList<>();
    private final ArrayList<IGameObject> objectStorage = new ArrayList<>();
    private IScope scope;
    private IGUI gui;
    private IPlayer player;
    private IBackground background;

    private AudioController audioController;

    private final IDifficultySettings difficultySettings;
    private final IWindowSettings windowSettings;

    private static GameScreen panel = null;

    private void SpawnEnemies(IGameObject enemy){

        for (int i = 0; i<difficultySettings.getEnemyCount(); i++) {

            enemies.add((Enemy) enemy.Clone());

            enemies.get(i).setX(windowSettings.getPanel_w() - enemies.get(i).getImage().getWidth());
            enemies.get(i).setY(i *  enemy.getImage().getHeight());

        }

    }

    private void GameStart(){

        IGameObject enemy = new Enemy(difficultySettings.getEnemyHp(), difficultySettings.getEnemySpeed(), 2300, 2300, new ImageResizer(windowSettings.getImageSizeCoef()));
        enemy.ToggleMoving();

        SimpleCursor.setSimpleCursorSettings(windowSettings.getPanel_w(), windowSettings.getPanel_h(), enemy.getImage().getHeight());

        player = Player.getInstance(difficultySettings.getNumberOfLives());
        background = Background.getInstance(new ImageResizer(windowSettings.getScopeAndBackgroundSizeCoef()));
        scope = Scope1.getInstance(windowSettings.getScopeXMoveCoef(), windowSettings.getScopeYMoveCoef(), windowSettings.getScopeAndBackgroundSizeCoef(), new ImageResizer(windowSettings.getScopeAndBackgroundSizeCoef()));
        gui = UI.getInstance(windowSettings.getPanel_w(), windowSettings.getPanel_h(), enemy.getImage().getHeight(), enemy.getImage().getWidth(), windowSettings.getScopeAndBackgroundSizeCoef(), new ImageResizer(windowSettings.getImageSizeCoef()));

        audioController = new AudioController(scope.getSound(), enemy.getSound());
        audioController.changeVolume(0, -15);
        audioController.changeVolume(1, -15);

        SpawnEnemies(enemy);

        Thread gameThread = new Thread(this);
        gameThread.start();

    }

    private GameScreen(IDifficultySettings difficultySettings, IWindowSettings windowSettings){

        this.windowSettings = windowSettings;
        this.setPreferredSize(new Dimension(windowSettings.getPanel_w(), windowSettings.getPanel_h()));

        this.setBackground(Color.white);
        this.addMouseListener(this);


        this.difficultySettings = difficultySettings;

        GameStart();
    }

    public static GameScreen getInstance(IDifficultySettings difficultySettings, IWindowSettings windowSettings){

        if(panel == null){

            panel = new GameScreen(difficultySettings, windowSettings);

        }

        return panel;

    }

    private void Interact(IGameObject gameObject){

        int interactionResult = gameObject.SpecialInteraction();

        if(interactionResult == 1){

            if(gameObject.isLastItem()){

                if(audioController.clipEnded(3)){

                    audioController.playEffectSound(3);

                }

            }
            else{

                if(audioController.clipEnded(2)){

                    audioController.playEffectSound(2);

                }

            }

        }
        else if(interactionResult == 2 && enemies.size()<20){

            objectStorage.add((Enemy) gameObject.Clone());
            player.setSpecialEventHappened(true);

        }
    }

    public void gameEnd(Graphics g){

        if(player.isVictory()){

            gui.drawVictory(g);

        }
        else gui.drawLoss(g);

        player.stopGame();

    }

    private void TryToAddObjects(){

        if(player.specialEventHappened()) {
            enemies.addAll(objectStorage);
            player.setSpecialEventHappened(false);
            objectStorage.clear();
        }

    }

    public void paint(Graphics g){

        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        background.drawBackground(g2D);

        for (IGameObject enemy : enemies) {

            enemy.Move(windowSettings.getPanel_w(), windowSettings.getPanel_h());
            g2D.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
            Interact(enemy);

        }

        TryToAddObjects();

        scope.tryToDrawShot(g2D, player.getNumberOfFramesAfterAction()/2);

        scope.AttachScope(g2D);

        this.setCursor(SimpleCursor.getCursor(player.isPlaying()));

        gui.drawUI(g2D, player.getNumberOfLives(), player.getNumberOfLivesLeft(), enemies);

        player.countFrames();

        if(!player.isPlaying()){

            gameEnd(g2D);

        }

    }

    public void run(){

        long lastTime = System.nanoTime();
        double delta = 0;

        while(player.isGameContinued()) {
            long now = System.nanoTime();
            delta += (now - lastTime)/NS;
            lastTime = now;

            if(delta>=1){

                repaint();

            delta--;

            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(player.isAbleToInteract()) {;

            scope.setActionCoordsX(e.getX());
            scope.setActionCoordsY(e.getY());
            audioController.playEffectSound(1);

            boolean hasMissed = true;

            for (IGameObject enemy : enemies) {
                if(enemy.GetHit(e.getX(), e.getY())) hasMissed = false;

            }

            if(hasMissed) player.getDamaged(1);
            else {

                player.checkVictory(enemies);

            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}
}
