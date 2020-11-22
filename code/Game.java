import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Game
{
    private static ArrayList<GameElement> gameElements = new ArrayList<>();
    private static ArrayList<Obstacle> obstacles = new ArrayList<>();
    private static ArrayList<Star> stars = new ArrayList<>();
    private static ArrayList<ColorSwitcher> colorSwitchers = new ArrayList<>();
    private static Ball ball;
    private static int score;

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

    public static Ball getBall() {
        return ball;
    }

    public static void setBall(Ball ball) {
        Game.ball = ball;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Game.score = score;
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
        Image pauseimage = new Image(new FileInputStream("assets/pause.png"));
        ImageView pauseimageView = new ImageView(pauseimage);
        pauseimageView.setX(5);
        pauseimageView.setY(5);
        pauseimageView.setFitHeight(40);
        pauseimageView.setFitWidth(40);
        btnPause.setGraphic(pauseimageView);
        btnPause.setBackground(Background.EMPTY);
        btnPause.setLayoutX(220);
        btnPause.setLayoutY(10);
        if(getScore()==0)
            setScore(17); //oops
        Text score_value = new Text(10.0, 30.0, getScore()+""); //oops
        Font font = Font.font("Verdana", FontWeight.BOLD,  35);
        score_value.setFont(font);
        score_value.setLayoutY(10);
        score_value.setFill(Color.WHITE);

        ImageView starimageView = new ImageView(new Image(new FileInputStream("assets/star.png")));
        getStars().add(new Star(starimageView)); //oops
        starimageView.setX(130.0f);
        starimageView.setY(275.0f);
        starimageView.setFitHeight(40);
        starimageView.setFitWidth(40);

        ImageView big_star = new ImageView(new Image(new FileInputStream("assets/big_star.png")));
        getStars().add(new Star(big_star)); //oops
        big_star.setLayoutX(120);
        big_star.setLayoutY(50);

        Rectangle rectangle=new Rectangle(75,220,150,20);
        rectangle.setFill(Color.rgb(250,225,0));
        Rectangle rectangle2=new Rectangle(205,220,20,150);
        rectangle2.setFill(Color.rgb(144,13,255));
        Rectangle rectangle3=new Rectangle(75,350,150,20);
        rectangle3.setFill(Color.rgb(255,1,129));
        Rectangle rectangle4=new Rectangle(75,220,20,150);
        rectangle4.setFill(Color.rgb(50,219,240));
        Group obstaclegroup2=new Group(rectangle,rectangle2,rectangle3,rectangle4,starimageView);
        getObstacles().add(new Obstacle(obstaclegroup2)); //oops

        Arc arc = new Arc(150.0,300.0,100.0,100.0,0.0,90.0);
        arc.setType(ArcType.ROUND);

        Arc arc2 = new Arc();
        arc2.setCenterX(150.0f);
        arc2.setCenterY(300.0f);
        arc2.setRadiusX(100.0f);
        arc2.setRadiusY(100.0f);
        arc2.setStartAngle(90.0f);
        arc2.setLength(90.0f);
        arc2.setType(ArcType.ROUND);

        Arc arc3 = new Arc();
        arc3.setCenterX(150.0f);
        arc3.setCenterY(300.0f);
        arc3.setRadiusX(100.0f);
        arc3.setRadiusY(100.0f);
        arc3.setStartAngle(270.0f);
        arc3.setLength(90.0f);
        arc3.setType(ArcType.ROUND);

        Arc arc4 = new Arc();
        arc4.setCenterX(150.0f);
        arc4.setCenterY(300.0f);
        arc4.setRadiusX(100.0f);
        arc4.setRadiusY(100.0f);
        arc4.setStartAngle(180.0f);
        arc4.setLength(90.0f);
        arc4.setType(ArcType.ROUND);

        Arc innerCircle = new Arc(150.0f,300.0f,80.0f,80.0f,0.0f,360.0f);
        innerCircle.setType(ArcType.ROUND);

        Circle circle = new Circle(150, 550, 17, Color.rgb(250,225,0));
        ball = new Ball(circle, "Yellow"); //oops

        ImageView colorswitcher_imageView = new ImageView(new Image(new FileInputStream("assets/ColorSwitcher.png")));
        getColorSwitchers().add(new ColorSwitcher(colorswitcher_imageView)); //oops
        colorswitcher_imageView.setX(120);
        colorswitcher_imageView.setY(110);

        Shape shape1=Shape.subtract(arc,innerCircle);
        shape1.setFill(Color.rgb(250,225,0));
        Shape shape2=Shape.subtract(arc2,innerCircle);
        shape2.setFill(Color.rgb(144, 13, 255));
        Shape shape3=Shape.subtract(arc3,innerCircle);
        shape3.setFill(Color.rgb(255, 1, 129));
        Shape shape4=Shape.subtract(arc4,innerCircle);
        shape4.setFill(Color.rgb(50, 219, 240));
        Group obstaclegroup=new Group(shape1,shape2,shape3,shape4);
        getObstacles().add(new Obstacle(obstaclegroup)); //oops

        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.millis(5000));
        rotate.setNode(obstaclegroup);
        rotate.play();

        RotateTransition rotate2 = new RotateTransition();
        rotate2.setAxis(Rotate.Z_AXIS);
        rotate2.setByAngle(360);
        rotate2.setCycleCount(Animation.INDEFINITE);
        rotate2.setDuration(Duration.millis(5000));
        rotate2.setNode(obstaclegroup2);
        rotate2.play();

        scene.setOnKeyPressed(e->
        {
            if(e.getCode()== KeyCode.W)
                ball.jump();
            if(circle.intersects(innerCircle.getLayoutBounds()))
            {
                try {
                    gameOver(primaryStage, window);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        layout.getChildren().add(big_star);
        layout.getChildren().addAll(btnPause,starimageView,score_value,circle,colorswitcher_imageView,obstaclegroup,obstaclegroup2);
        btnPause.setOnAction(e-> {
            try {
                pause(window, primaryStage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        scene.setFill(Color.rgb(40,40,40));
        window.setScene(scene);
        window.show();
    }

    public void pause(Stage GameStage, Stage primaryStage) throws FileNotFoundException
    {
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Group layout = new Group();
        window.setTitle("PAUSE");
        Button resume = new Button();
        Image resumeImage = new Image(new FileInputStream("assets/game_resume.png"));
        ImageView resumeImageView = new ImageView(resumeImage);
        resume.setGraphic(resumeImageView);
        resume.setBackground(Background.EMPTY);
        Button exit = new Button();
        Image homeImage = new Image(new FileInputStream("assets/game_home.png"));
        ImageView homeImageView = new ImageView(homeImage);
        exit.setGraphic(homeImageView);
        exit.setBackground(Background.EMPTY);
        Button save = new Button();
        Image saveImage = new Image(new FileInputStream("assets/SAVE.png"));
        ImageView saveImageView = new ImageView(saveImage);
        save.setGraphic(saveImageView);
        save.setBackground(Background.EMPTY);
        Text saved_text = new Text(10.0, 30.0, "SAVED ✓");
        Font font = Font.font("Verdana", FontWeight.NORMAL,  20);
        saved_text.setFont(font);
        saved_text.setLayoutY(10);
        saved_text.setFill(Color.WHITE);
        saved_text.setLayoutY(300);
        saved_text.setLayoutX(60);
        saved_text.setVisible(false);
        layout.getChildren().addAll(resume, save, exit, saved_text);
        save.setOnAction(e->
        {
            saved_text.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), saved_text);
            fadeTransition.setFromValue(10);
            fadeTransition.setToValue(0);
            fadeTransition.play();
        });
        saved_text.setOnMouseReleased(e -> saved_text.setVisible(false));
        saved_text.setVisible(false);
        resume.setOnAction(e->window.close());
        exit.setOnAction(e->exitToMainMenu(primaryStage, GameStage, window));
        resume.setLayoutX(65);
        resume.setLayoutY(150);
        save.setLayoutX(65);
        save.setLayoutY(250);
        Scene scene = new Scene(layout, 220, 100);
        scene.setFill(Color.BLACK);
        window.setScene(scene);
        window.setHeight(500);
        window.showAndWait();
    }

    public void gameOver(Stage primaryStage, Stage GameStage) throws FileNotFoundException {
        Group group = new Group();
        Scene scene = new Scene(group);
        Stage window = new Stage();
        GameStage.hide();
        Button restart = new Button();
        ImageView restartImageView = new ImageView(new Image(new FileInputStream("assets/restart.png")));
        restart.setGraphic(restartImageView);
        restart.setBackground(Background.EMPTY);
        Button home = new Button();
        ImageView homeImageView = new ImageView(new Image(new FileInputStream("assets/game_over_home.png")));
        home.setGraphic(homeImageView);
        home.setBackground(Background.EMPTY);
        Button continue_using_stars = new Button();
        ImageView continueImageView = new ImageView(new Image(new FileInputStream("assets/continue.png")));
        continue_using_stars.setGraphic(continueImageView);
        continue_using_stars.setBackground(Background.EMPTY);
        ImageView scoreImageView = new ImageView(new Image(new FileInputStream("assets/SCORE.png")));
        ImageView HighScoreImageView = new ImageView(new Image(new FileInputStream("assets/HIGHSCORE.png")));
        Text current_score = new Text(10.0, 30.0, getScore()+"");
        Font font = Font.font("Verdana", FontWeight.NORMAL,  30);
        current_score.setFont(font);
        current_score.setFill(Color.WHITE);
        Text high_score = new Text(10.0, 30.0, 19+"");
        high_score.setFont(font);
        high_score.setFill(Color.WHITE);
        restart.setOnAction(e->
        {
            window.close();
            try {
                new Game(primaryStage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        home.setOnAction(e->
        {
            window.close();
            GameStage.close();
            primaryStage.show();
        });
        continue_using_stars.setOnAction(e->
        {
            window.close();
            ball.getCircle().setCenterY(ball.getCircle().getCenterY()+30);
            GameStage.show();
        });
        group.getChildren().addAll(restart, home, continue_using_stars, scoreImageView, HighScoreImageView);
        group.getChildren().addAll(current_score, high_score);
        restart.setLayoutX(105);
        restart.setLayoutY(330);
        continue_using_stars.setLayoutX(65);
        continue_using_stars.setLayoutY(415);
        scoreImageView.setLayoutY(120);
        current_score.setLayoutY(170);
        HighScoreImageView.setLayoutY(220);
        high_score.setLayoutY(270);
        window.setScene(scene);
        scene.setFill(Color.rgb(40,40,40));
        window.setHeight(650);
        window.setWidth(300);
        window.show();
    }

    public void exitToMainMenu(Stage primaryStage, Stage GameStage, Stage window)
    {
        primaryStage.show();
        GameStage.close();
        window.close();
    }
}
