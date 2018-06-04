import javax.swing.JOptionPane;

public class Game {
    private static Game gameInstance = new Game();
    //TODO: map of all players in game. Wait for corinne to push player model
    private String userName;
    
    private Game() {
       userName = JOptionPane.showInputDialog("Please enter your name.");    
    }
    
    public static Game getInstance() {
        return gameInstance;
    }

    public String getUserName() {
        return userName;
    }
}
