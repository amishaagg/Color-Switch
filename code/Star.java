import javafx.scene.image.ImageView;

public class Star extends GameElement
{
    private int value;
    private transient ImageView starImageView;

    public Star(ImageView star)
    {
        this.starImageView = star;
        this.value = 1;
    }
    public ImageView getStarImageView() {
        return starImageView;
    }

    public void setStar(ImageView starImageView) {
        this.starImageView = starImageView;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

//    public void vanish(Game game)
//    {
//        this.starImageView.setVisible(false);
//        Game.setScore(Game.getScore()+1);
//    }
}
