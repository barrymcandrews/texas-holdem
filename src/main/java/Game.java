import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Models.*;

import java.util.ArrayList;

public class Game {
    private static Game gameInstance = new Game();
    // TODO: map of all players in game. Need AI players/names first
    private String userName;
    private int numberOfOpponents;

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
            if (userName != null) {
                Player user = createPlayer(userName);
                //TODO add user to the player array
            }
            ArrayList<Player> AI = new ArrayList<>();
            for (int i = 0; i < numberOfOpponents; i++) {
                AI.add(createAI(new Player(), numberOfOpponents));
            }
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

    private static Player createPlayer(String name) {
        Player user = new Player(name, Player.Role.PLAYER);
        String[] names = user.getAiNames();
        for(String p : names){
            if(p.equals(name)){
                user.resetAiNames(p);
            }
        }
        System.out.println(user.getName());
        return user;
    }

    private static Player createAI(Player ai, int numberOfOpponents) {
        String[] names = ai.getAiNames();
        for (int i = 0; i < numberOfOpponents; i++) {
            if (i == 0) {
                ai.setName(names[i]);
                ai.setRole(Player.Role.DEALER);
            } else {
                ai.setName(names[i]);
                ai.setRole(Player.Role.PLAYER);
            }
        }
        return ai;
    }
}
