package com.gameProj.screen.gui;

import com.gameProj.constants.IGameProjectConstants;
import com.gameProj.gameObjects.gameObjectsWithBehavior.IGameObject;
import com.gameProj.gameObjects.Scopes.SimpleCursor;
import com.gameProj.screen.utilities.ImageResizer;
import com.gameProj.screen.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class UI implements IGUI, IGameProjectConstants {

    private final int yR;
    private final int xR;
    private final int panel_w;
    private final int panel_h;
    private final int enemyHeight;
    private final int enemyWidth;
    private final double mapXRation;

    private BufferedImage heartFilled;
    private BufferedImage heartEmpty;

    private int mX;
    private int mY;

    private static UI ui = null;

    private UI(int PANEL_W, int PANEL_H, int enemyHeight, int enemyWidth, ImageResizer resizer){

        panel_w = PANEL_W;
        panel_h = PANEL_H;

        this.enemyHeight = enemyHeight;
        this.enemyWidth = enemyWidth;
        yR = PANEL_H - enemyHeight;
        xR = (int)(enemyHeight*SCREEN_RATIO);
        mapXRation = xR/(double)panel_w;

        try {
            heartFilled = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(GameScreen.class.getResource("/images/HeartFilled.png"))));
            heartEmpty = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(GameScreen.class.getResource("/images/HeartEmpty.png"))));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UI getInstance(int PANEL_W, int PANEL_H, int enemyHeight, int enemyWidth, ImageResizer resizer){

        if(ui == null) ui = new UI(PANEL_W, PANEL_H, enemyHeight, enemyWidth, resizer);
        return ui;

    }

    private void drawEnemiesOnMap(Graphics g, ArrayList<IGameObject> enemies){

        for(IGameObject enemy: enemies) {
            if (!enemy.isDead()) {
                g.setColor(Color.RED);
            } else {

                g.setColor(Color.DARK_GRAY);

            }

            g.fillRect(panel_w / 10 + xR - (int) ((panel_w - enemy.getX()) * mapXRation), yR + enemyHeight - (int) ((panel_h - enemy.getY()) * mapXRation), xR / 20, enemyHeight / 15);
        }

        g.setColor(Color.DARK_GRAY);
    }

    private void drawHearts(Graphics g, int numberOfLives, int numberOfLivesLeft){

        int i;
        for(i = 0; i < numberOfLivesLeft; i++){

            g.drawImage(heartFilled, panel_w/3+(i+1)*enemyWidth+enemyWidth/2, yR  - enemyHeight/6, null);

        }
        for(; i < numberOfLives; i++){

            g.drawImage(heartEmpty, panel_w/3+(i+1)*enemyWidth+enemyWidth/2, yR - enemyHeight/6, null);

        }

    }

    private void drawScopeOnMap(Graphics g){

        if(!SimpleCursor.isCursorOutOfBounds()){

            this.mX = SimpleCursor.getMouseX();
            this.mY = SimpleCursor.getMouseY();

        }

        g.setColor(Color.green);
        g.fillRect(panel_w / 10 + xR - (int) ((panel_w - this.mX) * mapXRation), yR + enemyHeight - (int) ((panel_h - this.mY) * mapXRation), -xR / 24, -enemyHeight / 15);

    }

    @Override
    public void drawUI(Graphics g, int numberOfLives, int numberOfLivesLeft, ArrayList<IGameObject> gameObjects){

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, panel_h - enemyHeight, panel_w, enemyHeight);

        g.setColor(Color.BLUE);
        g.fillRect(panel_w/10, yR, xR, (int)(enemyHeight*(1-mapXRation)));

        drawHearts(g, numberOfLives, numberOfLivesLeft);

        drawEnemiesOnMap(g, gameObjects);

        drawScopeOnMap(g);

    }

    @Override
    public void drawVictory(Graphics g) {

        g.setColor(Color.green);
        g.fillRect(0, 0, panel_w, panel_h);

    }

    @Override
    public void drawLoss(Graphics g) {

        g.setColor(Color.red);
        g.fillRect(0, 0, panel_w, panel_h);

    }

}
