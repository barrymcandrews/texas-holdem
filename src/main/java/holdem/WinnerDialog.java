package holdem;

import java.util.List;

import javax.swing.*;

import holdem.models.HandScore;
import holdem.models.Player;

public class WinnerDialog {
    public WinnerDialog() {
        
    }
    
    public static void show(List<Player> winners, HandScore winningHand) {
        StringBuilder names = new StringBuilder();
        StringBuilder hands = new StringBuilder();
        String AND = " and ";
        String COMMA = ", ";
        if(winners.size() == 1) {
            names.append(winners.get(0).getName() + " wins!");
            hands.append("Winning hand: " + winningHand.toString());
        } else {
            int i = 0;
            for(Player p : winners) {
                if(i == winners.size() - 1) 
                    names.append(p.getName() + " tie!");
                else {
                    if(i == winners.size() - 2)  
                        names.append(p.getName() + AND);
                    else
                        names.append(p.getName() + COMMA);
                }
                hands.append(p.getName() + "'s best hand: " + p.getHand() + "\n");
                i++;
             }
        }
        JOptionPane.showMessageDialog(null, hands.toString(), names.toString(), JOptionPane.PLAIN_MESSAGE);
    }
}
