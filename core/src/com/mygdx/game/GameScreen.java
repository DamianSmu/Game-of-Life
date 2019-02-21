package com.mygdx.game;

public class GameScreen extends BaseScreen
{
    private CellMenager menager;
    float step;
    @Override
    public void initialize()
    {
        menager = new CellMenager(30,30, mainStage);
        step = 0;
    }

    @Override
    public void update(float dt)
    {
        step += dt;
        if(step > 1)
        {
            step = 0;
            menager.setNeighboors();
        }

    }
}
