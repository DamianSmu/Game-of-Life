package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;


public class Cell extends Actor
{
    private int neighboors;
    private boolean alive;
    private TextureRegion texture;

    public Cell(float x, float y, float width, float height, Stage s, TextureRegion texture)
    {
        setPosition(x,y);
        s.addActor(this);

        this.texture = texture;
        neighboors = 0;
        setAlive(false);

        setWidth(width);
        setHeight(height);

        addListener((Event e) ->
        {
            if ( !(e instanceof InputEvent) ||
                    !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) )
                return false;

            setAlive(true);
            return false;
        });

       /*this.addListener(new InputListener()
       {
           boolean clicked = false;
           @Override
           public boolean keyDown(InputEvent event, int keycode)
           {
               clicked = true;
               Gdx.app.log("Touch", "Down");
               return true;
           }

           @Override
           public boolean keyUp(InputEvent event, int keycode)
           {
               clicked = false;
               return false;
           }

           @Override
           public boolean mouseMoved(InputEvent event, float x, float y)
           {
               if(clicked)
               setAlive(true);
               return false;
           }
       });*/
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
