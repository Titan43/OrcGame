package com.gameProj.gameObjects.Scopes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SimpleCursor {

    private static int panel_w;
    private static int panel_h;
    private static int lowerPanelHeight;
    private static final Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor");

    private static Point mouse;

    public static void setSimpleCursorSettings(int panel_w, int panel_h, int lowerPanelHeight){

        SimpleCursor.panel_w = panel_w;
        SimpleCursor.panel_h = panel_h;
        SimpleCursor.lowerPanelHeight = lowerPanelHeight;

    }

    public static Cursor getCursor(boolean isPlaying){

        if(SimpleCursor.isCursorOutOfBounds() || !isPlaying){

            return Cursor.getDefaultCursor();

        }
        return blankCursor;

    }

    public static void updateMouse(){

        mouse = MouseInfo.getPointerInfo().getLocation();

    }

    public static int getMouseX(){

        return mouse.x;
    }

    public static int getMouseY(){

        return mouse.y;
    }

    public static boolean isCursorOutOfBounds(){

        return mouse.x <= 0 || mouse.y <= 0 || mouse.x >= panel_w || mouse.y >= panel_h -(int)(lowerPanelHeight/1.5);

    }

}
