package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameConfig
{
    public static float REFRESH_RATE = 0.5f;
    public static int BOARD_WIDTH = 80;
    public static int BOARD_HEIGHT = 80;
    public static int WORLD_WIDTH = 800;
    public static int WORLD_HEIGHT = 800;
    public static float CELL_WIDTH = (float)WORLD_WIDTH/BOARD_WIDTH;
    public static float CELL_HEIGHT = (float)WORLD_HEIGHT/BOARD_HEIGHT;

    public static TextureRegion CELL_TEXTURE()
    {
        return new TextureRegion(new Texture( Gdx.files.internal("assets/cell.png")));
    }
}
