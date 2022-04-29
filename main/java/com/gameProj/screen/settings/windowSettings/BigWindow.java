package com.gameProj.screen.settings.windowSettings;

import org.springframework.stereotype.Service;

@Service
public class BigWindow implements IWindowSettings{

    @Override
    public int getPanel_w() {
        return 1920;
    }

    @Override
    public int getPanel_h() {
        return 1080;
    }

    @Override
    public double getScopeXMoveCoef() {
        return 1.9935;
    }

    @Override
    public double getScopeYMoveCoef() {
        return 1.785;
    }

    @Override
    public double getImageSizeCoef() {
        return 0.65;
    }

    @Override
    public double getScopeAndBackgroundSizeCoef() {
        return 1.0;
    }
}

