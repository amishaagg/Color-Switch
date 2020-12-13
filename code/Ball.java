import javafx.scene.shape.Circle;

public class Ball extends GameElement
{
    private String color;
    private boolean jumping;
    private transient Circle circle;
    public void jump()
    {
        this.circle.setCenterY(circle.getCenterY()-50);
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

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}
