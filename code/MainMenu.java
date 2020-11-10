import java.util.ArrayList;

public class MainMenu
{
    private static ArrayList<Game> savedGames;

    public static ArrayList<Game> getSavedGames() {
        return savedGames;
    }

    public static void setSavedGames(ArrayList<Game> savedGames) {
        MainMenu.savedGames = savedGames;
    }

    public void saveGame(){};
    public void newGame(){};
    public void exitGame(){};
}
