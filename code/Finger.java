import javafx.scene.image.ImageView;

public class Finger extends GameElement{
    private boolean Visible;
    private transient ImageView FingerImageView;
    public Finger(ImageView img){
        FingerImageView=img;
    }

    public boolean isVisible() {
        return Visible;
    }

    public void setVisible(boolean visible) {
        Visible = visible;
    }

    public ImageView getFingerImageView() {
        return FingerImageView;
    }

    public void setFingerImageView(ImageView fingerImageView) {
        FingerImageView = fingerImageView;
    }


}
