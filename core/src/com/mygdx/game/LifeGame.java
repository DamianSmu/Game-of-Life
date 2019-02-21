package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

public class LifeGame extends Game
{
    @Override
    public void create()
    {
        InputMultiplexer im = new InputMultiplexer();
        Gdx.input.setInputProcessor( im );
        setScreen(new GameScreen());
    }
}
