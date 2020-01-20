package com.austin.SnakeGame;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Does the drawing and displaying of the game board (background, snake, food)
 */
public class GameBoard extends JPanel {

    /**
     * The snake object that is used on the game board.
     */
    private Snake snake;

    /**
     * The food object that is placed on the game board.
     */
    private Food food;

    /**
     * Constructs a game board with a snake and food.
     * @param snake a snake object
     * @param food a food object
     */
    public GameBoard(Snake snake, Food food) {
        this.snake = snake;
        this.food = food;
    }

    /**
     * Updates the game board.
     * More specifically updates the snake's movement and food regeneration after eaten. Also checks if the snake died.
     * @throws InterruptedException
     */
    public void update() throws InterruptedException {
        snake.update();

        if (snake.isDead()) {
            snake.reset();
            snake.update();
            repaint();
            Thread.sleep(1000);
        }

        if(snake.canEat(food.getX(), food.getY())) {
            food.update();
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSnake(g);
        drawFood(g);
    }

    /**
     * Helper method for paintComponent. Draws the snake onto the board.
     * @param g Graphics object
     */
    public void drawSnake(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(snake.getX(), snake.getY(), snake.getPixelSize(), snake.getPixelSize());

        for (int i = 0; i < snake.getBody().size(); i++) {
            g2d.fillRect(snake.getBody().get(i).getLeft(), snake.getBody().get(i).getRight(), snake.getPixelSize(), snake.getPixelSize());
        }
    }

    /**
     * Helper method for paintComponent. Draws food onto the game board.
     * @param g Graphics object
     */
    public void drawFood(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fillRect(food.getX(), food.getY(), food.getPixelSize(), food.getPixelSize());
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }
}