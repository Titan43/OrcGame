package com.gameProj.gameObjects;

import java.util.ArrayList;

public interface IPlayer {

    boolean isGameContinued();

    boolean isVictory();

    int getNumberOfLives();

    int getNumberOfLivesLeft();

    boolean isPlaying();

    void stopGame();

    void countFrames();

    int getNumberOfFramesAfterAction();

    boolean isAbleToInteract();

    void checkVictory(ArrayList<IGameObject> gameObjects);

    void getDamaged(int damage);
}
