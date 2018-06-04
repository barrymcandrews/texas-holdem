import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Game {
    private static Game gameInstance = new Game();
    // TODO: map of all players in game. Need AI players/names first
    private String userName;
    private int numberOfOpponents;

    private Game() {
        // Create option fields
        JTextField nameField = new JTextField(5);
        Integer[] selectionValues = { 1, 2, 3, 4, 5, 6, 7 };
        JComboBox<Integer> playersField = new JComboBox<>(selectionValues);
        playersField.setSelectedIndex(0);

        // Format pop up panel
        JPanel panel = new JPanel();
        panel.add(new JLabel("Please enter your name:"));
        panel.add(nameField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Please enter the number of opponents:"));
        panel.add(playersField);

        // get the results.
        int result = JOptionPane.showConfirmDialog(null, panel, "Please enter the following:",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            numberOfOpponents = (int) playersField.getSelectedItem();
            userName = nameField.getText();
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
}
