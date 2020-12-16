import javafx.scene.Group;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Obstacle extends GameElement
{
    private double speed;
    private ArrayList<String> colors;
    private transient Group obstacleGroup;
    private String type;
    private ArrayList<Double> coordinates;
    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

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
