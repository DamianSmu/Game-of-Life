package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.mygdx.game.GameConfig.*;

public class CellManager
{
    private Stage stage;
    private Cell[][] board;

    public CellManager(Stage stage)
    {
        this.stage = stage;

        board = new Cell[BOARD_WIDTH][BOARD_HEIGHT];

        for (int x = 0; x < BOARD_WIDTH; x++)
        {
            for (int y = 0; y < BOARD_HEIGHT; y++)
            {
            board[x][y] = new Cell(CELL_WIDTH*x, CELL_HEIGHT*y, CELL_WIDTH, CELL_HEIGHT, stage, CELL_TEXTURE(), false);
            }
        }

        FigureHolder fh = new FigureHolder(this, new FigureLoader("assets/c.txt"));
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
                board[x][y].setNeighbours(cells);
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

    public Stage getStage()
    {
        return stage;
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public Cell getCell(int pos_x, int pos_y)
    {
        return board[pos_x][pos_y];
    }
}
