package holdem;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class that gets all init information needed for a game in a dialogue
 * 
 * @author Laura
 *
 */
public class StartDialogue {
    private int numberOfOpponents;
    private String userName;
    
    public StartDialogue init() {
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
        return this;
    }

    public int getNumberOfOpponents() {
        return numberOfOpponents;
    }
    
    public String getUserName() {
        return userName;
    }
}