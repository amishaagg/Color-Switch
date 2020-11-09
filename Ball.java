public class Ball extends GameElement
{
    private int y;
    private boolean alive;

    public Ball(Player player)
    {

    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
