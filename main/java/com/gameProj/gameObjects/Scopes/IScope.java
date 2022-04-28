package com.gameProj.gameObjects.Scopes;

import java.awt.*;

public interface IScope {

    void tryToDrawShot(Graphics g, int animationStage);
    void AttachScope(Graphics g);
    void setActionCoordsX(int actionCoordsX);
    void setActionCoordsY(int actionCoordsY);

}
