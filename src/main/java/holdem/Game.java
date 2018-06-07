package holdem;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import holdem.models.*;
import holdem.models.Player.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Game {
    private static Game gameInstance = new Game();

    private String userName;
    private int numberOfOpponents;
    private ArrayList<Player> players = new ArrayList<>();

    private Map<String, Player> playersMap = new HashMap<>(); //map player name to object for quick retrieval
    private ArrayList<String> aiNames = new ArrayList<>();
    private Deck deck = new Deck();
    
    private Game() {
        // Create option fields
        JTextField nameField = new JTextField(5);
        Integer[] selectionValues = {1, 2, 3, 4, 5, 6, 7};
        JComboBox<Integer> playersField = new JComboBox<>(selectionValues);
        playersField.setSelectedIndex(0);

        // Format pop up panel
        JPanel panel = new JPanel();
        panel.add(new JLabel("Please enter your name:"));
        panel.add(nameField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Please enter the number of opponents:"));
        panel.add(playersField);

        // get the results. if user presses cancel, exit
        int result = JOptionPane.showConfirmDialog(null, panel, "Please enter the following:", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            numberOfOpponents = (int) playersField.getSelectedItem();
            userName = nameField.getText();
            // verify that name field isn't empty. numberOfOpponents will default to 1. Exit if cancel is selected
            while (userName == null || userName.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "You did not enter your name. Please try again", "Error", JOptionPane.ERROR_MESSAGE);
                int n = JOptionPane.showConfirmDialog(null, new JPanel().add(nameField), "Enter your name.", JOptionPane.OK_CANCEL_OPTION);
                if (n == JOptionPane.CANCEL_OPTION)
                    System.exit(0);
                else {
                    userName = nameField.getText();
                }
            }
        } else {
            //exit if cancel is selected
            System.out.println("Goodbye!");
            System.exit(0);
        }
        initializePlayers();
    }

    public static Game getInstance() {
        return gameInstance;
    }

    public String getUserName() {
        return userName;
    }

    public int getNumberOfOpponents() {
        return numberOfOpponents;
    }
    
    public ArrayList<String> getAiNames() {
        return aiNames;
    }
    
    public Map<String, Player> getPlayersMap() {
        return playersMap;
    }
    
    public Set<Card> deal(int numberOfCards) {
        Set<Card> hand = new HashSet<>();
        for(int i = 0; i < numberOfCards; i++ )
            hand.add(deck.dealCard());
        return hand;
    }
    
    private void initializePlayers()
    {
        aiNames.addAll(Arrays.asList(new String[] {"Bob", "Linda", "Tina", "Gene", "Louise", "Jimmy Jr.", "Teddy", "AndyenOllie"}));
        createPlayer(userName);
        createAI();
    }

    private void createPlayer(String name) {
        // if player name equals an AI name, delete that AI name and use the backup.
        for(int i = 0; i < numberOfOpponents; i++){
            if(aiNames.get(i).equals(name))
                aiNames.remove(i);
        }
        Player player = new Player(userName, Player.Role.PLAYER, deal(2));
        playersMap.put(player.getName(), player);
        players.add(player);
    }

    private void createAI() {
        for (int i = 0; i < numberOfOpponents; i++) {
            Player ai;;;
            if (i == 0) {         
                ai = new Player(aiNames.get(i), Role.DEALER, deal(2));
            } else {
                ai = new Player(aiNames.get(i), Role.PLAYER, deal(2));        
            }
            playersMap.put(aiNames.get(i), ai);
            players.add(ai);
        }
    }
    
    public String toString() {
        return "Number of playersMap: " + (numberOfOpponents + 1) +
               " | Players: " + playersMap.toString();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
