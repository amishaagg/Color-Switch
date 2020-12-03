import javafx.scene.image.ImageView;

public class ColorSwitcher
{
    private ImageView colorSwitcher;

    public ColorSwitcher(ImageView colorSwitcher)
    {
        this.colorSwitcher = colorSwitcher;
    }

    public ImageView getColorSwitcher() {
        return colorSwitcher;
    }

    public void setColorSwitcher(ImageView colorSwitcher) {
        this.colorSwitcher = colorSwitcher;
    }

    public static void changeColor(Ball ball){};
}
