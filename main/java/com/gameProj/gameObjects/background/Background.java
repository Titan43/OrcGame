package com.gameProj.gameObjects.background;

import com.gameProj.screen.utilities.ImageResizer;
import com.gameProj.screen.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Background implements IBackground{

    private BufferedImage backgroundImage;

    private static Background background = null;

    private Background(ImageResizer resizer){

        try{
            backgroundImage = resizer.resizeImage(ImageIO.read(Objects.requireNonNull(GameScreen.class.getResource("/images/background.png"))));
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static Background getInstance(ImageResizer resizer){

        if(background == null) background = new Background(resizer);
        return background;

    }

    @Override
    public void drawBackground(Graphics g) {

        g.drawImage(backgroundImage, 0, 0, null);

    }
}
