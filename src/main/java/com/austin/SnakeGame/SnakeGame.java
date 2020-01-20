package com.austin.SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// TODO : why does Snake.java uses unchecked or unsafe operations
// TODO : remove build/ files frrom git push
// TODO : layer 3 JPanels (start screen), death screen, game screen (display score) + background screen
// TODO : add commands to restart, pause, etc.
// TODO : ex. if you are going to the right, if you quickly press UP then LEFT, you are allowed to go left but you die...
public class SnakeGame extends JFrame {

    // TODO : SOLVE THIS PLZ window height 800 + 22 (JFrame insets... includes the border at the top)
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 822;

    /**
     * The size of each chunk of the snake and each block of food.
     */
    public static final int PIXEL_SIZE = 25;

    public static GameBoard board;
    public static GameBackground background;

    public static void main(String[] args) throws InterruptedException {
        SnakeGame gameFrame = new SnakeGame();
        gameFrame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gameFrame.setVisible(true);

        JPanel layeredPanel = new JPanel();

        Food food = new Food(WINDOW_WIDTH, WINDOW_HEIGHT - 22, PIXEL_SIZE);
        Snake snake = new Snake(WINDOW_WIDTH, WINDOW_HEIGHT - 22, PIXEL_SIZE);

        board = new GameBoard(snake, food);
        board.setOpaque(false);
        background = new GameBackground(WINDOW_WIDTH, WINDOW_HEIGHT - 22, PIXEL_SIZE);
        background.setOpaque(false);


        LayoutManager overlay = new OverlayLayout(layeredPanel);
        layeredPanel.setLayout(overlay);
        layeredPanel.add(board);
        layeredPanel.add(background);

        gameFrame.add(layeredPanel);

        while(true) {
            board.update();
            board.repaint();
            Thread.sleep(100);
        }
    }

    public SnakeGame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}
