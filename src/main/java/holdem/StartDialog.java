package holdem;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;

import static java.awt.GridBagConstraints.LINE_END;
import static java.awt.GridBagConstraints.CENTER;

/**
 * Class that gets all show information needed for a game in a dialogue
 */
public class StartDialog {

    private JPanel dialogPanel = new JPanel();
    private JLabel instructionsLabel;
    private JTextField nameField;
    private JComboBox<Integer> playersField;

    public class DialogResult {
        public int numberOfOpponents;
        public String userName;
    }

    public StartDialog() {
        dialogPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5, 5, 5, 5);

        instructionsLabel = new JLabel("Please enter the following:");
        c.anchor = CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        dialogPanel.add(instructionsLabel, c);

        JLabel nameLabel = new JLabel("Your Name:");
        c.anchor = LINE_END;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        dialogPanel.add(nameLabel, c);

        nameField = new JTextField(20);
        c.anchor = CENTER;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        dialogPanel.add(nameField, c);

        JLabel numberOfPlayersLabel = new JLabel("Number of Opponents:");
        c.anchor = LINE_END;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        dialogPanel.add(numberOfPlayersLabel, c);

        Integer[] selectionValues = { 1, 2, 3, 4, 5, 6, 7 };
        playersField = new JComboBox<>(selectionValues);
        playersField.setSelectedIndex(0);
        c.anchor = CENTER;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        dialogPanel.add(playersField, c);

    }

    
    public DialogResult show() {

        // get the results. if user presses cancel, exit
        int result = JOptionPane.showConfirmDialog(null, dialogPanel, "New Texas Hold'em Game", JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION) {
            System.exit(0);
        }

        DialogResult dialogResult = new DialogResult();
        dialogResult.numberOfOpponents = (int) playersField.getSelectedItem();
        dialogResult.userName = nameField.getText();

        // verify that name field isn't empty. numberOfOpponents will default to 1. Exit if cancel is selected
        if (dialogResult.userName == null || dialogResult.userName.isEmpty()) {
            instructionsLabel.setText("Username must not be empty.");
            return show();
        }

        return dialogResult;
    }
}
