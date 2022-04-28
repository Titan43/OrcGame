package com.gameProj.screen.settings.difficultySettings;

import org.springframework.stereotype.Service;

@Service
public class MediumDifficulty implements IDifficultySettings {

    @Override
    public int getNumberOfLives(){

        return 2;

    }

    @Override
    public int getEnemyCount(){

        return 14;

    }

    @Override
    public int getEnemyDamage(){

        return 1;

    }

    @Override
    public int getEnemyHp(){

        return 2;

    }
    @Override
    public int getEnemySpeed(){

        return 8;

    }

    @Override
    public int getEnemyCloneNumber(){

        return 3;

    }

}
