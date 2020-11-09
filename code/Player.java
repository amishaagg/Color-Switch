public class Player
{
    private int stars;
    private Ball ball = new Ball(this);
    private boolean alive = true;

    public int getScore() {
        return stars;
    }

    public void setScore(int stars) {
        this.stars = stars;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
