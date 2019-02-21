package com.mygdx.game;



import com.badlogic.gdx.scenes.scene2d.Stage;

public class CellMenager
{
    private int boardWidth;
    private int boardHeight;
    private Stage stage;
    private Cell[][] board;

    public CellMenager(int boardWidth, int boardHeight, Stage stage)
    {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.stage = stage;

        board = new Cell[boardWidth][boardHeight];

        for (int x = 0; x < boardWidth; x++)
        {
            for (int y = 0; y < boardHeight; y++)
            {
            board[x][y] = new Cell(20*x, 20*y, stage);
            }
        }
    }

    public void setNeighboors()
    {
        for (int x = 0; x < boardWidth; x++)
        {
            for (int y = 0; y < boardHeight; y++)
            {
                Point[] close = {new Point(x - 1, y - 1), new Point(x, y - 1), new Point(x + 1, y - 1), new Point(x - 1, y), new Point(x + 1, y), new Point(x - 1, y + 1), new Point(x, y + 1), new Point(x + 1, y + 1)};
                int cells = 0;
                for (int i = 0; i < 8; i++)
                {
                    if(close[i].x > 0 && close[i].x < boardWidth && close[i].y > 0 && close[i].y < boardHeight && board[close[i].x][close[i].y].isAlive() )
                        cells++;
                }
                board[x][y].setNeighboors(cells);
            }
        }
        step();
    }

    public void step()
    {
        for (int x = 0; x < boardWidth; x++)
        {
            for (int y = 0; y < boardHeight; y++)
            {
                board[x][y].step();
            }
        }
    }
}
