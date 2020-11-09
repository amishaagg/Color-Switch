import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Main extends Application {
    @Override
    public void start(Stage window) throws Exception
    {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        Button b = new Button("Click");
        Label l = new Label("0");
        b.setOnAction(e ->
        {
            int val = Integer.parseInt(l.getText())+1;
            l.setText(val+"");
        });
        layout.getChildren().addAll(b,l);
        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
