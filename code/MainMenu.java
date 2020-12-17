import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class MainMenu extends Application implements Serializable
{
    Button btnNewGame, btnResumeGame, btnExitGame, btnreturn;
    Scene scene1, scene2;
    Stage window;
    Media media;
    private static ArrayList<Game> savedGames;
    public static ArrayList<Game> getSavedGames() {
        return savedGames;
    }

    public static void setSavedGames(ArrayList<Game> savedGames) {
        MainMenu.savedGames = savedGames;
    }


    public void saveGame(){};
    public void newGame(Stage stage)
    {
        window.hide();
        try {
            Game game=new Game();
            game.startGame(window);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    public void exitGame()
    {
        window.close();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        window = stage;
        window.setTitle("Color Switch");
        window.setOnCloseRequest(e->
        {
            e.consume();
            exitGame();
        });
        btnNewGame=new Button();
        Image play = new Image(new FileInputStream("assets/play.png"));
        ImageView imageViewPlay = new ImageView(play);
        imageViewPlay.setFitWidth(80);
        imageViewPlay.setFitHeight(80);
        btnNewGame.setGraphic(imageViewPlay);
        btnNewGame.setBackground(Background.EMPTY);
        btnResumeGame=new Button();
        Image resume = new Image(new FileInputStream("assets/resume.png"));
        ImageView imageViewResume = new ImageView(resume);
        btnResumeGame.setGraphic(imageViewResume);
        btnResumeGame.setBackground(Background.EMPTY);
        btnExitGame=new Button();
        Image exit = new Image(new FileInputStream("assets/exit.png"));
        ImageView imageViewExit = new ImageView(exit);
        btnExitGame.setGraphic(imageViewExit);
        btnExitGame.setBackground(Background.EMPTY);

        btnNewGame.setOnAction(e->newGame(window));
        btnResumeGame.setOnAction(e->
        {
            window.hide();
            try {
                resumeGame();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
        btnExitGame.setOnAction(e->exitGame());

        Button amisha=new Button();
        Button questionmark=new Button();
        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.millis(3000));
        rotate.setNode(amisha);
        rotate.play();
        ImageView imageViewGithub = new ImageView(new Image(new FileInputStream("assets/github.png")));
        amisha.setGraphic(imageViewGithub);
        amisha.setBackground(Background.EMPTY);
        amisha.setLayoutX(100.0);
        amisha.setLayoutY(550.0);
        amisha.setOnAction(e->{
            getHostServices().showDocument("https://github.com/amishaagg/Color-Switch");
        });

        ImageView imageViewQuestionMark = new ImageView(new Image(new FileInputStream("assets/questionmark.png")));
        questionmark.setGraphic(imageViewQuestionMark);
        questionmark.setBackground(Background.EMPTY);
        questionmark.setLayoutX(200.0);
        questionmark.setLayoutY(550.0);

        Button btnMusic = new Button();
        ImageView imageViewMusic = new ImageView(new Image(new FileInputStream("assets/music.png")));
        ImageView imageViewNoMusic = new ImageView(new Image(new FileInputStream("assets/no_music.png")));
        btnMusic.setGraphic(imageViewMusic);
        btnMusic.setBackground(Background.EMPTY);

        String musicFile = "music/background_theme.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        btnMusic.setOnAction(e->
        {
            if(btnMusic.getGraphic().equals(imageViewMusic)){
                mediaPlayer.pause();
                btnMusic.setGraphic(imageViewNoMusic);
            }
            else {
                mediaPlayer.play();
                btnMusic.setGraphic(imageViewMusic);
            }
        });

        rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.millis(3000));
        rotate.setNode(questionmark);
        rotate.play();

        rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.millis(3000));
        rotate.setNode(btnMusic);
        rotate.play();

        rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.millis(3000));
        rotate.setNode(btnExitGame);
        rotate.play();

        Image image = new Image(new FileInputStream("assets/mainmenu.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(-5);
        imageView.setY(0);
        imageView.setFitHeight(700);
        imageView.setFitWidth(400);
        window.setHeight(700);
        window.setWidth(400);
        Group layout1 = new Group(imageView);
        layout1.getChildren().addAll(btnNewGame,btnResumeGame,btnExitGame,btnMusic,amisha,questionmark);
        btnNewGame.setPrefWidth(80);
        btnNewGame.setLayoutX(160);
        btnNewGame.setLayoutY(300);
        btnResumeGame.setPrefWidth(80);
        btnResumeGame.setLayoutX(100);
        btnResumeGame.setLayoutY(480);
        btnExitGame.setLayoutX(310);
        btnExitGame.setLayoutY(70);
        btnMusic.setLayoutX(10);
        btnMusic.setLayoutY(65);
        scene1=new Scene(layout1,300,300);
        window.setScene(scene1);
        window.show();
    }

    public void resumeGame() throws IOException, ClassNotFoundException {
        btnreturn=new Button();
        Stage stage = new Stage();
        Image home = new Image(new FileInputStream("assets/resume_home.png"));
        ImageView imageViewHome = new ImageView(home);
        imageViewHome.setFitWidth(50);
        imageViewHome.setFitHeight(50);
        btnreturn.setGraphic(imageViewHome);
        btnreturn.setBackground(Background.EMPTY);
        btnreturn.setLayoutX(10);
        Image choose = new Image(new FileInputStream("assets/CHOOSE_GAME.png"));
        ImageView imageViewChoose = new ImageView(choose);
        ImageView imageViewGame1 = new ImageView(new Image(new FileInputStream("assets/game1.png")));
        Button game1 = new Button();
        game1.setGraphic(imageViewGame1);
        game1.setBackground(Background.EMPTY);
        game1.setLayoutX(-10);
        game1.setLayoutY(70);
        ImageView imageViewGame2 = new ImageView(new Image(new FileInputStream("assets/game2.png")));
        Button game2 = new Button();
        game2.setGraphic(imageViewGame2);
        game2.setBackground(Background.EMPTY);
        game2.setLayoutX(-10);
        game2.setLayoutY(125);
        ImageView imageViewGame3 = new ImageView(new Image(new FileInputStream("assets/game3.png")));
        Button game3 = new Button();
        game3.setGraphic(imageViewGame3);
        game3.setBackground(Background.EMPTY);
        game3.setLayoutX(-10);
        game3.setLayoutY(180);
        ImageView imageViewGame4 = new ImageView(new Image(new FileInputStream("assets/game4.png")));
        Button game4 = new Button();
        game4.setGraphic(imageViewGame4);
        game4.setBackground(Background.EMPTY);
        game4.setLayoutX(-10);
        game4.setLayoutY(235);
        ImageView imageViewGame5 = new ImageView(new Image(new FileInputStream("assets/game5.png")));
        imageViewGame5.setLayoutY(270);
        Button game5 = new Button();
        game5.setGraphic(imageViewGame5);
        game5.setBackground(Background.EMPTY);
        game5.setLayoutX(-10);
        game5.setLayoutY(290);

        game1.setOnAction(e->{
            deserialise("file.bin");
            stage.close();
        });
        game2.setOnAction(e->{
            deserialise("file1.bin");
            stage.close();
        });
        game3.setOnAction(e->{
            deserialise("file2.bin");
            stage.close();
        });
        game4.setOnAction(e->{
            deserialise("file3.bin");
            stage.close();
        });
        game5.setOnAction(e->{
            deserialise("file4.bin");
            stage.close();
        });

        Text score1 = new Text(10.0, 20.0, "");
        score1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        score1.setFill(Color.WHITE);
        Text score2 = new Text(10.0, 20.0, "");
        score2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        score2.setFill(Color.WHITE);
        Text score3 = new Text(10.0, 20.0, "");
        score3.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        score3.setFill(Color.WHITE);
        Text score4 = new Text(10.0, 20.0, "");
        score4.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        score4.setFill(Color.WHITE);
        Text score5 = new Text(10.0, 20.0, "");
        score5.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        score5.setFill(Color.WHITE);

        Group layout2=new Group();
        layout2.getChildren().addAll(imageViewChoose, btnreturn);
        layout2.getChildren().addAll(game1, game2, game3, game4, game5);

        FileInputStream file;
        try {
            file = new FileInputStream("file.bin");
        }catch (FileNotFoundException e)
        {
            layout2.getChildren().removeAll(game1, game2, game3, game4, game5);
        }
        try {
            file = new FileInputStream("file1.bin");
        }catch (FileNotFoundException e)
        {
            layout2.getChildren().removeAll(game2, game3, game4, game5);
        }
        try {
            file = new FileInputStream("file2.bin");
        }catch (FileNotFoundException e)
        {
            layout2.getChildren().removeAll(game3, game4, game5);
        }
        try {
            file = new FileInputStream("fil3.bin");
        }catch (FileNotFoundException e)
        {
            layout2.getChildren().removeAll(game4, game5);
        }
        try {
            file = new FileInputStream("file4.bin");
        }catch (FileNotFoundException e)
        {
            layout2.getChildren().removeAll(game5);
        }

        if(layout2.getChildren().contains(game1))
            setStarInResumeGame("file.bin", game1, layout2);
        if(layout2.getChildren().contains(game2))
            setStarInResumeGame("file1.bin", game2, layout2);
        if(layout2.getChildren().contains(game3))
            setStarInResumeGame("file2.bin", game3, layout2);
        if(layout2.getChildren().contains(game4))
            setStarInResumeGame("file3.bin", game4, layout2);
        if(layout2.getChildren().contains(game5))
            setStarInResumeGame("file4.bin", game5, layout2);

        scene2=new Scene(layout2,250,250);
        scene2.setFill(Color.rgb(40,40,40));
        stage.setScene(scene2);
        stage.setHeight(700);
        stage.setWidth(400);
        stage.show();
        btnreturn.setOnAction(e->
        {
            stage.close();
            window.show();
        });
    }

    public void setStarInResumeGame(String file, Button btn, Group layout2) throws IOException, ClassNotFoundException
    {
        Game object1;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        // Method for deserialization of object
        object1 = (Game)in.readObject();
        in.close();
        Text score = new Text(10, 30, object1.getScore()+"");
        score.setFont(Font.font("Verdana", FontWeight.BOLD,  30));
        score.setFill(Color.WHITE);
        score.setLayoutY(btn.getLayoutY()+10);
        score.setLayoutX(btn.getLayoutX()+330);
        layout2.getChildren().add(score);
    }

    public static void main(String[] args) {
        launch(args);
    }
    public void deserialise(String filename){
        Game object1 = null;
        //String filename="file.bin";
        try{
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            // Method for deserialization of object
            object1 = (Game)in.readObject();
            in.close();
            file.close();
            System.out.println("Object has been deserialized ");
            System.out.println("a = " + object1.getScore());
            for(Obstacle obs:object1.getObstacles()){
                System.out.println(obs.getCoordinates());
            }
            System.out.println("Star X"+object1.getStars().get(0).getX());
            System.out.println("Star Y"+object1.getStars().get(0).getY());
            Game game=new Game();
            game.savedGame(window,object1.getScore(),object1.getStars(),object1.getObstacles()
                    ,object1.getBall(),object1.getColorSwitchers(),object1.getMyfinger());
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

    }

}
