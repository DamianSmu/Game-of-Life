package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.mygdx.game.GameConfig.*;

public class GameScreen implements InputProcessor, Screen
{
    private CellManager manager;
    private float step;
    private boolean paused;
    private Stage mainStage;
    private Stage uiStage;

    public GameScreen()
    {
        mainStage = new Stage(new FitViewport(WORLD_WIDTH, WORLD_HEIGHT));
       // uiStage = new Stage(new FitViewport(WORLD_WIDTH + 200, WORLD_HEIGHT));
        manager = new CellManager(mainStage);
        step = 0;
        paused = true;

        Gdx.graphics.setTitle("Tickrate: " + String.format("%.2f", REFRESH_RATE));
    }

    @Override
    public void show()
    {
        InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
        im.addProcessor(this);
        im.addProcessor(mainStage);
    }

    @Override
    public void render(float dt)
    {
        dt = Math.min(dt, 1 / 30f);
        step += dt;

        mainStage.act(dt);

        if (step > REFRESH_RATE && !paused)
        {
            step = 0;
            manager.step();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glViewport(0,0, 800,800);
        mainStage.draw();

        Gdx.gl.glViewport(0,0, 1000,800);
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
    //    uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {
        InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
        im.removeProcessor(this);
        im.removeProcessor(mainStage);
    }

    @Override
    public void dispose()
    {

    }

    @Override
    public boolean keyDown(int keycode)
    {
        switch (keycode)
        {
            case Input.Keys.SPACE:
                paused = !paused;
                break;
            case Input.Keys.UP:
                REFRESH_RATE -= 0.02;
                REFRESH_RATE = Math.min(Math.max(REFRESH_RATE, 0.01f), 5f);
                Gdx.graphics.setTitle("Tickrate: " + String.format("%.2f", REFRESH_RATE));
                break;
            case Input.Keys.DOWN:
                REFRESH_RATE += 0.02;
                REFRESH_RATE = Math.min(Math.max(REFRESH_RATE, 0.01f), 5f);
                Gdx.graphics.setTitle("Tickrate: " + String.format("%.2f", REFRESH_RATE));
                break;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
