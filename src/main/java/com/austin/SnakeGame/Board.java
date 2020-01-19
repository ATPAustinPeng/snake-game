package com.austin.SnakeGame;

import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

// TODO: How do i deal with this Jframe.getInsets(); problem...
// I want my window height consistent...

public class Board extends JPanel {

    public static final int WINDOW_WIDTH = 800;

    // window height 800 + 22 (JFrame insets... includes the border at the top)
    public static final int WINDOW_HEIGHT = 822;
    public static int snakeX;
    public static int snakeY;
    public static int foodX;
    public static int foodY;

    public static ArrayList<ImmutablePair<Integer, Integer>> snakeBody;

    public static void main(String[] args) throws InterruptedException {
        Snake snake = new Snake(WINDOW_WIDTH, WINDOW_HEIGHT - 22);
        Food food = new Food(WINDOW_WIDTH, WINDOW_HEIGHT - 22);
        Board board = new Board();

        JFrame gameFrame = new JFrame();
        gameFrame.add(board);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
        gameFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        gameFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
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

        snakeBody = snake.getBody();
        while(true) {
            board.update(board, snake, food);
            board.repaint();
            Thread.sleep(100);
        }
    }

    public void update(Board board, Snake snake, Food food) {
        if (snake.isDead()) {
            snakeBody = snake.getBody();
        }
        snake.update();
        snakeX = snake.getX();
        snakeY = snake.getY();
        foodX = food.getX();
        foodY = food.getY();

        if (snake.canEat(foodX, foodY)) {
            food.update();
            foodX = food.getX();
            foodY = food.getY();
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(snakeX, snakeY, 20, 20);
        for (int i = 0; i < snakeBody.size(); i++) {
            g2d.fillRect(snakeBody.get(i).getLeft(), snakeBody.get(i).getRight(), 20, 20);
        }
        g2d.setColor(Color.RED);
        g2d.fillRect(foodX, foodY, 20, 20);
    }
}
