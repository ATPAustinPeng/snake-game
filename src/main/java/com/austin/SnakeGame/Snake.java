package com.austin.SnakeGame;

import java.util.ArrayList;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class Snake {
    private int snakePixelSize;
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private int speedMultiplier;
    private int score;

    /**
     * The length of the snake's body, not including the head.
     */
    private int length;
    private ArrayList<ImmutablePair<Integer, Integer>> body;

    public Snake(int windowWidth, int windowHeight) {
        this.snakePixelSize = 20;
        this.x = windowWidth / 2;
        this.y = windowHeight / 2;
        this.speedMultiplier = 1;
        this.xSpeed = 1;
        this.ySpeed = 0;
        this.score = 0;

        this.length = 0;
        this.body = new ArrayList<ImmutablePair<Integer, Integer>>();
    }

    public void update() {
        this.x += xSpeed * speedMultiplier * snakePixelSize;
        this.y += ySpeed * speedMultiplier * snakePixelSize;

        // if length isn't equal to the score, shift all elements in the ArrayList to the left
        // insert the head at the end of the ArrayList
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
        printSnakeBody();
    }

    public void printSnakeBody() {
        for (int i = 0; i < body.size(); i++) {
            System.out.println("(" + body.get(i).getLeft() + ", " + body.get(i).getRight() + ")");
        }
        System.out.println("____________________");
    }

    public void direction(int xSpeed, int ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
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

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public int getSpeedMultiplier() {
        return speedMultiplier;
    }

    public ArrayList<ImmutablePair<Integer, Integer>> getBody() {
        return body;
    }

    public char getDirection() {
        if (xSpeed == -1 && ySpeed == 0) {
            return 'L';
        } else if (xSpeed == 1 && ySpeed == 0) {
            return 'R';
        } else if (xSpeed == 0 && ySpeed == 1) {
            return 'D';
        } else if (xSpeed == 0 && ySpeed == -1) {
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
            System.out.println(score);
            return true;
        }
        return false;
    }

    /**
     * Checks if the snake is dead. If the snake is dead, set score to 0, set length and body to 0.
     * @return
     */
    public boolean isDead() {
        for (int i = 0; i < body.size() - 1; i++) {
            if (this.x == body.get(i).getLeft() && this.y == body.get(i).getRight() && this.length != 0) {
                System.out.println("dead");
                this.score = 0;
                this.length = 0;
                this.body = new ArrayList<ImmutablePair<Integer, Integer>>();
                return true;
            }
        }
        return false;
    }
}
