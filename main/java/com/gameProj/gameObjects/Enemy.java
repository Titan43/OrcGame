package com.gameProj.gameObjects;

import com.gameProj.constants.IGameProjectConstants;
import com.gameProj.screen.ImageResizer;
import com.gameProj.screen.MyPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Enemy extends EnemyPrototype implements IGameProjectConstants{

    private boolean switch1 = false;
    private boolean isMoving = true;
    private boolean isDead = false;
    private boolean wasShootingOrMultiplying = false;

    private BufferedImage enemyImg;
    private BufferedImage enemyMv1, enemyMv2, enemyIdle, enemyDead;

    private int directionX;
    private int directionY;

    private int frameSwitch = 0;
    private int framesAfterShotOrMultiplication = 0;

    private final int enemySpeed;

    private final ImageResizer resizer;

    @Override
    public boolean isDead(){

        return isDead;

    }

    @Override
    public BufferedImage getImage() {
        return enemyImg;
    }

    private boolean checkCollision(int cX, int cY){

        return cX <= getX() + enemyImg.getWidth() - enemyImg.getWidth()* COLLISION_COEF && cX >= getX() + 2*enemyImg.getWidth()*COLLISION_COEF && cY <= getY() + enemyImg.getHeight()&& cY >= getY();

    }

    @Override
    public void ToggleMoving() {

        if(isMoving){

            enemyImg = enemyIdle;

            isMoving = false;

        }
        else isMoving = true;

    }

    @Override
    public void GetHit(int xC, int yC){

        if(checkCollision(xC, yC)){
            if(!isDead) {
                isDead = true;
                enemyImg = enemyDead;
            }
        }
    }

    @Override
    public int SpecialInteraction(){

        return TryToShootOrMultiply();

    }

    private int TryToShootOrMultiply(){

        if(!wasShootingOrMultiplying && !isDead) {
            int chanceToShoot = (int)(Math.random() * 10000);
            if (chanceToShoot == 34) {

                ToggleMoving();
                wasShootingOrMultiplying = true;
                return 1;

            }
            else if(chanceToShoot == 45) {

                ToggleMoving();
                wasShootingOrMultiplying = true;
                return 2;
            }
        }
        else if(!isDead){

            framesAfterShotOrMultiplication++;

            if(framesAfterShotOrMultiplication >= 70) {
                ToggleMoving();
                framesAfterShotOrMultiplication = 0;
                wasShootingOrMultiplying = false;
            }

        }

        return 0;
    }

    @Override
    public void Move(int panel_w, int panel_h) {
        if(!isDead && isMoving){
        if(switch1 && frameSwitch >= TICKS_TO_SWITCH){

            enemyImg = enemyMv2;
            switch1 = false;
            frameSwitch = 0;

        }
        else {

            if(frameSwitch >= TICKS_TO_SWITCH){
                enemyImg = enemyMv1;
                switch1 = true;
                frameSwitch = 0;
            }
        }

        frameSwitch ++;

        if(getX()<= 0) {directionX = 1;  directionY = Direction();}
        else if(getX()>=panel_w- enemyImg.getWidth() && directionX == 1) {directionX = -1; directionY = Direction();}
        setX(getX()+directionX* enemySpeed);

        if(getY()<= 0) {directionY = 1; directionX = Direction();}
        else if(getY()>=panel_h- 2*enemyImg.getHeight() && directionY == 1){ directionY = -1;  directionX = Direction();}
        setY(this.getY()+directionY* enemySpeed);

        }
    }

    private int Direction(){

        int tmp = (((int) (Math.random()*100))%3);
        if (tmp == 1) return -1;
        else if(tmp == 2) return 1;
        else {
            if (directionX == 0 || directionY == 0) return ((((int) (Math.random() * 10)) % 2) == 0) ? -1 : 1;
            return 0;
        }
    }

    public Enemy(int hp, int dmg, int enemySpeed, int xC, int yC, ImageResizer resizer) {
        super(hp, dmg, xC, yC);

        this.resizer = resizer;
        this.enemySpeed = enemySpeed;

        directionX = Direction();
        directionY = Direction();

        try {
            enemyIdle = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(MyPanel.class.getResource("/images/enemy.png"))));
            enemyMv1 = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(MyPanel.class.getResource("/images/enemyWalkFront1.png"))));
            enemyMv2 = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(MyPanel.class.getResource("/images/enemyWalkFront2.png"))));
            enemyDead = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(MyPanel.class.getResource("/images/enemy.png"))));
            enemyImg = enemyIdle;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    @Override
    public EnemyPrototype Clone() {

        return new Enemy(this.getHP(), this.getDmg(), this.enemySpeed, this.getX(), this.getY(), this.resizer);

    }
}
