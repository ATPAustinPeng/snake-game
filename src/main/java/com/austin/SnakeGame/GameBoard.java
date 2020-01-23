package com.austin.SnakeGame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;


/**
 * Does the drawing and displaying of the game board (background, snake, food)
 */
public class GameBoard extends JPanel {

    /**
     * The pixel size of each section of the snake and each piece of food.
     */
    private int pixelSize;

    /**
     * The snake object that is used on the game board.
     */
    private Snake snake;

    /**
     * The food object that is placed on the game board.
     */
    private Food food;

    // TODO : put the label in the top right corner OR make a little bar at the top, with username, high score, and your score?
    //  Make the font color white.
    /**
     * The label for displaying the score.
     */
    private JLabel score;

    /**
     * Constructs a game board with a snake and food with the specified window width and window height.
     * @param windowWidth the window width (pixels)
     * @param windowHeight the window height (pixels)
     * @param pixelSize the pixel size of each block in the game
     * @param snake a Snake object
     * @param food a Food object
     */
    public GameBoard(int windowWidth, int windowHeight, int pixelSize, Snake snake, Food food) {
        this.snake = snake;
        this.food = food;
        this.pixelSize = pixelSize;
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.score = new JLabel("Score: 0");
        this.score.setOpaque(false);
        add(score);
    }

    /**
     * Updates the game board.
     * More specifically updates the snake's movement and food regeneration after eaten. Also checks if the snake died.
     * @throws InterruptedException
     */
    public void update() throws InterruptedException {
        snake.update();
        snake.checkDead();

        if (snake.isDead()) {
            snake.reset();
            snake.update();
            score.setText("Score: " + snake.getScore());
            repaint();
            Thread.sleep(1000);
        }

        if(snake.canEat(food.getX(), food.getY())) {
            snake.incrementScore();
            score.setText("Score: " + snake.getScore());
            food.update(snake);
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
     * Helper method for paintComponent.
     * Draws the snake onto the board.
     * @param g Graphics object
     */
    public void drawSnake(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(snake.getX(), snake.getY(), pixelSize, pixelSize);

        for (int i = 0; i < snake.getBody().size(); i++) {
            g2d.fillRect(snake.getBody().get(i).getLeft(), snake.getBody().get(i).getRight(), pixelSize, pixelSize);
        }
    }

    /**
     * Helper method for paintComponent.
     * Draws food onto the game board.
     * @param g Graphics object
     */
    public void drawFood(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fillRect(food.getX(), food.getY(), pixelSize, pixelSize);
    }

    /**
     * Gets the snake object.
     * @return the Snake object
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Gets the food object.
     * @return the Food object
     */
    public Food getFood() {
        return food;
    }

    /**
     * Gets the JLabel object that is recording score.
     * @return the JLabel displaying the score
     */
    public JLabel getScore() {
        return score;
    }
}