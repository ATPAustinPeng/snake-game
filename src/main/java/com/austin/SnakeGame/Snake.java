package com.austin.SnakeGame;

import java.util.ArrayList;
import org.apache.commons.lang3.tuple.ImmutablePair;

// TODO : add a white/gray outline around each section of the snake.

/**
 * The Snake object the user controls to eat Food objects.
 */
public class Snake {
    /**
     * The pixel size of the Food object.
     */
    private int pixelSize;

    /**
     * The window width in pixels.
     */
    private int windowWidth;

    /**
     * The window height in pixels.
     */
    private int windowHeight;

    /**
     * The x-location of the Food object.
     */
    private int x;

    /**
     * The y-location of the Food object.
     */
    private int y;

    /**
     * The x-direction the snake is going in. (+1 means right, -1 means left)
     */
    private int xDirection;

    /**
     * The y-direction the snake is going in. (+1 means down, -1 means up)
     */
    private int yDirection;

    /**
     * The snake's score. (the amount of food eaten by the snake)
     */
    private int score;

    /**
     * The length of the snake's body, not including the head.
     */
    private int length;

    /**
     * An ArrayList containing Pairs of x, y locations of each section of the snake's body
     */
    private ArrayList<ImmutablePair<Integer, Integer>> body;

    /**
     * Whether the snake is dead or alive. (true = dead, false = alive)
     */
    private boolean dead;
    /**
     * Constructs a Snake object within the specified window width and window height.
     * @param windowWidth the window width (pixels)
     * @param windowHeight the window height (pixels)
     * @param pixelSize the pixel size of each section of the snake
     */
    public Snake(int windowWidth, int windowHeight, int pixelSize) {
        this.pixelSize = pixelSize;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.x = windowWidth / 2;
        this.y = windowHeight / 2;
        this.xDirection = 1;
        this.yDirection = 0;
        this.score = 0;
        this.length = 0;
        this.body = new ArrayList<ImmutablePair<Integer, Integer>>();
        this.dead = false;
    }

    /**
     * Updates the snake's position and the position of the rest of its body (if exists).
     */
    public void update() {
        this.x += xDirection * pixelSize;
        this.y += yDirection * pixelSize;

        // if length isn't equal to the score, add head at the end of the ArrayList and increase length by 1
        // however, if length == score, shift all elements in the ArrayList to the left
        if (length != score) {
            body.add(new ImmutablePair(this.x, this.y));
            this.length++;
        } else if (length == score) {
            for (int i = 0; i < body.size() - 1; i++) {
                body.set(i, body.get(i+1));
            }
            if (body.size() != 0) {
                body.set(body.size() - 1, new ImmutablePair(this.x, this.y));
            } else {
                body.add(new ImmutablePair(this.x, this.y));
            }
        }
    }

    /**
     * Sets the direction of the snake with the top left corner of the UI as (0,0) and the bottom right corner as (width, height)
     * @param xDirection the x direction of the snake (+1 is right, -1 is left)
     * @param yDirection the y direction of the snake (+1 is downwards, -1 is upwards)
     */
    public void direction(int xDirection, int yDirection) {
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }

    /**
     * Gets the x-location of the snake's head.
     * @return the x-location of the snake's head
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-location of the snake's head.
     * @return the y-location of the snake's head
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the ArrayList of x, y pairs of each section of the snake's body.
     * @return a list of x, y pairs of each section of the snake's body
     */
    public ArrayList<ImmutablePair<Integer, Integer>> getBody() {
        return body;
    }

    /**
     * Gets the current score (the amount of food the snake has eaten).
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * The direction the snake is going in.
     * @return a character representing the direction the snake is going ('R' for right, 'L' for left, 'D' for down, 'U' for up)
     */
    public char getDirection() {
        if (xDirection == -1 && yDirection == 0) {
            return 'L';
        } else if (xDirection == 1 && yDirection == 0) {
            return 'R';
        } else if (xDirection == 0 && yDirection == 1) {
            return 'D';
        } else if (xDirection == 0 && yDirection == -1) {
            return 'U';
        }
        return ' ';
    }

    /**
     * Increases the score by 1.
     */
    public void incrementScore() {
        this.score++;
    }

    /**
     * If the snake can eat food (x and y values equal food x and y values) increment score.
     * @param foodX the x-location of the food
     * @param foodY the y-location of the food
     * @return whether or not the snake can eat food (true = yes, false = no)
     */
    public boolean canEat(int foodX, int foodY) {
        if (this.x == foodX && this.y == foodY) {
            return true;
        }
        return false;
    }

    /**
     * Sets whether the snake is dead or not.
     * @param x whether the snake is dead or not (true = dead, false = not dead)
     */
    public void setDead(boolean x) {
        this.dead = x;
    }

    /**
     * Gets whether the snake is dead or not.
     * @return whether the snake is dead or not (true = dead, false = not dead)
     */
    public boolean isDead() {
        return this.dead;
    }

    /**
     * Checks if the snake is dead.
     */
    public void checkDead() {
        // if the snake hits itself
        for (int i = 0; i < body.size() - 1; i++) {
            if (this.x == body.get(i).getLeft() && this.y == body.get(i).getRight() && this.length != 0) {
                this.dead = true;
                return;
            }
        }

        //if the snake runs out of the border
        if (this.x > (this.windowWidth - this.pixelSize) || this.y > (this.windowHeight - this.pixelSize) || this.x < 0 || this.y < 0) {
            this.dead = true;
            return;
        }
        this.dead = false;
    }

    /**
     * If the snake is dead, set score to 0, set length and body to 0.
     */
    public void reset() {
        this.x = windowWidth / 2;
        this.y = windowHeight / 2;
        this.score = 0;
        this.length = 0;
        this.body = new ArrayList<ImmutablePair<Integer, Integer>>();
        this.dead = false;
    }
}
