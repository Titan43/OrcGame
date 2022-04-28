package com.gameProj.screen;

import com.gameProj.constants.IGameProjectConstants;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageResizer implements IGameProjectConstants {

    private double imageSizeCoef;

    public ImageResizer(double imageSizeCoef){

        this.imageSizeCoef = imageSizeCoef;

    }

    public void setImageSizeCoef(double imageSizeCoef) {
        this.imageSizeCoef = imageSizeCoef;
    }

    public BufferedImage resizeImage(BufferedImage originalImage){

        Image resultingImage = originalImage.getScaledInstance((int)(originalImage.getWidth()* imageSizeCoef), (int)(originalImage.getHeight()* imageSizeCoef), Image.SCALE_REPLICATE);
        BufferedImage outputImage = new BufferedImage((int)(originalImage.getWidth()* imageSizeCoef), (int)(originalImage.getHeight()* imageSizeCoef), BufferedImage.TYPE_INT_ARGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }


}
