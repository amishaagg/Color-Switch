import java.io.Serializable;

public class GameElement implements Serializable
{
    private double x;
    private double y;
    private int height;
    private int width;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void rotate()
    {}

}
