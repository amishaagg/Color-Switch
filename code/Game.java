import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

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
    Stage window;
    public Game(Stage primaryStage) throws FileNotFoundException
    {
        window = new Stage();
        Group layout = new Group();
        Scene scene = new Scene(layout);
        window.setWidth(300);
        window.setHeight(650);
        btnPause = new Button();
        Image image = new Image(new FileInputStream("assets/Pause_BTN.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(5);
        imageView.setY(5);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        btnPause.setGraphic(imageView);
        btnPause.setBackground(Background.EMPTY);
        btnPause.setLayoutX(10);
        btnPause.setLayoutY(10);
        layout.getChildren().add(btnPause);
        btnPause.setOnAction(e-> pause(window, primaryStage));
        scene.setFill(Color.rgb(40,40,40));
        window.setScene(scene);
        window.show();
    }

    public void pause(Stage GameStage, Stage primaryStage)
    {
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Group layout = new Group();
        window.setTitle("PAUSE");
        Button resume = new Button("RESUME");
        Button exit = new Button("EXIT");
        Button save = new Button("SAVE");
        Label l = new Label();
        layout.getChildren().addAll(resume, save, exit);
        resume.setOnAction(e->window.close());
        exit.setOnAction(e->
        {
            primaryStage.show();
            GameStage.close();
            window.close();
        });
        resume.setLayoutX(10);
        save.setLayoutX(90);
        exit.setLayoutX(170);
        Scene scene = new Scene(layout, 220, 100);
        scene.setFill(Color.rgb(40,40,40));
        window.setScene(scene);
        window.showAndWait();
    }
}
