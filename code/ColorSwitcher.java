import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Random;

public class ColorSwitcher
{
    private transient ImageView colorSwitcher;

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

    public static void changeColor(Ball ball)
    {
        Random rand = new Random();
        int color = rand.nextInt(4);
        switch (color)
        {
            case 0: if(ball.getColor().equals("yellow")) //yellow
                    {
                        ball.getCircle().setFill(Color.rgb(144,13,255));
                        ball.setColor("purple");
                    }
                    else
                    {
                        ball.getCircle().setFill(Color.rgb(250,225,0));
                        ball.setColor("yellow");
                    }
                    break;

            case 1: if(ball.getColor().equals("cyan")) //cyan
                    {
                        ball.getCircle().setFill(Color.rgb(250,225,0));
                        ball.setColor("yellow");
                    }
                    else
                    {
                        ball.getCircle().setFill(Color.rgb(50,219,240));
                        ball.setColor("cyan");
                    }
                    break;

            case 2: if(ball.getColor().equals("purple")) //purple
                    {
                        ball.getCircle().setFill(Color.rgb(255,1,129));
                        ball.setColor("pink");
                    }
                    else
                    {
                        ball.getCircle().setFill(Color.rgb(144,13,225));
                        ball.setColor("purple");
                    }
                    break;

            case 3: if(ball.getColor().equals("pink")) //pink
                    {
                        ball.getCircle().setFill(Color.rgb(50,219,240));
                        ball.setColor("cyan");
                    }
                    else
                    {
                        ball.getCircle().setFill(Color.rgb(255,1,129));
                        ball.setColor("pink");
                    }
                    break;
        }
    }
}
