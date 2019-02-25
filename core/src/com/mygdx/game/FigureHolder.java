package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import static com.mygdx.game.GameConfig.*;

public class FigureHolder
{
    Group group;
    private int[][] source;

    public FigureHolder(CellManager manager, FigureLoader loader)
    {
        group = new Group();

        int figure_width = loader.getWidth();
        int figure_height = loader.getHeight();
        source = loader.getSource();


        for (int i = 0; i < figure_height; i++)
        {
            for (int j = 0; j < figure_width; j++)
            {
                if(source[i][j] == 1)
                {
                    group.addActor(new Cell(100 + j*CELL_WIDTH, 100 + i*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, manager.getStage(), CELL_TEXTURE(), true));
                }
            }
        }

        manager.getStage().addActor(group);
        group.setSize(CELL_WIDTH, 2*CELL_WIDTH);
        group.addListener(new InputListener()
        {
            private float grabOffsetX;
            private float grabOffsetY;

            @Override
            public boolean touchDown(InputEvent event, float eventOffsetX, float eventOffsetY, int pointer, int button)
            {
                grabOffsetX = eventOffsetX;
                grabOffsetY = eventOffsetY;

                group.toFront();
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float eventOffsetX, float eventOffsetY, int pointer)
            {
                float deltaX = eventOffsetX - grabOffsetX;
                float deltaY = eventOffsetY - grabOffsetY;

                group.moveBy(deltaX, deltaY);
            }
        });
    }
}
