package com.gameProj.gameObjects.gameObjectsWithBehavior.enemy;

import com.gameProj.gameObjects.gameObjectsWithBehavior.IGameObject;

public abstract class EnemyPrototype implements IGameObject {

    private final int hp;
    private int x;
    private int y;

    @Override
    public int getX(){

        return x;

    }

    @Override
    public void setX(int x){

        this.x = x;

    }

    @Override
    public void setY(int y){

        this.y = y;

    }

    @Override
    public int getY(){

        return y;

    }

    @Override
    public int getHP(){

        return hp;

    }

    public EnemyPrototype(int hp, int x, int y){

        this.hp = hp;
        this.x = x;
        this.y = y;

    }


    @Override
    public abstract EnemyPrototype Clone();

}
