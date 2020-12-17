import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.*;
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
            }
        });
        btnExitGame.setOnAction(e->exitGame());

        Button btnMusic = new Button();
        ImageView imageViewMusic = new ImageView(new Image(new FileInputStream("assets/music.png")));
        ImageView imageViewNoMusic = new ImageView(new Image(new FileInputStream("assets/no_music.png")));
        btnMusic.setGraphic(imageViewMusic);
        btnMusic.setBackground(Background.EMPTY);

        String musicFile = "staythenight.mp3";
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

        Image image = new Image(new FileInputStream("assets/mainmenu.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(-5);
        imageView.setY(0);
        imageView.setFitHeight(700);
        imageView.setFitWidth(400);
        window.setHeight(700);
        window.setWidth(400);
        Group layout1 = new Group(imageView);
        layout1.getChildren().addAll(btnNewGame,btnResumeGame,btnExitGame,btnMusic);
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

    public void resumeGame() throws FileNotFoundException
    {
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
        });
        game2.setOnAction(e->{
            deserialise("file2.bin");
        });
        game3.setOnAction(e->{
            deserialise("file3.bin");
        });
        game4.setOnAction(e->{
            deserialise("file4.bin");
        });
        game5.setOnAction(e->{
            deserialise("file5.bin");
        });
        Group layout2=new Group();
        layout2.getChildren().addAll(imageViewChoose, btnreturn);
        layout2.getChildren().addAll(game1, game2, game3, game4, game5);
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
