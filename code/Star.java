public class Star extends GameElement
{
    private int value;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public void vanish() //star will disappear if ball touches it
    {}
}
