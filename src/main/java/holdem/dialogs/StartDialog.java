package holdem.dialogs;

import holdem.Constants;
import holdem.MyTimerTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import static java.awt.GridBagConstraints.LINE_END;
import static java.awt.GridBagConstraints.CENTER;

/**
 * Class that gets all show information needed for a game in a dialogue
 */
public class StartDialog {

    private JPanel dialogPanel = new JPanel();
    private JLabel instructionsLabel;
    private JTextField nameField;
    private JComboBox<Integer> playersComboBox;
    private JCheckBox timerCheckBox = new JCheckBox("Round Time Limit", false);
    private JCheckBox hecklingCheckBox = new JCheckBox("Enable heckling mode?", false);
    private JSpinner timerSpinner = new JSpinner();
    private SpinnerNumberModel timerSpinnerModel;
    private JTextField fileTextField = new JTextField();

    private JFileChooser fileChooser = new JFileChooser();

    public class DialogResult {
        public int numberOfOpponents;
        public String userName;
        public int timer;
        public String imgPath;
        public boolean enableHeckling;
    }

    public StartDialog() {
        dialogPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5, 5, 5, 5);

        instructionsLabel = new JLabel("Please enter the following:");
        c.anchor = CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.NONE;
        dialogPanel.add(instructionsLabel, c);

        JLabel nameLabel = new JLabel("Your Name:");
        c.anchor = LINE_END;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        dialogPanel.add(nameLabel, c);

        nameField = new JTextField();
        c.anchor = CENTER;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
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
        playersComboBox = new JComboBox<>(selectionValues);
        playersComboBox.setSelectedIndex(0);
        c.anchor = CENTER;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        dialogPanel.add(playersComboBox, c);

        c.anchor = LINE_END;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 5, 5, 5);
        timerSpinnerModel = new SpinnerNumberModel(30, 10, 120, 5);
        timerSpinner.setModel(timerSpinnerModel);
        timerSpinner.setEnabled(false);
        timerCheckBox.addItemListener(this::timeCheckboxPressed);
        dialogPanel.add(timerCheckBox, c);

        c.anchor = CENTER;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);
        dialogPanel.add(timerSpinner, c);

        JLabel fileLabel = new JLabel("Avatar Image:");
        c.anchor = LINE_END;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 5, 5, 5);
        dialogPanel.add(fileLabel, c);

        c.anchor = CENTER;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 5, 5, 0);
        fileTextField.setEditable(false);
        fileTextField.setText("src/main/resources/player-images/default.png");
        dialogPanel.add(fileTextField, c);

        JButton chooseFileButton = new JButton("Choose File");
        c.anchor = LINE_END;
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 0, 5, 5);
        chooseFileButton.addActionListener(this::chooseFileButtonPressed);
        dialogPanel.add(chooseFileButton, c);

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        c.anchor = CENTER;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 5, 5, 5);
        dialogPanel.add(hecklingCheckBox, c);
    }

    public DialogResult show() {
        DialogResult dialogResult = new DialogResult();

        if (Constants.DEBUG) {
            dialogResult.userName = "Debug User";
            dialogResult.numberOfOpponents = 7;
            dialogResult.timer = 30;
            dialogResult.imgPath = "src/main/resources/player-images/default.png";
            dialogResult.enableHeckling = true;
            return dialogResult;
        }

        // get the results. if user presses cancel, exit
        int result = JOptionPane.showConfirmDialog(null, dialogPanel, "New Texas Hold'em Game", JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION) {
            System.exit(0);
        }

        dialogResult.numberOfOpponents = (int) playersComboBox.getSelectedItem();
        dialogResult.userName = nameField.getText();
        dialogResult.timer = timerSpinnerModel.getNumber().intValue();
        dialogResult.imgPath = fileTextField.getText();
        dialogResult.enableHeckling = hecklingCheckBox.isSelected();

        MyTimerTask.setHeckle(dialogResult.enableHeckling);

        if (!timerSpinner.isEnabled()) {
            dialogResult.timer = -1;
        }

        // verify that name field isn't empty. numberOfOpponents will default to 1. Exit if cancel is selected
        if (dialogResult.userName == null || dialogResult.userName.isEmpty()) {
            instructionsLabel.setText("Username must not be empty.");
            return show();
        }

        return dialogResult;
    }

    private void timeCheckboxPressed(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            timerSpinner.setEnabled(true);
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            timerSpinner.setEnabled(false);
        }
        dialogPanel.validate();
        dialogPanel.repaint();
    }

    private void chooseFileButtonPressed(ActionEvent actionEvent) {
        fileChooser.showDialog(dialogPanel, "Select");
        fileTextField.setText(fileChooser.getSelectedFile().getPath());
    }
}
