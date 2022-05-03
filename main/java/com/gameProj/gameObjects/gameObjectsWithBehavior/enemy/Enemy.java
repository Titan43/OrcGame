package com.gameProj.gameObjects.gameObjectsWithBehavior.enemy;

import com.gameProj.constants.IGameProjectConstants;
import com.gameProj.screen.utilities.ImageResizer;
import com.gameProj.screen.GameScreen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Enemy extends GameObjectPrototype implements IGameProjectConstants{

    private boolean switch1 = false;
    private boolean isMoving = true;
    private boolean isDead = false;
    private boolean isLastItem = false;
    private boolean wasShoutingOrMultiplying = false;

    private BufferedImage enemyImg;
    private BufferedImage enemyMv1, enemyMv2, enemyIdle, enemyDead, enemyRunAway1, enemyRunAway2;

    private int directionX;
    private int directionY;

    private int frameSwitch = 0;
    private int framesAfterShouttOrMultiplication = 0;

    private int enemySpeed;

    private final ImageResizer resizer;

    @Override
    public boolean isAlive(){

        return !isDead;

    }

    @Override
    public boolean isLastItem() {
        return isLastItem;
    }

    @Override
    public void setIsLastItem(boolean isLastItem){

        enemySpeed -= enemySpeed/3;
        this.isLastItem = isLastItem;

    }

    @Override
    public String getSound1() {
        return "enemy_shout.wav";
    }

    @Override
    public String getSound2() {
        return "lms.wav";
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
    public boolean GetHit(int xC, int yC){

        if(checkCollision(xC, yC)){
            if(!isDead) {
                isDead = true;
                enemyImg = enemyDead;
                return true;
            }
        }
        return false;
    }

    @Override
    public int SpecialInteraction(){

        return TryToShoutOrMultiply();

    }

    private int TryToShoutOrMultiply(){

            if (!wasShoutingOrMultiplying && !isDead) {
                if(!isLastItem) {
                    int chanceToShoutOrMultiply = (int) (Math.random() * 10000);

                    if (chanceToShoutOrMultiply == 34 || chanceToShoutOrMultiply == 4545 || chanceToShoutOrMultiply == 44) {

                        ToggleMoving();
                        wasShoutingOrMultiplying = true;
                        return 1;

                    } else if (chanceToShoutOrMultiply == 45) {

                        ToggleMoving();
                        wasShoutingOrMultiplying = true;
                        return 2;
                    }
                }
                else {

                    int chanceToShout = (int) (Math.random() * 100);
                    if (chanceToShout == 34 || chanceToShout == 45 || chanceToShout == 11 || chanceToShout == 47 || chanceToShout == 4 || chanceToShout == 7 || chanceToShout == 44) {

                        wasShoutingOrMultiplying = true;
                        return 1;

                    }

                }

            }
            else if (!isDead) {

                framesAfterShouttOrMultiplication++;
                if (framesAfterShouttOrMultiplication >= 70) {
                    if(!isLastItem) ToggleMoving();
                    framesAfterShouttOrMultiplication = 0;
                    wasShoutingOrMultiplying = false;
                }

            }

            return 0;
    }

    @Override
    public void Move(int panel_w, int panel_h) {
        if(!isDead && isMoving){
        if(switch1 && frameSwitch >= TICKS_TO_SWITCH){

            if(!isLastItem) {
                enemyImg = enemyMv2;
            }
            else enemyImg = enemyRunAway2;

            switch1 = false;
            frameSwitch = 0;

        }
        else {

            if(frameSwitch >= TICKS_TO_SWITCH){
                if(!isLastItem) {
                    enemyImg = enemyMv1;
                }
                else enemyImg = enemyRunAway1;

                switch1 = true;
                frameSwitch = 0;
            }
        }

        frameSwitch ++;

        if(!isLastItem) {
            if (getX() <= 0) {
                directionX = 1;
                directionY = Direction();
            } else if (getX() >= panel_w - enemyImg.getWidth() && directionX == 1) {
                directionX = -1;
                directionY = Direction();
            }
        }
        setX(getX()+directionX* enemySpeed);

        if(!isLastItem) {
            if (getY() <= 0) {
                directionY = 1;
                directionX = Direction();
            } else if (getY() >= panel_h - 2 * enemyImg.getHeight() && directionY == 1) {
                directionY = -1;
                directionX = Direction();
            }
        }
        setY(this.getY()+directionY* enemySpeed);

        if(isLastItem) {

            if (getX() > panel_w || getX() < -enemyImg.getWidth() || getY() < -enemyImg.getHeight() || getY() > panel_h ){

                ToggleMoving();
                isDead = true;

            }
        }
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

    public Enemy(int hp, int enemySpeed, int xC, int yC, ImageResizer resizer) {
        super(hp, xC, yC);

        this.resizer = resizer;
        this.enemySpeed = enemySpeed;

        directionX = Direction();
        directionY = Direction();

        try {
            enemyIdle = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(GameScreen.class.getResource("/images/enemy.png"))));
            enemyMv1 = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(GameScreen.class.getResource("/images/enemyWalkFront1.png"))));
            enemyMv2 = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(GameScreen.class.getResource("/images/enemyWalkFront2.png"))));
            enemyDead = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(GameScreen.class.getResource("/images/enemyDead.png"))));
            enemyRunAway1 = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(GameScreen.class.getResource("/images/enemyRunAway1.png"))));
            enemyRunAway2 = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(GameScreen.class.getResource("/images/enemyRunAway2.png"))));
            enemyImg = enemyIdle;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    @Override
    public GameObjectPrototype Clone() {

        return new Enemy(this.getHP(), this.enemySpeed, this.getX(), this.getY(), this.resizer);

    }
}
