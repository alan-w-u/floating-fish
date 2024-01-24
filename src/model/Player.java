package model;

import java.awt.*;

// Represents a player that can fly up and down
public class Player extends Entity {

    private boolean falling;
    private Image fish;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.falling = true;
        fish = Toolkit.getDefaultToolkit().getImage("data/fish.png")
                .getScaledInstance(Game.PLAYER_WIDTH, Game.PLAYER_HEIGHT, Image.SCALE_SMOOTH);
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(Boolean falling) {
        this.falling = falling;
    }

    @Override
    public void draw(Graphics g) {
//        g.setColor(Color.GREEN);
//        g.fillRect(getX() - width / 2, getY() - height / 2, width, height);
//        g.setColor(Color.BLACK);
//        g.drawRect(getX() - width / 2, getY() - height / 2, width, height);
        g.drawImage(fish, getX() - width / 2, getY() - height / 2, null);
    }

    @Override
    public void move() {
        y += speed;

        super.move();
    }
}
