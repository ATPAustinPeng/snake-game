package com.austin.SnakeGame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// TODO : why does Snake.java uses unchecked or unsafe operations
// TODO : layer 3 JPanels (start screen), death screen, game screen (display score) + background screen
// TODO : add commands/buttons to restart, pause, etc.
// TODO : ex. if you are going to the right, if you quickly press UP then LEFT, you are allowed to go left but you die...

/**
 * The client class that actually runs the snake game.
 */
public class SnakeGameClient extends JFrame {

    /**
     * The specified window width.
     */
    public static final int WINDOW_WIDTH = 800;

    /**
     * The specified window height plus the value of the window's insets (22 pixels high).
     */
    public static final int WINDOW_HEIGHT = 822;

    /**
     * The size of each tile, chunk of the snake, and each block of food.
     */
    public static final int PIXEL_SIZE = 40;

    /**
     * A JPanel containing the snake and food objects.
     */
    public static GameBoard board;

    /**
     * A JPanel containing the alternating colored tiles for the background.
     */
    public static GameBackground background;

    public static void main(String[] args) throws InterruptedException {
        // Creates the JFrame for the snake game.
        SnakeGameClient gameFrame = new SnakeGameClient();

        // Creates a JPanel for overlaying the game board with the background.
        JPanel gameAndBackground = new JPanel();

        // Creates a food object and a snake object.
        Food food = new Food(WINDOW_WIDTH, WINDOW_HEIGHT - 22, PIXEL_SIZE);
        Snake snake = new Snake(WINDOW_WIDTH, WINDOW_HEIGHT - 22, PIXEL_SIZE);

        // instantiates the game board and game background
        board = new GameBoard(WINDOW_WIDTH, WINDOW_HEIGHT - 22, PIXEL_SIZE, snake, food);
        board.setOpaque(false);
        background = new GameBackground(WINDOW_WIDTH, WINDOW_HEIGHT - 22, PIXEL_SIZE);
        background.setOpaque(true);

        // layout manager merging the for the game board and the background
        LayoutManager gameOverlay = new OverlayLayout(gameAndBackground);
        gameAndBackground.setLayout(gameOverlay);
        gameAndBackground.add(board);
        gameAndBackground.add(background);

        // adds the JPanel with the layered board and background to the frame
        gameFrame.add(gameAndBackground);


        // Starts the game!
        Thread.sleep(2000);
        while(!snake.isDead()) {
            board.update();
            board.repaint();
            Thread.sleep(100);
        }
    }

    /**
     * Creates the JFrame to run the snake game.
     * The frame tracks key presses the change the direction of the snake accordingly.
     * Press the 'Q' key to close the game.
     * Press the 'R' key to restart.
     */
    public SnakeGameClient() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setTitle("Snake by Austin Peng");
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                Snake snake = board.getSnake();
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT && snake.getDirection() != 'R') {
                    snake.direction(-1, 0);
                } else if (keyCode == KeyEvent.VK_RIGHT && snake.getDirection() != 'L') {
                    snake.direction(1, 0);
                } else if (keyCode == KeyEvent.VK_DOWN && snake.getDirection() != 'U') {
                    snake.direction(0, 1);
                } else if (keyCode == KeyEvent.VK_UP  && snake.getDirection() != 'D') {
                    snake.direction(0, -1);
                } else if (keyCode == KeyEvent.VK_R) {
                    snake.setDead(true);
                    snake.reset();
                    snake.update();
                    board.getScore().setText("Score: " + snake.getScore());
                    board.repaint();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else if (keyCode == KeyEvent.VK_Q) {
                    System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}
