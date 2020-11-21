import javafx.application.Application;
import java.nio.file.Paths;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

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
    public void newGame(Stage stage) throws FileNotFoundException
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
        imageViewExit.setFitHeight(40);
        imageViewExit.setFitWidth(40);
        btnExitGame.setGraphic(imageViewExit);
        btnExitGame.setBackground(Background.EMPTY);
        btnNewGame.setOnAction(e->
        {
            window.hide();
            try {
                new Game(window);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        btnResumeGame.setOnAction(e-> {
            try {
                resumeGame();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        btnExitGame.setOnAction(e->exitGame());

        Image image = new Image(new FileInputStream("assets/mainmenu.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(-5);
        imageView.setY(0);
        imageView.setFitHeight(700);
        imageView.setFitWidth(400);
        window.setHeight(700);
        window.setWidth(400);
        Group layout1 = new Group(imageView);
        layout1.getChildren().addAll(btnNewGame,btnResumeGame,btnExitGame);
        btnNewGame.setPrefWidth(80);
        btnNewGame.setLayoutX(160);
        btnNewGame.setLayoutY(300);
        btnResumeGame.setPrefWidth(80);
        btnResumeGame.setLayoutX(100);
        btnResumeGame.setLayoutY(480);
        btnExitGame.setPrefWidth(80);
        btnExitGame.setLayoutX(300);
        btnExitGame.setLayoutY(70);
        scene1=new Scene(layout1,300,300);
        window.setScene(scene1);
        window.show();
    };
    public void exitGame()
    {
        window.close();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        newGame(stage);
    }

    public void resumeGame() throws FileNotFoundException {
        btnreturn=new Button();
        Image home = new Image(new FileInputStream("assets/resume_home.png"));
        ImageView imageViewHome = new ImageView(home);
        imageViewHome.setFitWidth(50);
        imageViewHome.setFitHeight(50);
        btnreturn.setGraphic(imageViewHome);
        btnreturn.setBackground(Background.EMPTY);
        btnreturn.setLayoutX(10);
        Image choose = new Image(new FileInputStream("assets/CHOOSE_GAME.png"));
        ImageView imageViewChoose = new ImageView(choose);
        //imageViewChoose.setFitHeight(80);
        //imageViewChoose.setFitWidth(400);
        Group layout2=new Group();
        layout2.getChildren().addAll(imageViewChoose, btnreturn);
        scene2=new Scene(layout2,250,250);
        scene2.setFill(Color.rgb(40,40,40));
        window.setScene(scene2);
        btnreturn.setOnAction(e->window.setScene(scene1));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
