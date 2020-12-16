import javafx.scene.image.ImageView;


public class Star extends GameElement
{
    private int value;
    private boolean Visible;

    public boolean isVisible() {
        return Visible;
    }

    public void setVisible(boolean visible) {
        Visible = visible;
    }

    private transient ImageView starImageView;

    public Star(ImageView star)
    {
        this.starImageView = star;
        this.value = 1;
    }
    public ImageView getStarImageView() {
        return starImageView;
    }

    public void setStarImageView(ImageView starImageView) {
        this.starImageView = starImageView;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
