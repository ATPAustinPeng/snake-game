package com.austin.SnakeGame;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Creates the background tiles (light green/dark green) for the snake game.
 */
public class GameBackground extends JPanel {

    /**
     * The window width in pixels.
     */
    private int windowWidth;

    /**
     * The window height in pixels.
     */
    private int windowHeight;

    /**
     * The pixel size of each light green or dark green tile drawn.
     */
    private int pixelSize;

    /**
     * Constructs a Game Background object within the specified window width and window height,
     * and tiles with the specified pixelSize.
     * @param windowWidth the window width (pixels)
     * @param windowHeight the window height (pixels)
     * @param pixelSize the pixel size of each tile of the background
     */
    public GameBackground(int windowWidth, int windowHeight, int pixelSize) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.pixelSize = pixelSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        drawBackground(g2d);
    }

    /**
     * Helper method for the paintComponent method.
     * Draws the game background with alternating tile colors (like a checkerboard)
     * @param g2d the Graphics2D object used to draw the background
     */
    private void drawBackground(Graphics2D g2d) {
        Color lightGreen = new Color(148,237,143);
        Color darkGreen = new Color(136, 225, 130);

        for (int i = 0; i < this.windowWidth; i += this.pixelSize) {
            for (int j = 0; j < this.windowHeight; j += this.pixelSize) {
                if (((i + j) / this.pixelSize) % 2 == 0) {
                    g2d.setColor(lightGreen);
                } else {
                    g2d.setColor(darkGreen);
                }
                g2d.fillRect(i , j, this.pixelSize, this.pixelSize);
            }
        }
    }
}
