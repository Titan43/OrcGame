package com.gameProj.gameObjects.gameObjectsWithBehavior;

import java.awt.image.BufferedImage;

public interface IGameObject {

    boolean isAlive();
    BufferedImage getImage();
    void ToggleMoving();
    boolean GetHit(int xC, int yC);
    int SpecialInteraction();
    void Move(int PANEL_W, int PANEL_H);

    void setX(int x);
    void setY(int y);

    int getX();
    int getY();

    int getHP();

    String getSound();

    Object Clone();

}