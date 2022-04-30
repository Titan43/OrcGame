package com.gameProj.gameObjects.player;

import com.gameProj.gameObjects.gameObjectsWithBehavior.IGameObject;

import java.util.ArrayList;

public class Player implements IPlayer{

    private final int numberOfLives;
    private int numberOfLivesLeft;

    private boolean isPlaying = true;
    private boolean isGameContinued = true;
    private boolean isVictory = false;
    private boolean wasShooting = false;
    private boolean specialEventHappened = false;

    private static Player player = null;

    private int framesAfterShot = 0;

    @Override
    public boolean specialEventHappened(){

        return specialEventHappened;

    }

    @Override
    public void setSpecialEventHappened(boolean specialEventHappened) {
        this.specialEventHappened = specialEventHappened;
    }

    @Override
    public int getNumberOfFramesAfterAction() {
        return framesAfterShot;
    }

    @Override
    public boolean isGameContinued() {
        return isGameContinued;
    }

    private Player(int numberOfLives){

        this.numberOfLives = numberOfLives;
        this.numberOfLivesLeft = numberOfLives;

    }

    @Override
    public boolean isVictory() {
        return isVictory;
    }

    @Override
    public int getNumberOfLives() {
        return numberOfLives;
    }

    @Override
    public int getNumberOfLivesLeft() {
        return numberOfLivesLeft;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public void countFrames(){

        if(isPlaying) {
           if(wasShooting) {
               framesAfterShot++;
               if (framesAfterShot >= 60) {

                   framesAfterShot = 0;
                   wasShooting = false;

               }
           }
        }

    }

    @Override
    public boolean isAbleToInteract(){

        if(!wasShooting && isPlaying){

            wasShooting = true;
            return true;

        }
        return false;

    }

    @Override
    public void stopGame(){

        isGameContinued = false;

    }

    public static Player getInstance(int numberOfLives){

        if(player == null){

            player = new Player(numberOfLives);

        }

        return player;

    }

    @Override
    public void checkVictory(ArrayList<IGameObject> gameObjects){

        for (IGameObject gameObject: gameObjects) {

            if(gameObject.isAlive()){

                return;

            }

        }

        isVictory = true;
        isPlaying = false;

    }

    @Override
    public void getDamaged(int damage){

        numberOfLivesLeft -= damage;
        if(numberOfLivesLeft <= 0){

            isPlaying = false;

        }

    }

}
