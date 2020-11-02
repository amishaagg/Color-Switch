package sample;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.util.Random;

public class Main extends Application
{

    Button button;
    Label l;
    @Override
    public void start(Stage primaryStage)
    {
        Circle player = new Circle(300, 600, 20, Color.BLUE);
        Pane root = new Pane();
        root.setPrefSize(600,700);
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        Rectangle rectangle = new Rectangle();
        rectangle.setX(150.0f);
        rectangle.setY(75.0f);
        rectangle.setWidth(300.0f);
        rectangle.setHeight(50.0f);
        rectangle.setFill(Color.YELLOW);
        scene.setFill(Color.BLACK);
        scene.setOnKeyPressed(e ->
        {
            if(e.getCode()==KeyCode.UP)
            {
                player.setCenterY(player.getCenterY() - 10);
                Random rand = new Random();
                int r = rand.nextInt(4);
                switch (r)
                {
                    case 0: rectangle.setFill(Color.BLUE);
                            break;
                    case 1: rectangle.setFill(Color.RED);
                            break;
                    case 2: rectangle.setFill(Color.GREEN);
                            break;
                    default: rectangle.setFill(Color.YELLOW);
                }
            }
            if(player.getCenterY()-player.getRadius()<=rectangle.getY()+rectangle.getHeight() && player.getFill()!=rectangle.getFill())
                System.out.println("game over");
        });
        if(player.getCenterY()-player.getRadius()<=rectangle.getY()+rectangle.getHeight())
            System.out.println("game over");
        root.getChildren().addAll(player, rectangle);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
