package model;

import ui.GUI;

import java.awt.*;

public class Obstacle extends Entity {

    private Image crate;

    public Obstacle(int x, int y, int width, int height, double speed) {
        super(x, y, width, height);
        this.speed = speed;
        crate = Toolkit.getDefaultToolkit().getImage("data/crate.png")
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    @Override
    public void draw(Graphics g) {
//        g.setColor(Color.RED);
//        g.fillRect(getX() - width / 2, getY() - height / 2, width, height);
//        g.setColor(Color.BLACK);
//        g.drawRect(getX() - width / 2, getY() - height / 2, width, height);
        g.drawImage(crate, getX() - width / 2, getY() - height / 2, null);
    }

    @Override
    public void move() {
        x -= speed;

        super.move();
    }
}
