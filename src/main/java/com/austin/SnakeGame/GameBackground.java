package com.austin.SnakeGame;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class GameBackground extends JPanel {

    private int windowWidth;
    private int windowHeight;
    private int pixelSize;

    public GameBackground(int windowWidth, int windowHeight, int pixelSize) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.pixelSize = pixelSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

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
