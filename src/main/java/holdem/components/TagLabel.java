package holdem.components;

import holdem.Game;
import holdem.models.Player;

import javax.swing.*;
import java.awt.*;

public class TagLabel extends JLabel {
    private final Game GAME = Game.getInstance();

    public void updateTagFor(Player player) {
        setBorder(new RoundedCornerBorder(getForeground()));
        if (player == GAME.getDealer()) {
            setText("Dealer");
        } else if (player == GAME.getBigBlind()) {
            setText("Big Blind");
            setVisible(true);
        }else if (player == GAME.getLittleBlind()) {
            setText("Little Blind");
        } else {
            setText(" ");
            setBorder(new RoundedCornerBorder(new Color(0,0,0,0)));
        }
    }
}
