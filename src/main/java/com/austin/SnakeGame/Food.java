package com.austin.SnakeGame;

import java.lang.Math;

public class Food {
    private int x;
    private int y;
    private int windowWidth;
    private int windowHeight;
    private int pixelSize;

    //TODO :  make sure food is not generated on top of the Snake
    public Food(int windowWidth, int windowHeight, int pixelSize) {
        this.pixelSize = pixelSize;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.x = (int)(Math.random() * (windowWidth - pixelSize) / pixelSize) * pixelSize;
        this.y = (int)(Math.random() * (windowHeight - pixelSize) / pixelSize) * pixelSize;
    }

    public void update() {
        this.x = (int)(Math.random() * (windowWidth - pixelSize) / pixelSize) * pixelSize;
        this.y = (int)(Math.random() * (windowHeight - pixelSize) / pixelSize) * pixelSize;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPixelSize() {
        return pixelSize;
    }
}
