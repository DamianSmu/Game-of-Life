package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.mygdx.game.GameConfig.*;

public class CellManager
{
    private Stage stage;
    private Cell[][] board;
    private TextureRegion cell_texture;

    public CellManager(Stage stage)
    {
        this.stage = stage;
        cell_texture = new TextureRegion(new Texture( Gdx.files.internal("assets/cell.png")));

        board = new Cell[BOARD_WIDTH][BOARD_HEIGHT];

        float cell_width = (float)WORLD_WIDTH/BOARD_WIDTH;
        float cell_height = (float)WORLD_HEIGHT/BOARD_HEIGHT;

        for (int x = 0; x < BOARD_WIDTH; x++)
        {
            for (int y = 0; y < BOARD_HEIGHT; y++)
            {
            board[x][y] = new Cell(cell_width*x, cell_height*y, cell_width, cell_height, stage, cell_texture);
            }
        }
    }

    public void setNeighbours()
    {
        for (int x = 0; x < BOARD_WIDTH; x++)
        {
            for (int y = 0; y < BOARD_HEIGHT; y++)
            {
                Point[] close = {new Point(x - 1, y - 1), new Point(x, y - 1), new Point(x + 1, y - 1), new Point(x - 1, y), new Point(x + 1, y), new Point(x - 1, y + 1), new Point(x, y + 1), new Point(x + 1, y + 1)};
                int cells = 0;
                for (int i = 0; i < 8; i++)
                {
                    if(close[i].x > 0 && close[i].x < BOARD_WIDTH && close[i].y > 0 && close[i].y < BOARD_HEIGHT && board[close[i].x][close[i].y].isAlive() )
                        cells++;
                }
                board[x][y].setNeighboors(cells);
            }
        }
    }

    public void step()
    {
        setNeighbours();

        for (int x = 0; x < BOARD_WIDTH; x++)
        {
            for (int y = 0; y < BOARD_HEIGHT; y++)
            {
                board[x][y].step();
            }
        }
    }
}
