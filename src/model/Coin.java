package model;

import ui.GUI;

import java.awt.*;

public class Coin extends Entity {

    public Coin(int x, int y, double speed) {
        super(x, y, Game.COIN_SIZE, Game.COIN_SIZE);
        this.speed = speed;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(getX() - width / 2, getY() - height / 2, width, height);
        g.setColor(Color.BLACK);
        g.drawOval(getX() - width / 2, getY() - height / 2, width, height);
    }

    @Override
    public void move() {
        x -= speed;

        super.move();
    }
}
