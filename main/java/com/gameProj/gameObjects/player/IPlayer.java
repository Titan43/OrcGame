package com.gameProj.gameObjects.player;

import com.gameProj.gameObjects.gameObjectsWithBehavior.IGameObject;

import java.util.ArrayList;

public interface IPlayer {

    boolean isGameContinued();

    boolean isVictory();

    int getNumberOfLives();

    int getNumberOfLivesLeft();

    boolean isPlaying();

    void stopGame();

    void countFrames();

    void setSpecialEventHappened(boolean specialEventHappened);

    int getNumberOfFramesAfterAction();

    boolean isAbleToInteract();

    void checkVictory(ArrayList<IGameObject> gameObjects);

    void getDamaged(int damage);

    boolean specialEventHappened();


}
