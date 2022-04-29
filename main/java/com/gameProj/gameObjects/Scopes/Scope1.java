package com.gameProj.gameObjects.Scopes;

import com.gameProj.constants.IGameProjectConstants;
import com.gameProj.screen.utilities.ImageResizer;
import com.gameProj.screen.GameCanvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Scope1 implements IScope, IGameProjectConstants {

    private BufferedImage scopeImg;

    private BufferedImage shotAnimationFrame1, shotAnimationFrame2, shotAnimationFrame3;
    private static Scope1 scope = null;

    private final double scopeXMoveCoef;
    private final double scopeYMoveCoef;

    private int actionCoordsX = OFF_SCREEN_COORDS;
    private int actionCoordsY = OFF_SCREEN_COORDS;

    private int x = 1;
    private int y = 1;

    @Override
    public void setActionCoordsX(int actionCoordsX) {
        this.actionCoordsX = actionCoordsX;
    }

    @Override
    public void setActionCoordsY(int actionCoordsY) {
        this.actionCoordsY = actionCoordsY;
    }

    private Scope1(double scopeXMoveCoef, double scopeYMoveCoef, double imageSizeCoef, ImageResizer resizer){

        this.scopeXMoveCoef = scopeXMoveCoef;
        this.scopeYMoveCoef = scopeYMoveCoef;

        try {
            scopeImg = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(GameCanvas.class.getResource("/images/scope.png"))));

            resizer.setImageSizeCoef(imageSizeCoef);

            shotAnimationFrame1 = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(GameCanvas.class.getResource("/images/scope.png"))));
            shotAnimationFrame2 = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(GameCanvas.class.getResource("/images/scope.png"))));
            shotAnimationFrame3 = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(GameCanvas.class.getResource("/images/scope.png"))));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static Scope1 getInstance(double scopeXMoveCoef, double scopeYMoveCoef, double imageSizeCoef, ImageResizer resizer){

        if(scope == null){

            scope = new Scope1(scopeXMoveCoef, scopeYMoveCoef, imageSizeCoef, resizer);

        }
        return scope;

    }

    private boolean checkCoordsOfShot(){

        return actionCoordsX > OFF_SCREEN_COORDS + 5 && actionCoordsY > OFF_SCREEN_COORDS + 5;

    }

    @Override
    public void tryToDrawShot(Graphics g, int animationStage){

        if(checkCoordsOfShot()){

            switch (animationStage) {
                case 0 -> {
                    g.setColor(Color.red);
                    g.drawImage(shotAnimationFrame1, actionCoordsX-shotAnimationFrame1.getWidth()/2, actionCoordsY - shotAnimationFrame1.getHeight()/2, null);
                }
                case 1 -> {
                    g.setColor(Color.green);
                    g.drawImage(shotAnimationFrame2, actionCoordsX- shotAnimationFrame2.getWidth()/2, actionCoordsY-shotAnimationFrame2.getHeight()/2, null);
                }
                case 2 -> {
                    g.setColor(Color.black);
                    g.drawImage(shotAnimationFrame3, actionCoordsX-shotAnimationFrame3.getWidth()/2, actionCoordsY-shotAnimationFrame3.getHeight()/2, null);
                }
                default -> {
                    actionCoordsX = OFF_SCREEN_COORDS;
                    actionCoordsY = OFF_SCREEN_COORDS;
                }
            }


        }

    }

    @Override
    public void AttachScope(Graphics g){

        SimpleCursor.updateMouse();

        if(!SimpleCursor.isCursorOutOfBounds()){
            x = SimpleCursor.getMouseX() - (int)(scopeImg.getWidth()/scopeXMoveCoef);
            y = SimpleCursor.getMouseY() - (int)(scopeImg.getHeight()/scopeYMoveCoef);

        }

        g.drawImage(scopeImg, x, y, null);

    }

}
