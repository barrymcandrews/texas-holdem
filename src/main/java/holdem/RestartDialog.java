package holdem;

import javax.swing.*;

public class RestartDialog {

    int restart;

    public RestartDialog show() {

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
