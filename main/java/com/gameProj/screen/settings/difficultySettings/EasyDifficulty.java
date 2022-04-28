package com.gameProj.screen.settings.difficultySettings;

import org.springframework.stereotype.Service;

@Service
public class EasyDifficulty implements IDifficultySettings {

    @Override
    public int getNumberOfLives(){

        return 3;

    }

    @Override
    public int getEnemyCount(){

        return 8;

    }

    @Override
    public int getEnemyDamage(){

        return 1;

    }

    @Override
    public int getEnemyHp(){

        return 1;

    }

    @Override
    public int getEnemySpeed(){

        return 6;

    }

    @Override
    public int getEnemyCloneNumber(){

        return 2;

    }

}
