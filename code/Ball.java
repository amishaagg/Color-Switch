import javafx.scene.shape.Circle;

public class Ball extends GameElement
{
    private String color;
    private boolean alive;
    private Circle circle;
    public void jump()
    {

    };

    public Ball(Circle circle, String color)
    {
        this.circle = circle;
        this.color = color;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
