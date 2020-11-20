package JavaFX;
import com.sun.xml.internal.bind.v2.runtime.ClassBeanInfoImpl;
import javafx.animation.RotateTransition;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
        import javafx.stage.Stage;
import javafx.util.Duration;

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
        Image pauseimage = new Image(new FileInputStream("assets/Pause_BTN.png"));
        ImageView pauseimageView = new ImageView(pauseimage);
        pauseimageView.setX(5);
        pauseimageView.setY(5);
        pauseimageView.setFitHeight(40);
        pauseimageView.setFitWidth(40);
        btnPause.setGraphic(pauseimageView);
        btnPause.setBackground(Background.EMPTY);
        btnPause.setLayoutX(230);
        btnPause.setLayoutY(0);

        String str = "17";
        Text score = new Text(10.0, 30.0, str);
        //Setting the font bold and italic
        Font font = Font.font("Verdana", FontWeight.BOLD,  35);
        score.setFont(font);
        //Setting the color of the text
        score.setFill(Color.WHITE);


        Arc arc = new Arc(150.0,300.0,100.0,100.0,0.0,90.0);
//        arc.setCenterX(150.0f);
//        arc.setCenterY(150.0f);
//        arc.setRadiusX(125.0f);
//        arc.setRadiusY(125.0f);
//        arc.setStartAngle(0.0f);
//        arc.setLength(90.0f);
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.rgb(250,225,0));

        Arc arc2 = new Arc();
        arc2.setCenterX(150.0f);
        arc2.setCenterY(300.0f);
        arc2.setRadiusX(100.0f);
        arc2.setRadiusY(100.0f);
        arc2.setStartAngle(90.0f);
        arc2.setLength(90.0f);
        arc2.setType(ArcType.ROUND);
        arc2.setFill(Color.rgb(144, 13, 255));

        Arc arc3 = new Arc();
        arc3.setCenterX(150.0f);
        arc3.setCenterY(300.0f);
        arc3.setRadiusX(100.0f);
        arc3.setRadiusY(100.0f);
        arc3.setStartAngle(270.0f);
        arc3.setLength(90.0f);
        arc3.setType(ArcType.ROUND);
        arc3.setFill(Color.rgb(255, 1, 129));

        Arc arc4 = new Arc();
        arc4.setCenterX(150.0f);
        arc4.setCenterY(300.0f);
        arc4.setRadiusX(100.0f);
        arc4.setRadiusY(100.0f);
        arc4.setStartAngle(180.0f);
        arc4.setLength(90.0f);
        arc4.setType(ArcType.ROUND);
        arc4.setFill(Color.rgb(50, 219, 240));

        Arc innerCircle = new Arc();
        innerCircle.setCenterX(150.0f);
        innerCircle.setCenterY(300.0f);
        innerCircle.setRadiusX(80.0f);
        innerCircle.setRadiusY(80.0f);
        //arc.setStartAngle(90.0f);
        innerCircle.setLength(360.0f);
        innerCircle.setType(ArcType.ROUND);
        innerCircle.setFill(Color.rgb(39,39,39));
        //innerCircle.setFill(new ImagePattern(new Image(new FileInputStream("assets/star.png"))));


        Image star = new Image(new FileInputStream("assets/star.png"));
        ImageView starimageView = new ImageView(star);
        starimageView.setX(130.0f);
        starimageView.setY(275.0f);
        starimageView.setFitHeight(40);
        starimageView.setFitWidth(40);

        Circle ball = new Circle(150, 550, 20, Color.rgb(250,225,0));

        Image colorSwitcher = new Image(new FileInputStream("assets/Color Switcher.png"));
        ImageView colorswitcher_imageView = new ImageView(colorSwitcher);
        colorswitcher_imageView.setX(120);
        colorswitcher_imageView.setY(110);
        colorswitcher_imageView.setFitHeight(45);
        colorswitcher_imageView.setFitWidth(55);

        layout.getChildren().addAll(btnPause,arc,arc2,arc3,arc4,innerCircle,starimageView,score,ball,colorswitcher_imageView);
        btnPause.setOnAction(e-> pause(window, primaryStage));
        scene.setFill(Color.rgb(39,39,39));
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
