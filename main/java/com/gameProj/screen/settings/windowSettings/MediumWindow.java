package com.gameProj.screen.settings.windowSettings;

import org.springframework.stereotype.Service;

@Service
public class MediumWindow implements IWindowSettings{

    @Override
    public int getPanel_w() {
        return 1600;
    }

    @Override
    public int getPanel_h() {
        return 900;
    }

    @Override
    public double getScopeXMoveCoef() {
        return 1.9925;
    }

    @Override
    public double getScopeYMoveCoef() {
        return 1.773;
    }

    @Override
    public double getImageSizeCoef() {
        return 0.541;
    }

    @Override
    public double getScopeSizeCoef() {
        return 0.833;
    }


}

