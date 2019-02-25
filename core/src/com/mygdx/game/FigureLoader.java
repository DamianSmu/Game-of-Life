package com.mygdx.game;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FigureLoader
{
    private int width;
    private int height;
    private int[][] source;

    public FigureLoader(String path)
    {
        width = 0;
        height = 0;
        try
        {
            Scanner scan = new Scanner(new File(path));
            while (scan.hasNext())
            {
                height++;
                width = scan.next().length();
            }
            scan = new Scanner(new File((path)));
            source = new int[height][width];
            int i = 0;
            while (scan.hasNext())
            {
                String line = scan.next();
                for (int j = 0; j < line.length(); j++)
                {
                    source[i][j] = line.charAt(j) == '1' ? 1 : 0;
                }
                i++;
            }
        } catch (IOException e)
        {
        }
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public int[][] getSource()
    {
        return source;
    }
}
