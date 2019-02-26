package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Cell extends Actor
{
    private int neighbours;
    private boolean alive;
    private TextureRegion texture;
    private Polygon bounds;

    public Cell(float x, float y, float width, float height, Stage s, TextureRegion texture, boolean alive)
    {
        setPosition(x,y);
        s.addActor(this);

        this.texture = texture;
        neighbours = 0;
        setAlive(alive);

        setWidth(width);
        setHeight(height);

        setBounds();

        addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                setAlive(true);
                return false;
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

    private void setBounds()
    {
        float w = getWidth();
        float h = getHeight();

        float[] vertices = {0,0, w,0, w,h, 0,h};
        bounds = new Polygon(vertices);
    }
    public Polygon getBounds()
    {
        Vector2 stage_coords = localToStageCoordinates(new Vector2(0,0));
        bounds.setPosition(stage_coords.x, stage_coords.y);
        bounds.setOrigin( getOriginX(), getOriginY() );
        bounds.setRotation( getRotation() );
        bounds.setScale( getScaleX(), getScaleY() );
        return bounds;
    }

    public boolean overlaps(Cell other)
    {
        Polygon poly1 = this.getBounds();
        Polygon poly2 = other.getBounds();

        return poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle());
    }
}
