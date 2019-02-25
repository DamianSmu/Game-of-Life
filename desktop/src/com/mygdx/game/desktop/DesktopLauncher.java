package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.GameConfig;
import com.mygdx.game.LifeGame;
import static com.mygdx.game.GameConfig.*;


public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = WORLD_WIDTH;
        config.height = WORLD_HEIGHT;
        new LwjglApplication(new LifeGame(), config);
    }
}
