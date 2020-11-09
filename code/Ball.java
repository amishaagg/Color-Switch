public class Ball extends GameElement
{
    private Player player;
    private String color;
    public Ball(Player player)
    {
        this.player=player;
    }
	public void jump() {
		
	}
	public void increaseScore() {
		
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}


    
}
