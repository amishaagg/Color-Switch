import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Game
{
    private static ArrayList<GameElement> gameElements;
    private static ArrayList<Obstacle> obstacles;
    private static ArrayList<Star> stars;
    private static ArrayList<ColorSwitcher> colorSwitchers;
    private static Player player;

    public static ArrayList<GameElement> getGameElements() {
        return gameElements;
    }

    public static void setGameElements(ArrayList<GameElement> gameElements) {
        Game.gameElements = gameElements;
    }

    public static ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public static void setObstacles(ArrayList<Obstacle> obstacles) {
        Game.obstacles = obstacles;
    }

    public static ArrayList<Star> getStars() {
        return stars;
    }

    public static void setStars(ArrayList<Star> stars) {
        Game.stars = stars;
    }

    public static ArrayList<ColorSwitcher> getColorSwitchers() {
        return colorSwitchers;
    }

    public static void setColorSwitchers(ArrayList<ColorSwitcher> colorSwitchers) {
        Game.colorSwitchers = colorSwitchers;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Game.player = player;
    }

    Button btnPause;
    public Game(Stage window)
    {
        Group layout = new Group();
        Scene scene = new Scene(layout);
        window.setWidth(300);
        window.setHeight(650);
        btnPause = new Button("||");
        btnPause.setLayoutX(10);
        btnPause.setLayoutY(10);
        layout.getChildren().add(btnPause);
        btnPause.setOnAction(e-> pause(window));
        scene.setFill(Color.BLACK);
        window.setScene(scene);
        window.show();
    }

    public void pause(Stage GameStage)
    {
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        HBox layout = new HBox(25);
        layout.setPadding(new Insets(10,10,10,10));
        window.setTitle("PAUSE");
        Button resume = new Button("RESUME");
        Button exit = new Button("EXIT");
        Button save = new Button("SAVE");
        Label l = new Label();
        layout.getChildren().addAll(resume, save, exit);
        resume.setOnAction(e->window.close());
        exit.setOnAction(e->
        {
            MainMenu mainMenu = new MainMenu();
            try {
                mainMenu.newGame(GameStage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            window.close();
        });
        Scene scene = new Scene(layout, 220, 100);
        window.setScene(scene);
        window.showAndWait();
    }
}
