package holdem;

import javax.swing.*;

public class RestartDialogue {

    int restart;

    public RestartDialogue show() {
        // Format pop up panel
        JPanel panel = new JPanel();
        panel.add(new JButton("Yes"));
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JButton("No"));

        // get the results. if user presses cancel, exit
        if (JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Restart?",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            // yes option
            restart = 1;
        } else {
            // no option
            restart = 0;
        }
        return this;
    }

    public int getRestart() {
        return restart;
    }
}
