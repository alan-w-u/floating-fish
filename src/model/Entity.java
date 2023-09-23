package model;

import ui.*;

import java.awt.*;

// Represents an entity in the game
public abstract class Entity {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected double speed;

    // Constructs an entity
    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = 0.0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    // Draws the entity
    public abstract void draw(Graphics g);

    // Moves the entity, keeps it within the vertical height
    public void move() {
        if (y < getHeight() / 2) {
            y = getHeight() / 2;
        } else if (y > GUI.HEIGHT - Game.PLAYER_HEIGHT - Game.PLAYER_HEIGHT / 2) {
            y = GUI.HEIGHT - Game.PLAYER_HEIGHT - Game.PLAYER_HEIGHT / 2;
        }
    }

    // Check for collision
    public boolean collidedWith(Entity other) {
        Rectangle thisBoundingRect = new Rectangle(getX() - getWidth() / 2, getY() - getHeight() / 2,
                getWidth(), getHeight());
        Rectangle otherBoundingRect = new Rectangle(other.getX() - other.getWidth() / 2,
                other.getY() - other.getHeight() / 2, other.getWidth(), other.getHeight());
        return thisBoundingRect.intersects(otherBoundingRect);
    }
}
