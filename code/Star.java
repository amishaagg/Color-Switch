import javafx.scene.image.ImageView;

public class Star extends GameElement
{
    private int value;
    private ImageView star;

    public Star(ImageView star)
    {
        this.star = star;
        this.value = 1;
    }
    public ImageView getStar() {
        return star;
    }

    public void setStar(ImageView star) {
        this.star = star;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
