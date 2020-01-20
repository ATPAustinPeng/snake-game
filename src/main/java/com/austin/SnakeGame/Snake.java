package com.austin.SnakeGame;

import java.util.ArrayList;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class Snake {
    private int pixelSize;
    private int windowWidth;
    private int windowHeight;
    private int x;
    private int y;
    private int xDirection;
    private int yDirection;
    private int speedMultiplier;
    private int score;

    /**
     * The length of the snake's body, not including the head.
     */
    private int length;
    private ArrayList<ImmutablePair<Integer, Integer>> body;

    public Snake(int windowWidth, int windowHeight, int pixelSize) {
        this.pixelSize = pixelSize;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.x = windowWidth / 2;
        this.y = windowHeight / 2;
        this.speedMultiplier = 1;
        this.xDirection = 1;
        this.yDirection = 0;
        this.score = 0;

        this.length = 0;
        this.body = new ArrayList<ImmutablePair<Integer, Integer>>();
    }

    /**
     * Updates the snake's position and the position of the rest of its body (if exists).
     */
    public void update() {
        this.x += xDirection * speedMultiplier * pixelSize;
        this.y += yDirection * speedMultiplier * pixelSize;

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

    public void speedUp() {
        this.speedMultiplier += 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getxDirection() {
        return xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public int getSpeedMultiplier() {
        return speedMultiplier;
    }

    public ArrayList<ImmutablePair<Integer, Integer>> getBody() {
        return body;
    }

    public int getPixelSize() {
        return pixelSize;
    }

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
     * If the snake can eat food (x and y values equal food x and y values)
     * increment score, add 1 section to the snake's body.
     * @param foodX the x-location of the food
     * @param foodY the y-location of the food
     * @return whether or not the snake can eat food (true = yes, false = no)
     */
    public boolean canEat(int foodX, int foodY) {
        if (this.x == foodX && this.y == foodY) {
            this.score++;
            return true;
        }
        return false;
    }

    /**
     * Checks if the snake is dead.
     * @return
     */
    public boolean isDead() {
        // if the snake hits itself
        for (int i = 0; i < body.size() - 1; i++) {
            if (this.x == body.get(i).getLeft() && this.y == body.get(i).getRight() && this.length != 0) {
                return true;
            }
        }

        //if the snake runs out of the border
//        if (this.x > this.windowWidth || this.y > this.windowHeight || this.x < 0 || this.y < 0) {
        if (this.x > (this.windowWidth - this.pixelSize) || this.y > (this.windowHeight - this.pixelSize) || this.x < 0 || this.y < 0) {
            return true;
        }
        return false;
    }

    //If the snake is dead, set score to 0, set length and body to 0.
    public void reset() {
        this.x = windowWidth / 2;
        this.y = windowHeight / 2;
        this.score = 0;
        this.length = 0;
        this.body = new ArrayList<ImmutablePair<Integer, Integer>>();
    }
}
