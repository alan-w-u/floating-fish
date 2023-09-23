package model;

import ui.GUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Represents the game
public class Game {

    private List<Entity> entities;
    private final Player player;

    public static final Random RANDOM = new Random();
    public static int score = 0;
    private int difficulty_counter = 0;
    private final double difficulty_threshold = GUI.TIME_INTERVAL * 100;

    public static final int PLAYER_X = GUI.WIDTH / 10;
    public static final int PLAYER_Y = GUI.HEIGHT;
    public static final int PLAYER_WIDTH = GUI.WIDTH / 25;
    public static final int PLAYER_HEIGHT = GUI.HEIGHT / 15;

    public static final double UP_SPEED = - (double) GUI.HEIGHT / 35 * GUI.TIME_INTERVAL / 20;
    public static final double DOWN_SPEED = (double) GUI.HEIGHT / 40 * GUI.TIME_INTERVAL / 20;
    public static double speed = GUI.TIME_INTERVAL / 2;

    private final int COIN_CHANCE = (int) GUI.TIME_INTERVAL * 40;
    public static final int COIN_SIZE = Game.PLAYER_HEIGHT;

    private int obstacleChance = (int) GUI.TIME_INTERVAL * 20;
    private final int OBSTACLE_WIDTH_MIN = GUI.WIDTH / 40;
    private final int OBSTACLE_WIDTH_MAX = GUI.WIDTH / 40 - OBSTACLE_WIDTH_MIN + 1;
    private final int OBSTACLE_HEIGHT_MIN = GUI.HEIGHT / 5;
    private final int OBSTACLE_HEIGHT_MAX = GUI.HEIGHT / 3 - OBSTACLE_HEIGHT_MIN + 1;

    public Game() {
        entities = new ArrayList<>();
        player = new Player(PLAYER_X, PLAYER_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
        entities.add(player);
    }

    public Player getPlayer() {
        return player;
    }

    public void update() {
        putObstacles();
        putCoins();
        checkCollision();
        acceleration();
        move();
        removeObstacles();
        updateDifficulty();
    }

    public void move() {
        for(Entity entity : entities) {
            entity.move();
        }
    }

    public void draw(Graphics g) {
        for (Entity entity : entities) {
            entity.draw(g);
        }
    }

    // Responds to keyboard key presses
    public void keyPress(int key) {
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W) {
            player.setFalling(false);
            player.move();
        } else if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    // Handle the acceleration when changing directions
    public void acceleration() {
        if (!player.isFalling()) {
            if (player.getSpeed() > UP_SPEED) {
                player.setSpeed(player.getSpeed() - GUI.TIME_INTERVAL / 25);
            }
        } else {
            if (player.getSpeed() < DOWN_SPEED) {
                player.setSpeed(player.getSpeed() + GUI.TIME_INTERVAL / 25);
            }
        }
    }

    // Check if the player has collided with an obstacle
    public void checkCollision() {

        Entity player = entities.get(0);

        for (Entity e : entities) {
            if (e instanceof Obstacle) {
                if (e.collidedWith(player)) {
                    System.exit(0);
                }
            }

            if (e instanceof Coin) {
                if (e.collidedWith(player)) {
                    removeCoin((Coin) e);
                    score++;
                }
            }
        }
    }

    // Return true if entities are overlapping
    public boolean checkOverlap(Entity e1) {
        for (Entity e2 : entities) {
            if (e2 instanceof Obstacle || e2 instanceof Coin) {
                if (e2.collidedWith(e1)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Randomly places obstacles
    public void putObstacles() {
        if (RANDOM.nextInt(obstacleChance) == 0) {

            Obstacle obstacle;

            if (RANDOM.nextInt(2) == 0) {
                // Creates the vertical obstacles
                obstacle = new Obstacle(GUI.WIDTH, RANDOM.nextInt(GUI.HEIGHT - OBSTACLE_HEIGHT_MAX),
                        (RANDOM.nextInt(OBSTACLE_WIDTH_MAX) + OBSTACLE_WIDTH_MIN),
                        (RANDOM.nextInt(OBSTACLE_HEIGHT_MAX) + OBSTACLE_HEIGHT_MIN), speed);
            } else {
                // Creates the horizontal obstacles
                if (RANDOM.nextInt(2) == 0) {
                    // Create obstacles on roof
                    obstacle = new Obstacle(GUI.WIDTH, (RANDOM.nextInt(GUI.HEIGHT / 8)),
                            (RANDOM.nextInt(OBSTACLE_HEIGHT_MAX) + OBSTACLE_HEIGHT_MIN),
                            (RANDOM.nextInt(OBSTACLE_WIDTH_MAX) + OBSTACLE_WIDTH_MIN), speed);
                } else {
                    // Create obstacles on floor
                    obstacle = new Obstacle(GUI.WIDTH, (RANDOM.nextInt(GUI.HEIGHT / 8) + GUI.HEIGHT / 8 * 7),
                            (RANDOM.nextInt(OBSTACLE_HEIGHT_MAX) + OBSTACLE_HEIGHT_MIN),
                            (RANDOM.nextInt(OBSTACLE_WIDTH_MAX) + OBSTACLE_WIDTH_MIN), speed);
                }
            }

            if (!checkOverlap(obstacle)) {
                entities.add(obstacle);
            }
        }
    }

    // Randomly places coins
    public void putCoins() {
        if (RANDOM.nextInt(COIN_CHANCE) == 0) {
            Coin coin = new Coin(GUI.WIDTH, RANDOM.nextInt(GUI.HEIGHT), speed);

            if (!checkOverlap(coin)) {
                entities.add(coin);
            }
        }
    }

    // Removes obstacles that are off the screen
    public void removeObstacles() {
        for (Entity e1 : entities) {
            if (e1 instanceof Obstacle && e1.getX() < - e1.getWidth() - e1.getHeight()) {
                List<Entity> list = new ArrayList<>();

                for (Entity e2 : entities) {
                    if (!e2.equals(e1)) {
                        list.add(e2);
                    }
                }

                entities = list;
            }
        }
    }

    // Removes the given coin
    public void removeCoin(Coin coin) {
        List<Entity> list = new ArrayList<>();

        for (Entity e : entities) {
            if (!e.equals(coin)) {
                list.add(e);
            }
        }

        entities = list;
    }

    // Increases speed of objects as time passes
    public void updateDifficulty() {
        difficulty_counter++;

        if (difficulty_counter >= difficulty_threshold && obstacleChance > 0) {
            speed += 0.1;
            obstacleChance--;
            difficulty_counter = 0;

            for (Entity e : entities) {
                if (!(e instanceof Player)) {
                    e.setSpeed(speed);
                }
            }
        }
    }
}
