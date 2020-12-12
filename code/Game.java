import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game implements Serializable
{
    private ArrayList<GameElement> gameElements = new ArrayList<>();
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private ArrayList<Star> stars = new ArrayList<>();
    private ArrayList<ColorSwitcher> colorSwitchers = new ArrayList<>();
    private Ball ball;
    private int score;
    transient AnimationTimer timer;
    boolean jumping;

    public ArrayList<GameElement> getGameElements() {
        return gameElements;
    }

    public void setGameElements(ArrayList<GameElement> gameElements) {
        this.gameElements = gameElements;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public ArrayList<Star> getStars() {
        return stars;
    }

    public void setStars(ArrayList<Star> stars) {
        this.stars = stars;
    }

    public ArrayList<ColorSwitcher> getColorSwitchers() {
        return colorSwitchers;
    }

    public void setColorSwitchers(ArrayList<ColorSwitcher> colorSwitchers) {
        this.colorSwitchers = colorSwitchers;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    transient Button btnPause;
    transient Stage window;
    transient Scene scene1;
    public Game(Stage primaryStage) throws FileNotFoundException
    {
        window = new Stage();
        Group layout = new Group();
        scene1 = new Scene(layout);
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
        setScore(0); //oops
        Text score_value = new Text(10.0, 30.0, getScore()+""); //oops
        Font font = Font.font("Verdana", FontWeight.BOLD,  35);
        score_value.setFont(font);
        score_value.setLayoutY(10);
        score_value.setFill(Color.WHITE);

        ImageView starimageView = new ImageView(new Image(new FileInputStream("assets/star.png")));
        starimageView.setId("star");
        getStars().add(new Star(starimageView)); //oops
        starimageView.setX(130.0f);
        starimageView.setY(275.0f);
        starimageView.setFitHeight(40);
        starimageView.setFitWidth(40);
        starimageView.setVisible(false);

        ImageView starimageView2 = new ImageView(new Image(new FileInputStream("assets/star.png")));
        starimageView2.setId("star");
        getStars().add(new Star(starimageView2)); //oops
        starimageView2.setX(130.0f);
        starimageView2.setY(275.0f);
        starimageView2.setFitHeight(40);
        starimageView2.setFitWidth(40);

        Rectangle rectangle=new Rectangle(75,220,130,20);
        rectangle.setFill(Color.rgb(250,225,0));
        rectangle.setId("yellow");
        Rectangle rectangle2=new Rectangle(205,220,20,130);
        rectangle2.setFill(Color.rgb(144,13,255));
        rectangle2.setId("purple");
        Rectangle rectangle3=new Rectangle(95,350,130,20);
        rectangle3.setFill(Color.rgb(255,1,129));
        rectangle3.setId("pink");
        Rectangle rectangle4=new Rectangle(75,240,20,130);
        rectangle4.setFill(Color.rgb(50,219,240));
        rectangle4.setId("cyan");
        Group obstaclegroup2=new Group(rectangle,rectangle2,rectangle3,rectangle4);
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

        Circle circle = new Circle(150, 550, 10, Color.rgb(250,225,0));
        ball = new Ball(circle, "yellow"); //oops

        ImageView colorswitcher_imageView = new ImageView(new Image(new FileInputStream("assets/ColorSwitcher.png")));
        getColorSwitchers().add(new ColorSwitcher(colorswitcher_imageView)); //oops
        colorswitcher_imageView.setX(125);
        colorswitcher_imageView.setY(110);

        Shape shape1=Shape.subtract(arc,innerCircle);
        shape1.setFill(Color.rgb(250,225,0));
        shape1.setId("yellow");
        Shape shape2=Shape.subtract(arc2,innerCircle);
        shape2.setFill(Color.rgb(144, 13, 255));
        shape2.setId("purple");
        Shape shape3=Shape.subtract(arc3,innerCircle);
        shape3.setFill(Color.rgb(255, 1, 129));
        shape3.setId("pink");
        Shape shape4=Shape.subtract(arc4,innerCircle);
        shape4.setFill(Color.rgb(50, 219, 240));
        shape4.setId("cyan");
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
        rotate2.setDuration(Duration.millis(10000));
        rotate2.setNode(obstaclegroup2);
        rotate2.play();

        ImageView fingerImageView = new ImageView(new Image(new FileInputStream("assets/finger.png")));
        fingerImageView.setLayoutY(565);
        fingerImageView.setLayoutX(135);

        Text highScore_broken_text = new Text(10.0, 30.0, "NEW HIGH-SCORE!");
        highScore_broken_text.setFont(Font.font("Verdana", FontWeight.BOLD,  20));
        highScore_broken_text.setFill(Color.CADETBLUE);
        highScore_broken_text.setVisible(false);
        highScore_broken_text.setLayoutX(30);
        highScore_broken_text.setLayoutY(570);

        Scanner sc = new Scanner(new File("highscore.txt"));
        int Highest_score = Integer.parseInt(sc.next());
        final double[] obstacle_position = {-400};
        final int[] i = {0};
        final int[] j = {0};
        final double[] speed = {2};
        final boolean[] highScorebroken = {false};
        timer = new AnimationTimer()
        {
            @Override
            public void handle(long l)
            {
                if(getScore()>Highest_score && !highScorebroken[0])
                {
                    highScorebroken[0] = true;
                    highScore_broken_text.setVisible(true);
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), highScore_broken_text);
                    fadeTransition.setFromValue(10);
                    fadeTransition.setToValue(0);
                    fadeTransition.play();
                }

                if(fingerImageView.getBoundsInParent().getMinY()>=600)
                    fingerImageView.setVisible(false);

                for(Node shapey:obstaclegroup.getChildren())
                {
                    if(!ball.getColor().equals(shapey.getId()) && !shapey.getId().equals("star"))
                    {
                        Shape intersect = Shape.intersect((Shape) shapey,ball.getCircle());
                        if(intersect.getBoundsInParent().getWidth()>0)
                        {
                            try {
                                timer.stop();
                                gameOver(primaryStage, window);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                for(Node shapey:obstaclegroup2.getChildren())
                {
                    if(!ball.getColor().equals(shapey.getId()) && !shapey.getId().equals("star"))
                    {
                        Shape intersect = Shape.intersect((Shape) shapey,ball.getCircle());
                        if(intersect.getBoundsInParent().getWidth()>0)
                        {
                            try {
                                timer.stop();
                                gameOver(primaryStage, window);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                if(ball.getCircle().getCenterY()<550 && fingerImageView.isVisible())
                {
                    ball.getCircle().setCenterY(ball.getCircle().getCenterY() + speed[0]);
                }
                if(!fingerImageView.isVisible())
                {
                    ball.getCircle().setCenterY(ball.getCircle().getCenterY() + speed[0]);
                    if(ball.getCircle().getBoundsInParent().getMinY()>=650)
                    {
                        try {
                            timer.stop();
                            gameOver(primaryStage, window);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                if(ball.getCircle().getCenterY()<300)
                {
                    for(Star s: getStars())
                    {
                        if(jumping)
                            s.getStarImageView().setLayoutY(s.getStarImageView().getLayoutY()+7);
                    }

                    if(jumping)
                        fingerImageView.setLayoutY(fingerImageView.getLayoutY()+7);
                    if(starimageView.getBoundsInParent().getMinY()>=650)
                    {
                        if(obstacle_position[0]<=-401)
                            starimageView.setVisible(true);
                        starimageView.setLayoutY(obstaclegroup.getLayoutY());
                    }

                    if(starimageView2.getBoundsInParent().getMinY()>=650)
                    {
                        starimageView2.setVisible(true);
                        starimageView2.setLayoutY(obstaclegroup2.getLayoutY());
                    }

                    for(Obstacle obstacle: getObstacles())
                    {
                        if(jumping)
                        {
                            obstacle.getObstacleGroup().setLayoutY(obstacle.getObstacleGroup().getLayoutY() + 7);
                        }
                        if(obstacle.getObstacleGroup().getBoundsInParent().getMinY()>=650) {
                            obstacle.getObstacleGroup().setLayoutY(obstacle_position[0]);
                            obstacle_position[0] -= 1;
                        }
                    }

                    for(ColorSwitcher c: getColorSwitchers())
                    {
                        if(jumping)
                            c.getColorSwitcher().setLayoutY(c.getColorSwitcher().getLayoutY()+7);
                        if(c.getColorSwitcher().getBoundsInParent().getMinY()>=650)
                        {
                            c.getColorSwitcher().setVisible(true);
                            if(i[0]%2==0)
                                c.getColorSwitcher().setLayoutY(obstaclegroup2.getLayoutY());
                            else
                                c.getColorSwitcher().setLayoutY(obstaclegroup2.getLayoutY()-50);
                            if(i[0]%3==0)
                                c.getColorSwitcher().setVisible(false);
                            i[0]++;
                        }
                    }
                }

                for(Star s: getStars())
                {
                    if(s.getStarImageView().isVisible() && ball.getCircle().intersects(s.getStarImageView().getBoundsInParent()))
                    {
                        s.getStarImageView().setVisible(false);
                        score++;
                        score_value.setText(getScore()+"");
                    }
                }

                if(colorswitcher_imageView.isVisible() &&
                        ball.getCircle().intersects(colorswitcher_imageView.getBoundsInParent()))
                {
                    colorswitcher_imageView.setVisible(false);
                    ColorSwitcher.changeColor(ball);
                }
            }
        };
        timer.start();
        if(ball.getCircle().getCenterY()<550)
            timer.stop();
        scene1.setOnKeyPressed(e->
        {
            if(e.getCode()== KeyCode.Q)
            {
                ball.jump();
                jumping = true;
            }
        });
        scene1.setOnKeyReleased(e->jumping=false);
        //layout.getChildren().addAll(big_star,btnPause,score_value);
        layout.getChildren().addAll(obstaclegroup,obstaclegroup2,circle,colorswitcher_imageView);
        layout.getChildren().addAll(score_value, starimageView, starimageView2,btnPause,fingerImageView);
        layout.getChildren().addAll(highScore_broken_text);
        obstaclegroup.setLayoutY(obstaclegroup.getLayoutY()-400);
        btnPause.setOnAction(e-> {
            try {
                timer.stop();
                pause(window, primaryStage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        scene1.setFill(Color.rgb(40,40,40));
        window.setScene(scene1);
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
            String filename="file.bin";
            try{FileOutputStream file = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(file);
                out.writeObject(this);
                out.close();
                file.close();
                System.out.println("Object has been serialized");
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        });
        resume.setOnAction(e->
        {
            timer.start();
            window.close();
        });
        exit.setOnAction(e->exitToMainMenu(primaryStage, GameStage, window));
        window.setOnCloseRequest(e-> timer.start() );
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

    public void gameOver(Stage primaryStage, Stage GameStage) throws IOException
    {
        Group group = new Group();
        Scene scene = new Scene(group);
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

        Scanner sc = new Scanner(new File("highscore.txt"));
        int Highest_score = Integer.parseInt(sc.next());
        if(getScore() > Highest_score)
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("highscore.txt"));
            writer.write(getScore()+"");
            writer.close();
            Highest_score = getScore();
        }

        Text high_score = new Text(10.0, 30.0, Highest_score+"");
        high_score.setFont(font);
        high_score.setFill(Color.WHITE);
        restart.setOnAction(e->
        {
            e.consume();
            //gameOverWindow.close();
            GameStage.close();
            try {
                new Game(primaryStage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        home.setOnAction(e->
        {
            //gameOverWindow.close();
            GameStage.close();
            primaryStage.show();
        });
        continue_using_stars.setOnAction(e->
        {
            timer.start();
            //gameOverWindow.close();
            ball.getCircle().setCenterY(ball.getCircle().getCenterY()+50);
            GameStage.setScene(scene1);
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
        GameStage.setScene(scene);
        scene.setFill(Color.rgb(40,40,40));
//        gameOverWindow.setHeight(650);
//        gameOverWindow.setWidth(300);
//        gameOverWindow.show();
    }

    public void exitToMainMenu(Stage primaryStage, Stage GameStage, Stage window)
    {
        primaryStage.show();
        GameStage.close();
        window.close();
    }
}
