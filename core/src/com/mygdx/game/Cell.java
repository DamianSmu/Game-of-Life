package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;


public class Cell extends Actor
{
    private int neighbours;
    private boolean alive;
    private TextureRegion texture;

    public Cell(float x, float y, float width, float height, Stage s, TextureRegion texture)
    {
        setPosition(x,y);
        s.addActor(this);

        this.texture = texture;
        neighbours = 0;
        setAlive(false);

        setWidth(width);
        setHeight(height);

       this.addListener(new InputListener()
       {
           @Override
           public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
           {
               if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
               setAlive(true);
           }
       });
    }

    public void setNeighbours(int neighbours)
    {
        this.neighbours = neighbours;
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
        if(neighbours == 3 && !isAlive())
            setAlive(true);

        if((neighbours < 2 || neighbours > 3) && isAlive())
            setAlive(false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);

        if ( isVisible() && isAlive())
            batch.draw( texture,
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );

        super.draw(batch, parentAlpha);
    }
}
