package holdem;

import javax.swing.*;

public class RestartDialogue {

    int restart;

    public RestartDialogue show() {

        if (JOptionPane.showConfirmDialog(null, "Would you like to play again?", "restart",

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
