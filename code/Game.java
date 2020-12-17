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
import java.util.HashMap;
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
    private Finger myfinger;
    transient Button btnPause;
    transient Stage window;
    transient Scene scene1;

    transient double obstacle_position = -400;
    transient int i = 0;
    transient double speed = 2;
    transient double obstacle_speed = 5000;

    public double getObstacle_position() {
        return obstacle_position;
    }

    public int getI() {
        return i;
    }

    public double getSpeed() {
        return speed;
    }

    public double getObstacle_speed() {
        return obstacle_speed;
    }

    public Game() throws IOException { }

    public Finger getMyfinger() { return myfinger; }

    public void setMyfinger(Finger myfinger) { this.myfinger = myfinger; }

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

    public void pause(Stage GameStage, Stage primaryStage,RotateTransition rotate,RotateTransition rotate2) throws FileNotFoundException
    {
        Stage window=new Stage();
        rotate.pause();
        rotate2.pause();
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

            int current_count = 0;
            try {
                Scanner sc = new Scanner(new File("count.txt"));
                current_count = Integer.parseInt(sc.next());
                BufferedWriter writer = new BufferedWriter(new FileWriter("count.txt"));
                writer.write(current_count+1+"");
                current_count++;
                writer.close();
            }
            catch (FileNotFoundException fileNotFoundException)
            {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("count.txt"));
                    writer.write(0+"");
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            switch (current_count%5)
            {
                case 0: serialise("file.bin");
                    break;
                case 1: serialise("file1.bin");
                    break;
                case 2: serialise("file2.bin");
                    break;
                case 3: serialise("file3.bin");
                    break;
                case 4: serialise("file4.bin");
                    break;
            }
        });
        resume.setOnAction(e->
        {
            rotate.play();
            rotate2.play();
            timer.start();
            window.close();
        });
        exit.setOnAction(e->exitToMainMenu(primaryStage, GameStage, window));
        window.setOnCloseRequest(e->
        {
            rotate.play();
            rotate2.play();
            timer.start();
        });
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

        ImageView not_enough_stars = new ImageView(new Image(new FileInputStream("assets/no_stars.png")));
        not_enough_stars.setVisible(false);
        not_enough_stars.setLayoutY(480);
        not_enough_stars.setLayoutX(45);


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
                new Game();
                startGame(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
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
            if(getScore()>=5)
            {
                timer.start();
                setScore(getScore()-5);
                ball.setJumping(false);
                if(ball.getCircle().getCenterY()>=650)
                    ball.getCircle().setCenterY(550);
                else
                    ball.getCircle().setCenterY(ball.getCircle().getCenterY() + 50);
                GameStage.setScene(scene1);
            }
            else
            {
                not_enough_stars.setVisible(true);
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), not_enough_stars);
                fadeTransition.setFromValue(10);
                fadeTransition.setToValue(0);
                fadeTransition.play();
            }

        });
        group.getChildren().addAll(restart, home, continue_using_stars, scoreImageView, HighScoreImageView);
        group.getChildren().addAll(current_score, high_score,not_enough_stars);
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

    public void exitToMainMenu(Stage primaryStage, Stage GameStage, Stage window) {
        primaryStage.show();
        GameStage.close();
        window.close();
    }

    public void startGame(Stage primaryStage) throws IOException{
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
        starimageView.setX(130.0f);
        starimageView.setY(275.0f);
        starimageView.setFitHeight(40);
        starimageView.setFitWidth(40);
        starimageView.setVisible(false);
        getStars().add(new Star(starimageView)); //oops

        ImageView starimageView2 = new ImageView(new Image(new FileInputStream("assets/star.png")));
        starimageView2.setId("star");
        starimageView2.setX(130.0f);
        starimageView2.setY(275.0f);
        starimageView2.setFitHeight(40);
        starimageView2.setFitWidth(40);
        getStars().add(new Star(starimageView2)); //oops

        Arc arc = new Arc(150.0,300.0,100.0,100.0,0.0,90.0);
        arc.setType(ArcType.ROUND);
        Arc arc2 = new Arc(150.0,300.0,100.0,100.0,90.0,90.0);
        arc2.setType(ArcType.ROUND);
        Arc arc3 = new Arc(150.0,300.0,100.0,100.0,270.0,90.0);
//        arc3.setCenterX(150.0f);
//        arc3.setCenterY(300.0f);
//        arc3.setRadiusX(100.0f);
//        arc3.setRadiusY(100.0f);
//        arc3.setStartAngle(270.0f);
//        arc3.setLength(90.0f);
        arc3.setType(ArcType.ROUND);
        Arc arc4 = new Arc(150.0,300.0,100.0,100.0,180.0,90.0);
        arc4.setType(ArcType.ROUND);
        Arc innerCircle = new Arc(150.0f,300.0f,80.0f,80.0f,0.0f,360.0f);
        innerCircle.setType(ArcType.ROUND);
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
        Obstacle obstacle=new Obstacle(obstaclegroup);
        obstacle.setType("Circles");
        ArrayList<ArrayList<Double>> obstaclecircle=new ArrayList<>();
        getObstacles().add(obstacle);//oops

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
        Obstacle obstacle2=new Obstacle(obstaclegroup2);
        obstacle2.setType("Rectangles");
        getObstacles().add(obstacle2); //oops

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

        Circle circle = new Circle(150, 550, 10, Color.rgb(250,225,0));
        ball = new Ball(circle, "yellow"); //oops

        ImageView colorswitcher_imageView = new ImageView(new Image(new FileInputStream("assets/ColorSwitcher.png")));
        colorswitcher_imageView.setX(125);
        colorswitcher_imageView.setY(110);
        getColorSwitchers().add(new ColorSwitcher(colorswitcher_imageView)); //oops

        ImageView fingerImageView = new ImageView(new Image(new FileInputStream("assets/finger.png")));
        fingerImageView.setLayoutY(565);
        fingerImageView.setLayoutX(135);
        System.out.println("Finger X= "+fingerImageView.getLayoutX());
        System.out.println("Finger Y= "+fingerImageView.getLayoutY());
        myfinger=new Finger(fingerImageView);

        Text highScore_broken_text = new Text(10.0, 30.0, "NEW HIGH-SCORE!");
        highScore_broken_text.setFont(Font.font("Verdana", FontWeight.BOLD,  20));
        highScore_broken_text.setFill(Color.CADETBLUE);
        highScore_broken_text.setVisible(false);
        highScore_broken_text.setLayoutX(30);
        highScore_broken_text.setLayoutY(570);
        int Highest_score = 0;
        try {
            Scanner sc = new Scanner(new File("highscore.txt"));
            Highest_score = Integer.parseInt(sc.next());
        }
        catch (FileNotFoundException e)
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("highscore.txt"));
            writer.write(0+"");
            writer.close();
        }

        final boolean[] highScorebroken = {false};
        int finalHighest_score = Highest_score;
        timer = new AnimationTimer()
        {
            @Override
            public void handle(long l)
            {
                score_value.setText(getScore()+"");
                if(getScore() > finalHighest_score && !highScorebroken[0])
                {
                    highScorebroken[0] = true;
                    highScore_broken_text.setVisible(true);
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), highScore_broken_text);
                    fadeTransition.setFromValue(10);
                    fadeTransition.setToValue(0);
                    fadeTransition.play();
                }

                if(myfinger.getFingerImageView().getBoundsInParent().getMinY()>=600){
                    myfinger.getFingerImageView().setVisible(false);

                }

                for(Node shapey:obstaclegroup.getChildren())
                {
                    if(!ball.getColor().equals(shapey.getId()) && !shapey.getId().equals("star"))
                    {
                        Shape intersect = Shape.intersect((Shape) shapey,ball.getCircle());
                        if(intersect.getBoundsInParent().getWidth()>0)
                        {
                            try {
                                timer.stop();
//                                rotate.pause();
//                                rotate2.pause();
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

                if(ball.getCircle().getCenterY()<550 && myfinger.getFingerImageView().isVisible())
                {

                    ball.getCircle().setCenterY(ball.getCircle().getCenterY() + speed);
                }
                if(!myfinger.getFingerImageView().isVisible())
                {

                    ball.getCircle().setCenterY(ball.getCircle().getCenterY() + speed);
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
                    if(ball.isJumping())
                        starimageView.setLayoutY(starimageView.getLayoutY()+7);

                    if(ball.isJumping())
                        starimageView2.setLayoutY(starimageView2.getLayoutY()+7);

                    if(ball.isJumping())
                        myfinger.getFingerImageView().setLayoutY(myfinger.getFingerImageView().getLayoutY()+7);
                    if(starimageView.getBoundsInParent().getMinY()>=650)
                    {
                        if(obstacle_position<=-401)
                            starimageView.setVisible(true);
                        starimageView.setLayoutY(obstaclegroup.getLayoutY());
                    }

                    if(starimageView2.getBoundsInParent().getMinY()>=650)
                    {
                        starimageView2.setVisible(true);
                        starimageView2.setLayoutY(obstaclegroup2.getLayoutY());
                    }

                    if(ball.isJumping())
                    {
                        obstaclegroup.setLayoutY(obstaclegroup.getLayoutY() + 7);
                    }
                    if(obstaclegroup.getBoundsInParent().getMinY()>=650) {
                        obstaclegroup.setLayoutY(obstacle_position);
                        obstacle_position -= 1;
//                        if(rotate.getDuration().toMillis()>3000)
//                            rotate.setDuration(Duration.millis(rotate.getDuration().toMillis()-100));
                        RotateTransition rotate = new RotateTransition();
                        rotate.setAxis(Rotate.Z_AXIS);
                        rotate.setByAngle(360);
                        rotate.setCycleCount(Animation.INDEFINITE);
                        rotate.setDuration(Duration.millis(obstacle_speed-100));
                        obstacle_speed -= 100;
                        rotate.setNode(obstaclegroup);
                        rotate.play();
                    }
                    if(ball.isJumping())
                    {
                        obstaclegroup2.setLayoutY(obstaclegroup2.getLayoutY() + 7);
                    }
                    if(obstaclegroup2.getBoundsInParent().getMinY()>=650) {
                        obstaclegroup2.setLayoutY(obstacle_position);
                        obstacle_position -= 1;
//                        if(rotate2.getDuration().toMillis()>3000)
//                            rotate2.setDuration(Duration.millis(rotate2.getDuration().toMillis()-100));
                        RotateTransition rotate2 = new RotateTransition();
                        rotate2.setAxis(Rotate.Z_AXIS);
                        rotate2.setByAngle(360);
                        rotate2.setCycleCount(Animation.INDEFINITE);
                        rotate2.setDuration(Duration.millis(obstacle_speed-100));
                        obstacle_speed -= 100;
                        rotate2.setNode(obstaclegroup2);
                        rotate2.play();
                    }

                    if(ball.isJumping())
                        colorswitcher_imageView.setLayoutY(colorswitcher_imageView.getLayoutY()+7);
                    if(colorswitcher_imageView.getBoundsInParent().getMinY()>=650)
                    {
                        colorswitcher_imageView.setVisible(true);
                        if(i%2==0)
                            colorswitcher_imageView.setLayoutY(obstaclegroup2.getLayoutY());
                        else
                            colorswitcher_imageView.setLayoutY(obstaclegroup2.getLayoutY()-50);
                        if(i%3==0)
                            colorswitcher_imageView.setVisible(false);
                        i++;
                    }
                }

                if(starimageView.isVisible() && ball.getCircle().intersects(starimageView.getBoundsInParent()))
                {
                    starimageView.setVisible(false);
                    score++;
                    score_value.setText(getScore()+"");
                }
                if(starimageView2.isVisible() && ball.getCircle().intersects(starimageView2.getBoundsInParent()))
                {
                    starimageView2.setVisible(false);
                    score++;
                    score_value.setText(getScore()+"");
                }

                if(colorswitcher_imageView.isVisible() && ball.getCircle().intersects(colorswitcher_imageView.getBoundsInParent())){
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
                ball.setJumping(true);
            }
        });
        scene1.setOnKeyReleased(e->ball.setJumping(false));
        layout.getChildren().addAll(obstaclegroup,obstaclegroup2,circle,colorswitcher_imageView);
        layout.getChildren().addAll(score_value, starimageView, starimageView2,btnPause,fingerImageView);
        layout.getChildren().addAll(highScore_broken_text);
        obstaclegroup.setLayoutY(obstaclegroup.getLayoutY()-400);
        btnPause.setOnAction(e-> {
            try {
                myfinger.setVisible(fingerImageView.isVisible());
                myfinger.setX(fingerImageView.getLayoutX());
                myfinger.setY(fingerImageView.getLayoutY());
                ArrayList<Double> positions=new ArrayList<>();
                positions.add(obstaclegroup.getLayoutX());
                positions.add(obstaclegroup.getLayoutY());
                positions.add(obstaclegroup.getRotate());
                System.out.println("OBs group X= "+obstaclegroup.getLayoutX());
                System.out.println("OBs group Y= "+obstaclegroup.getLayoutY());
                getObstacles().get(0).setCoordinates(positions);
                ArrayList<Double> positions2=new ArrayList<>();
                positions2.add(obstaclegroup2.getLayoutX());
                positions2.add(obstaclegroup2.getLayoutY());
                positions2.add(obstaclegroup2.getRotate());
                System.out.println("OBs group2 X= "+obstaclegroup2.getLayoutX());
                System.out.println("OBs group2 Y= "+obstaclegroup2.getLayoutY());
                getObstacles().get(1).setCoordinates(positions2);
                ImageView img=colorswitcher_imageView;
                getColorSwitchers().get(0).setX(img.getX());
                getColorSwitchers().get(0).setY(img.getY());
                getColorSwitchers().get(0).setVisible(img.isVisible());

                img=starimageView;
                getStars().get(0).setX(img.getX());
                getStars().get(0).setY(img.getY());
                getStars().get(0).setVisible(img.isVisible());

                ImageView img2=starimageView2;
                getStars().get(1).setX(img2.getX());
                getStars().get(1).setY(img2.getY());
                getStars().get(1).setVisible(img2.isVisible());

                Circle circ=ball.getCircle();
                ball.setX(circ.getCenterX());
                ball.setY(circ.getCenterY());
                ball.setRadius(circ.getRadius());
                timer.stop();
                pause(window, primaryStage,rotate,rotate2);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        scene1.setFill(Color.rgb(40,40,40));
        window.setScene(scene1);
        window.show();
    }

    public void savedGame(Stage primaryStage,int score2,ArrayList<Star> stars2,
                          ArrayList<Obstacle> obstacles2, Ball ball2,
                          ArrayList<ColorSwitcher> colorswitchers2,Finger finger2) throws IOException
    {
//        this.obstacle_position = obstacle_position2;
//        this.i = i2;
//        this.speed = speed2;
//        this.obstacle_speed = obstacle_speed2;
        myfinger=finger2;
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

        setScore(score2); //oops
        Text score_value = new Text(10.0, 30.0, getScore() + ""); //oops
        Font font = Font.font("Verdana", FontWeight.BOLD, 35);
        score_value.setFont(font);
        score_value.setLayoutY(10);
        score_value.setFill(Color.WHITE);

        this.stars=stars2;
        ImageView starimageView = new ImageView(new Image(new FileInputStream("assets/star.png")));
        starimageView.setId("star");
        starimageView.setVisible(this.stars.get(0).isVisible());
        starimageView.setX(this.stars.get(0).getX());
        starimageView.setY(this.stars.get(0).getY());
        starimageView.setFitHeight(40);
        starimageView.setFitWidth(40);
        this.stars.get(0).setStarImageView(starimageView);

        ImageView starimageView2 = new ImageView(new Image(new FileInputStream("assets/star.png")));
        starimageView2.setId("star");
        starimageView2.setVisible(this.stars.get(1).isVisible());
        starimageView2.setX(stars.get(1).getX());
        starimageView2.setY(stars.get(1).getY());
        starimageView2.setFitHeight(40);
        starimageView2.setFitWidth(40);
        this.stars.get(1).setStarImageView(starimageView2);

        this.obstacles = obstacles2;

        Arc arc = new Arc(150.0, 300.0, 100.0, 100.0, 0.0, 90.0);
        arc.setType(ArcType.ROUND);
        Arc arc2 = new Arc(150.0, 300.0, 100.0, 100.0, 90.0, 90.0);
        arc2.setType(ArcType.ROUND);
        Arc arc3 = new Arc(150.0, 300.0, 100.0, 100.0, 270.0, 90.0);
        arc3.setType(ArcType.ROUND);
        Arc arc4 = new Arc(150.0, 300.0, 100.0, 100.0, 180.0, 90.0);
        arc4.setType(ArcType.ROUND);
        Arc innerCircle = new Arc(150.0f, 300.0f, 80.0f, 80.0f, 0.0f, 360.0f);
        innerCircle.setType(ArcType.ROUND);
        Shape shape1 = Shape.subtract(arc, innerCircle);
        shape1.setFill(Color.rgb(250, 225, 0));
        shape1.setId("yellow");
        Shape shape2 = Shape.subtract(arc2, innerCircle);
        shape2.setFill(Color.rgb(144, 13, 255));
        shape2.setId("purple");
        Shape shape3 = Shape.subtract(arc3, innerCircle);
        shape3.setFill(Color.rgb(255, 1, 129));
        shape3.setId("pink");
        Shape shape4 = Shape.subtract(arc4, innerCircle);
        shape4.setFill(Color.rgb(50, 219, 240));
        shape4.setId("cyan");
        Group obstaclegroup = new Group(shape1, shape2, shape3, shape4);
        double X=this.obstacles.get(0).getCoordinates().get(0);
        double Y=this.obstacles.get(0).getCoordinates().get(1);
        double Rotation=this.obstacles.get(0).getCoordinates().get(2);
        obstaclegroup.setLayoutX(X);
        obstaclegroup.setLayoutY(Y);
        obstaclegroup.setRotate(Rotation);
        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.millis(5000));
        rotate.setNode(obstaclegroup);
        rotate.play();
        this.obstacles.get(0).setObstacleGroup(obstaclegroup);

        Rectangle rectangle = new Rectangle(75, 220, 130, 20);
        rectangle.setFill(Color.rgb(250, 225, 0));
        rectangle.setId("yellow");
        Rectangle rectangle2 = new Rectangle(205, 220, 20, 130);
        rectangle2.setFill(Color.rgb(144, 13, 255));
        rectangle2.setId("purple");
        Rectangle rectangle3 = new Rectangle(95, 350, 130, 20);
        rectangle3.setFill(Color.rgb(255, 1, 129));
        rectangle3.setId("pink");
        Rectangle rectangle4 = new Rectangle(75, 240, 20, 130);
        rectangle4.setFill(Color.rgb(50, 219, 240));
        rectangle4.setId("cyan");
        Group obstaclegroup2 = new Group(rectangle, rectangle2, rectangle3, rectangle4);
        System.out.println(this.obstacles.get(1).getType());
        X=this.obstacles.get(1).getCoordinates().get(0);
        Y=this.obstacles.get(1).getCoordinates().get(1);
        Rotation=this.obstacles.get(1).getCoordinates().get(2);
        obstaclegroup2.setLayoutX(X);
        obstaclegroup2.setLayoutY(Y);
        obstaclegroup2.setRotate(Rotation);
        RotateTransition rotate2 = new RotateTransition();
        rotate2.setAxis(Rotate.Z_AXIS);
        rotate2.setByAngle(360);
        rotate2.setCycleCount(Animation.INDEFINITE);
        rotate2.setDuration(Duration.millis(5000));
        rotate2.setNode(obstaclegroup2);
        rotate2.play();
        this.obstacles.get(1).setObstacleGroup(obstaclegroup2);

        this.ball = ball2;
        Circle circle = new Circle(ball.getX(), ball.getY(), ball.getRadius());
        String color = ball.getColor();
        if (color.equals("yellow")) {
            circle.setFill(Color.rgb(250, 225, 0));
        } else if (color.equals("purple")) {
            circle.setFill(Color.rgb(144, 13, 255));
        } else if (color.equals("pink")) {
            circle.setFill(Color.rgb(255, 1, 129));
        } else {
            circle.setFill(Color.rgb(50, 219, 240));
        }
        this.ball.setCircle(circle);
        ball.setJumping(false);
        System.out.println("ball is "+ ball.isJumping());

        this.colorSwitchers=colorswitchers2;
        ImageView colorswitcher_imageView = new ImageView(new Image(new FileInputStream("assets/ColorSwitcher.png")));
        colorswitcher_imageView.setX(colorSwitchers.get(0).getX());
        colorswitcher_imageView.setY(colorSwitchers.get(0).getY());
        colorswitcher_imageView.setVisible(colorSwitchers.get(0).isVisible());
        colorSwitchers.get(0).setColorSwitcher(colorswitcher_imageView);

        ImageView fingerImageView = new ImageView(new Image(new FileInputStream("assets/finger.png")));
        fingerImageView.setLayoutX(finger2.getX());
        fingerImageView.setLayoutY(finger2.getY());
        fingerImageView.setVisible(finger2.isVisible());
        myfinger.setFingerImageView(fingerImageView);

        Text highScore_broken_text = new Text(10.0, 30.0, "NEW HIGH-SCORE!");
        highScore_broken_text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        highScore_broken_text.setFill(Color.CADETBLUE);
        highScore_broken_text.setVisible(false);
        highScore_broken_text.setLayoutX(30);
        highScore_broken_text.setLayoutY(570);
        int Highest_score = 0;
        try {
            Scanner sc = new Scanner(new File("highscore.txt"));
            Highest_score = Integer.parseInt(sc.next());
        }
        catch (FileNotFoundException e)
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("highscore.txt"));
            writer.write(0+"");
            writer.close();
        }

        final boolean[] highScorebroken = {false};
        int finalHighest_score = Highest_score;
        timer = new AnimationTimer()
        {
            @Override
            public void handle(long l)
            {
                score_value.setText(getScore()+"");
                if(getScore() > finalHighest_score && !highScorebroken[0])
                {
                    highScorebroken[0] = true;
                    highScore_broken_text.setVisible(true);
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), highScore_broken_text);
                    fadeTransition.setFromValue(10);
                    fadeTransition.setToValue(0);
                    fadeTransition.play();
                }

                if(myfinger.getFingerImageView().getBoundsInParent().getMinY()>=600){
                    myfinger.getFingerImageView().setVisible(false);

                }


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

                if(ball.getCircle().getCenterY()<550 && myfinger.getFingerImageView().isVisible())
                {

                    ball.getCircle().setCenterY(ball.getCircle().getCenterY() + speed);
                }
                if(!myfinger.getFingerImageView().isVisible())
                {
                    ball.getCircle().setCenterY(ball.getCircle().getCenterY() + speed);
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
                    if(ball.isJumping())
                        starimageView.setLayoutY(starimageView.getLayoutY()+7);

                    if(ball.isJumping())
                        starimageView2.setLayoutY(starimageView2.getLayoutY()+7);


                    if(ball.isJumping())
                        myfinger.getFingerImageView().setLayoutY(myfinger.getFingerImageView().getLayoutY()+7);
                    if(starimageView.getBoundsInParent().getMinY()>=650)
                    {
                        if(obstacle_position<=-401)
                            starimageView.setVisible(true);
                        starimageView.setLayoutY(obstaclegroup.getLayoutY());
                    }

                    if(starimageView2.getBoundsInParent().getMinY()>=650)
                    {
                        starimageView2.setVisible(true);
                        starimageView2.setLayoutY(obstaclegroup2.getLayoutY());
                    }


                    if(ball.isJumping())
                    {
                        obstaclegroup.setLayoutY(obstaclegroup.getLayoutY() + 7);
                    }
                    if(obstaclegroup.getBoundsInParent().getMinY()>=650) {
                        obstaclegroup.setLayoutY(obstacle_position);
                        obstacle_position -= 1;
                        RotateTransition rotate = new RotateTransition();
                        rotate.setAxis(Rotate.Z_AXIS);
                        rotate.setByAngle(360);
                        rotate.setCycleCount(Animation.INDEFINITE);
                        rotate.setDuration(Duration.millis(obstacle_speed-100));
                        obstacle_speed -= 100;
                        rotate.setNode(obstaclegroup);
                        rotate.play();
                    }
                    if(ball.isJumping())
                    {
                        obstaclegroup2.setLayoutY(obstaclegroup2.getLayoutY() + 7);
                    }
                    if(obstaclegroup2.getBoundsInParent().getMinY()>=650) {
                        obstaclegroup2.setLayoutY(obstacle_position);
                        obstacle_position -= 1;
                        RotateTransition rotate2 = new RotateTransition();
                        rotate2.setAxis(Rotate.Z_AXIS);
                        rotate2.setByAngle(360);
                        rotate2.setCycleCount(Animation.INDEFINITE);
                        rotate2.setDuration(Duration.millis(obstacle_speed-100));
                        obstacle_speed -= 100;
                        rotate2.setNode(obstaclegroup2);
                        rotate2.play();
                    }

                    if(ball.isJumping())
                        colorswitcher_imageView.setLayoutY(colorswitcher_imageView.getLayoutY()+7);
                    if(colorswitcher_imageView.getBoundsInParent().getMinY()>=650)
                    {
                        colorswitcher_imageView.setVisible(true);
                        if(i%2==0)
                            colorswitcher_imageView.setLayoutY(obstaclegroup2.getLayoutY());
                        else
                            colorswitcher_imageView.setLayoutY(obstaclegroup2.getLayoutY()-50);
                        if(i%3==0)
                            colorswitcher_imageView.setVisible(false);
                        i++;
                    }
                }


                if(starimageView.isVisible() && ball.getCircle().intersects(starimageView.getBoundsInParent()))
                {
                    starimageView.setVisible(false);
                    score++;
                    score_value.setText(getScore()+"");
                }
                if(starimageView2.isVisible() && ball.getCircle().intersects(starimageView2.getBoundsInParent()))
                {
                    starimageView2.setVisible(false);
                    score++;
                    score_value.setText(getScore()+"");
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
        scene1.setOnKeyPressed(e->
        {
            if(e.getCode()== KeyCode.Q)
            {
                ball.jump();
                ball.setJumping(true);
            }
        });
        scene1.setOnKeyReleased(e->ball.setJumping(false));
        //layout.getChildren().addAll(big_star,btnPause,score_value);
        layout.getChildren().addAll(obstaclegroup,obstaclegroup2,circle,colorswitcher_imageView);
        layout.getChildren().addAll(score_value, starimageView, starimageView2,btnPause,fingerImageView);
        layout.getChildren().addAll(highScore_broken_text);
        //obstaclegroup.setLayoutY(obstaclegroup.getLayoutY()-400);
        btnPause.setOnAction(e-> {
            try {
                myfinger.setVisible(fingerImageView.isVisible());
                myfinger.setX(fingerImageView.getLayoutX());
                myfinger.setY(fingerImageView.getLayoutY());
                ArrayList<Double> positions=new ArrayList<>();
                positions.add(obstaclegroup.getLayoutX());
                positions.add(obstaclegroup.getLayoutY());
                positions.add(obstaclegroup.getRotate());
                System.out.println("OBs group X= "+obstaclegroup.getLayoutX());
                System.out.println("OBs group Y= "+obstaclegroup.getLayoutY());
                getObstacles().get(0).setCoordinates(positions);
                ArrayList<Double> positions2=new ArrayList<>();
                positions2.add(obstaclegroup2.getLayoutX());
                positions2.add(obstaclegroup2.getLayoutY());
                positions2.add(obstaclegroup2.getRotate());
                System.out.println("OBs group2 X= "+obstaclegroup2.getLayoutX());
                System.out.println("OBs group2 Y= "+obstaclegroup2.getLayoutY());
                getObstacles().get(1).setCoordinates(positions2);
                ImageView img=colorswitcher_imageView;
                getColorSwitchers().get(0).setX(img.getX());
                getColorSwitchers().get(0).setY(img.getY());
                getColorSwitchers().get(0).setVisible(img.isVisible());

                img=starimageView;
                getStars().get(0).setX(img.getX());
                getStars().get(0).setY(img.getY());
                getStars().get(0).setVisible(img.isVisible());

                ImageView img2=starimageView2;
                getStars().get(1).setX(img2.getX());
                getStars().get(1).setY(img2.getY());
                getStars().get(1).setVisible(img2.isVisible());

                Circle circ=ball.getCircle();
                ball.setX(circ.getCenterX());
                ball.setY(circ.getCenterY());
                ball.setRadius(circ.getRadius());
                timer.stop();
                pause(window, primaryStage,rotate,rotate2);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

        });
        scene1.setFill(Color.rgb(40,40,40));
        window.setScene(scene1);
        window.show();
    }
    public void serialise(String filename)
    {
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
    }
}
