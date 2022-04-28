package com.gameProj.gameObjects;

public abstract class EnemyPrototype implements IGameObject{

    private final int hp;
    private final int dmg;
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

    @Override
    public int getDmg(){

        return dmg;

    }

    public EnemyPrototype(int hp, int dmg, int x, int y){

        this.hp = hp;
        this.dmg = dmg;
        this.x = x;
        this.y = y;

    }


    @Override
    public abstract EnemyPrototype Clone();

}
