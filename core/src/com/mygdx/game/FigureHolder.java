package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import static com.mygdx.game.GameConfig.*;

public class FigureHolder
{
    Group group;
    private int[][] source;
    private int figure_width;
    private int figure_height;
    private CellManager manager;

    public FigureHolder(CellManager manager, FigureLoader loader)
    {
        group = new Group();

        figure_width = loader.getWidth();
        figure_height = loader.getHeight();
        source = loader.getSource();

        this.manager = manager;

        for (int i = 0; i < figure_height; i++)
        {
            for (int j = 0; j < figure_width; j++)
            {
                if (source[i][j] == 1)
                {
                    group.addActor(new Cell( j * CELL_WIDTH,  i * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, manager.getStage(), CELL_TEXTURE(), true));
                }
            }
        }

        manager.getStage().addActor(group);
        group.setPosition(100,100);

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
                Gdx.app.log("POS", group.getX() + " " + group.getY());
                Gdx.app.log("EVENT", event.getStageX() + " " + event.getStageY());
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                putOnBoard();
                group.remove();
            }
        });
    }

    private void putOnBoard()
    {
        for (Actor actor : group.getChildren())
        {
            Cell cell = (Cell) actor;
            float closestDistance = Float.MAX_VALUE;
            for (int i = 0; i < BOARD_WIDTH; i++)
            {
                for (int j = 0; j < BOARD_HEIGHT; j++)
                {
                    Cell target = manager.getCell(i, j);
                    if (cell.overlaps(target))
                    {
                        Vector2 stage_coords = cell.localToStageCoordinates(new Vector2(0,0));
                        float currentDistance = Vector2.dst(stage_coords.x, stage_coords.y, target.getX(), target.getY());

                        if (currentDistance < closestDistance)
                        {
                            manager.putCell(i,j);
                            closestDistance = currentDistance;
                        }
                    }
                }
            }

        }
    }
}
