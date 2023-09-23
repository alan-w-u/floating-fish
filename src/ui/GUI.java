package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JFrame {

    private Game game;
    private GamePanel gamePanel;
    private Timer timer;

    public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static final int WIDTH = SCREEN_WIDTH / 10 * 8;
    public static final int HEIGHT = SCREEN_HEIGHT / 10 * 5;

    public static final double TIME_INTERVAL = 5;

    public GUI() {
        game = new Game();
        gamePanel = new GamePanel(game);

        setTitle("Floating Fish");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation((SCREEN_WIDTH - WIDTH) / 2, (SCREEN_HEIGHT - HEIGHT) / 2);

        refresh();
        addTimer();
        timer.start();
        addKeyListener(new KeyHandler());

        add(gamePanel);

        setVisible(true);
    }

    // Refreshes the images on the screen
    public void refresh() {
        revalidate();
        repaint();
    }

    // Creates an in game clock which performs action on tick
    private void addTimer() {
        timer = new Timer((int) TIME_INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.update();
                refresh();
            }
        });
    }

    // Represents a key handler that responds to keyboard key presses
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPress(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
//            game.getPlayer().setSpeed(Game.DOWN_SPEED);
            game.getPlayer().setFalling(true);
        }
    }
}
