import javafx.scene.shape.Circle;


public class Ball extends GameElement
{
    private String color;
    private boolean jumping;
    private transient Circle circle;
    private double radius;
    public void jump()
    {
        this.circle.setCenterY(circle.getCenterY()-40);
    };

    public Ball(Circle circle, String color)
    {
        this.circle = circle;
        this.color = color;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
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
        return  jumping;
    }

    public void setJumping(boolean alive) {
        this.jumping = alive;
    }
}
