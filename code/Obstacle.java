import javafx.scene.Group;
import java.util.ArrayList;

public class Obstacle extends GameElement
{
    private double speed;
    private ArrayList<String> colors;
    private Group obstacleGroup;

    public Obstacle(Group obstacleGroup)
    {
        this.obstacleGroup = obstacleGroup;
    }
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public Group getObstacleGroup() {
        return obstacleGroup;
    }

    public void setObstacleGroup(Group obstacleGroup) {
        this.obstacleGroup = obstacleGroup;
    }
}
