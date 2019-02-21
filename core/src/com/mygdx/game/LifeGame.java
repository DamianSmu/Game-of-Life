package com.mygdx.game;

public class LifeGame extends BaseGame
{
    @Override
    public void create()
    {
        super.create();
        setActiveScreen(new GameScreen());
    }
}
