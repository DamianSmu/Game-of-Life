package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.mygdx.game.GameConfig.*;

public class CellManager
{
    private Stage stage;
    private Cell[][] board;
    int[][][][] close;

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

        setCloseCellsPosition();

        FigureHolder fh = new FigureHolder(this, new FigureLoader("assets/c.txt"));
    }

    public void setNeighbours()
    {
        for (int x = 0; x < BOARD_WIDTH; x++)
        {
            for (int y = 0; y < BOARD_HEIGHT; y++)
            {
                int cells = 0;
                for (int i = 0; i < 8; i++)
                {
                    if(close[x][y][i][0] > 0 && close[x][y][i][0] < BOARD_WIDTH && close[x][y][i][1] > 0 && close[x][y][i][1] < BOARD_HEIGHT && board[close[x][y][i][0]][close[x][y][i][1]].isAlive() )
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
    public void putCell(int pos_x, int pos_y)
    {
        board[pos_x][pos_y].setAlive(true);
    }

    public void setCloseCellsPosition()
    {
        close = new int[BOARD_WIDTH][BOARD_HEIGHT][][];
        for (int x = 0; x < BOARD_WIDTH; x++)
        {
            for (int y = 0; y < BOARD_HEIGHT; y++)
            {
                close[x][y] = new int[][]{{x - 1, y - 1}, {x, y - 1}, {x + 1, y - 1}, {x - 1, y}, {x + 1, y}, {x - 1, y + 1}, {x, y + 1}, {x + 1, y + 1}};
            }
        }
    }
}