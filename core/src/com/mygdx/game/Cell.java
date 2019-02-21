package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;



public class Cell extends BaseActor
{
    private int neighboors;
    private boolean alive;

    public Cell(float x, float y, Stage s)
    {
        super(x, y, s);

        loadTexture("assets/cell.png");
        neighboors = 0;
        setColor(Color.BLACK);
        alive = false;

        this.addListener((Event e) ->
        {
            if ( !(e instanceof InputEvent) ||
                    !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) )
                return false;

            setColor(Color.WHITE);
            alive = true;
            return false;
        });
    }

    public void setNeighboors(int neighboors)
    {
        this.neighboors = neighboors;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
        if(alive)
            setColor(Color.WHITE);
        else
            setColor(Color.BLACK);
    }

    public boolean isAlive()
    {
        return alive;
    }

    public void step()
    {
        if(neighboors == 3 && !isAlive())
            setAlive(true);

        if((neighboors < 2 || neighboors > 3) && isAlive())
            setAlive(false);
    }
}
