package com.gameProj.screen.gui;

import com.gameProj.gameObjects.gameObjectsWithBehavior.IGameObject;

import java.awt.*;
import java.util.ArrayList;

public interface IGUI {

    void drawUI(Graphics g, int numberOfLives, int numberOfLivesLeft, ArrayList<IGameObject> gameObjects);

    void drawVictory(Graphics g);

    void drawLoss(Graphics g);

}
