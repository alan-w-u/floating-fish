package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;

//import java.io.File;
//import javafx.beans.binding.Bindings;
//import javafx.beans.property.DoubleProperty;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Scene;
//import javafx.scene.layout.StackPane;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaView;
//import javafx.stage.Screen;

public class GamePanel extends JPanel {

    private Game game;
    private Image background;

    public GamePanel(Game game) {
        this.game = game;
        //        getVideo();
        background = Toolkit.getDefaultToolkit().getImage("data/background.png")
                .getScaledInstance(GUI.WIDTH / 100 * 180, GUI.HEIGHT / 100 * 120, Image.SCALE_DEFAULT);
        setPreferredSize(new Dimension(GUI.WIDTH, GUI.HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);

        g.setColor(new Color(175, 215, 230));
        g.fillRect(0, 0, GUI.WIDTH / 15, GUI.HEIGHT / 15);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, GUI.WIDTH / 15, GUI.HEIGHT / 15);
        g.setFont(new Font("Arial", Font.BOLD, GUI.HEIGHT / 15));

        if (Game.score < 10) {
            g.drawString(Integer.toString(Game.score), GUI.WIDTH / 40, GUI.HEIGHT / 18);
        } else if (Game.score < 100) {
            g.drawString(Integer.toString(Game.score), GUI.WIDTH / 55, GUI.HEIGHT / 18);
        } else if (Game.score < 1000) {
            g.drawString(Integer.toString(Game.score), GUI.WIDTH / 95, GUI.HEIGHT / 18);
        } else {
            g.drawString(Integer.toString(Game.score), GUI.WIDTH / 300, GUI.HEIGHT / 18);
        }

        game.draw(g);
    }

//    private void getVideo(){
//        final JFXPanel VFXPanel = new JFXPanel();
//
//        File video_source = new File("data/background-video.mp4");
//        Media m = new Media(video_source.toURI().toString());
//        MediaPlayer player = new MediaPlayer(m);
//        MediaView viewer = new MediaView(player);
//
//        StackPane root = new StackPane();
//        Scene scene = new Scene(root);
//
//        // center video position
//        javafx.geometry.Rectangle2D screen = Screen.getPrimary().getVisualBounds();
//        viewer.setX((screen.getWidth() - this.getWidth()) / 2);
//        viewer.setY((screen.getHeight() - this.getHeight()) / 2);
//
//        // resize video based on screen size
//        DoubleProperty width = viewer.fitWidthProperty();
//        DoubleProperty height = viewer.fitHeightProperty();
//        width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
////        height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
//        viewer.setPreserveRatio(true);
//
//        // add video to stackpane
//        root.getChildren().add(viewer);
//
//        VFXPanel.setScene(scene);
//        player.play();
//        this.setLayout(new BorderLayout());
//        this.add(VFXPanel, BorderLayout.CENTER);
//    }

}
