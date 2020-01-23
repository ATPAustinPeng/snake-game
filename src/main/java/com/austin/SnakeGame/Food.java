package com.austin.SnakeGame;

import java.lang.Math;

/**
 * The Food object that the snake eats.
 */
public class Food {
    /**
     * The x-location of the Food object.
     */
    private int x;

    /**
     * The y-location of the Food object.
     */
    private int y;

    /**
     * The window width in pixels.
     */
    private int windowWidth;

    /**
     * The window height in pixels.
     */
    private int windowHeight;

    /**
     * The pixel size of the Food object.
     */
    private int pixelSize;

    //TODO :  make sure food is not generated on top of the snake

    /**
     * Constructs the Food object within the specified window width and window height.
     * @param windowWidth the window width (pixels)
     * @param windowHeight the window height (pixels)
     * @param pixelSize the pixel size of each Food object
     */
    public Food(int windowWidth, int windowHeight, int pixelSize) {
        this.pixelSize = pixelSize;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.x = (int)(Math.random() * (windowWidth - pixelSize) / pixelSize) * pixelSize;
        this.y = (int)(Math.random() * (windowHeight - pixelSize) / pixelSize) * pixelSize;
    }

    /**
     * Create a food object at coordinates x,y.
     * @param snake the Snake object
     */
    public void update(Snake snake) {
        this.x = (int)(Math.random() * (windowWidth - pixelSize) / pixelSize) * pixelSize;
        this.y = (int)(Math.random() * (windowHeight - pixelSize) / pixelSize) * pixelSize;
    }

    /**
     * Gets the x-location of the food object.
     * @return x-location of the food object
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-location of the food object.
     * @return the y-location of the food object
     */
    public int getY() {
        return y;
    }
}
