import javafx.application.Application;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

public class MainMenu extends Application implements Serializable
{
    Button btnNewGame, btnResumeGame, btnExitGame, btnreturn;
    Scene scene1, scene2;
    Stage window;
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
        window.setOnCloseRequest(e->{
            e.consume();
            exitGame();
        });
        btnNewGame=new Button();
        Image play = new Image(new FileInputStream("assets/play.png"));
        ImageView imageViewplay = new ImageView(play);
        imageViewplay.setFitWidth(80);
        imageViewplay.setFitHeight(80);
        btnNewGame.setGraphic(imageViewplay);
        btnNewGame.setBackground(Background.EMPTY);
        btnResumeGame=new Button();
        Image resume = new Image(new FileInputStream("assets/resume.png"));
        ImageView imageViewResume = new ImageView(resume);
        btnResumeGame.setGraphic(imageViewResume);
        btnResumeGame.setBackground(Background.EMPTY);
        btnExitGame=new Button("Exit Game");
        btnreturn=new Button("Go back to main menu");
        Button b = new Button();
        btnNewGame.setOnAction(e->
        {
            window.hide();
            try {
                new Game(window);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        btnResumeGame.setOnAction(e->resumeGame());
        btnExitGame.setOnAction(e->exitGame());
        btnreturn.setOnAction(e->window.setScene(scene1));

        Image image = new Image(new FileInputStream("assets/mainmenu.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(-5);
        imageView.setY(0);
        imageView.setFitHeight(700);
        imageView.setFitWidth(400);
        window.setHeight(700);
        window.setWidth(400);
        System.out.println(image.getHeight());
        System.out.println(image.getWidth());
        Group layout1 = new Group(imageView);
        //layout1.getChildren().add(btnNewGame);
        layout1.getChildren().addAll(btnNewGame,btnResumeGame,btnExitGame);
        btnNewGame.setPrefWidth(80);
        btnNewGame.setLayoutX(160);
        btnNewGame.setLayoutY(300);
        btnResumeGame.setPrefWidth(80);
        btnResumeGame.setLayoutX(100);
        btnResumeGame.setLayoutY(480);
        btnExitGame.setPrefWidth(80);
        btnExitGame.setLayoutX(250);
        btnExitGame.setLayoutY(310);
        scene1=new Scene(layout1,300,300);
        Label label=new Label("Yo I am list of games");
        VBox layout2=new VBox(20);
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(label,btnreturn);
        scene2=new Scene(layout2,250,250);
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

    public static void display(String title,String message){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Label label=new Label(message);
        Button closeButton=new Button("Return to game");
        closeButton.setOnAction(e->window.close());

        VBox layout=new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label,closeButton);

        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void resumeGame(){
        window.setScene(scene2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
