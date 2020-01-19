package com.austin.SnakeGame;

import java.lang.Math;

public class Food {
    private int x;
    private int y;
    private int windowWidth;
    private int windowHeight;
    private int foodPixelSize;

    public Food(int windowWidth, int windowHeight) {
        this.foodPixelSize = 20;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.x = (int)(Math.random() * (windowWidth - foodPixelSize) / foodPixelSize) * foodPixelSize;
        this.y = (int)(Math.random() * (windowHeight - foodPixelSize) / foodPixelSize) * foodPixelSize;
    }

    public void update() {
        this.x = (int)(Math.random() * (windowWidth - foodPixelSize) / foodPixelSize) * foodPixelSize;
        this.y = (int)(Math.random() * (windowHeight - foodPixelSize) / foodPixelSize) * foodPixelSize;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
