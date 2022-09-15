package org.academiadecodigo.shooting_academy;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public class Grid {

    private Rectangle grid;
    private final int padding = 10;

    public Grid() {

        int cellSize = 15;
        int maxCols = 85;
        int maxRows = 48;
        int width = maxCols * cellSize;
        int height = maxRows * cellSize;

        grid = new Rectangle(padding,padding, width, height);
        grid.draw();
        drawGrid();

    }


    public void drawGrid() {

        Picture backround = new Picture(padding, padding, "BackgroundGame.png");
        backround.draw();

    }
}
