package holdem;

import java.util.List;

import javax.swing.*;

import holdem.models.Card;
import holdem.models.HandScore;
import holdem.models.Player;

public class WinnerDialog {
    private JPanel dialog = new JPanel();
    private StringBuilder names;
    
    public WinnerDialog(List<Player> winners, HandScore winningHand) {
        names = new StringBuilder();
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
        
        for(int i = 0; i < winners.size(); i++) {
           JLabel hand = new JLabel(hands.toString());
           dialog.add(hand);
           //TODO get cards that make up winning hand and format it correctly
           if(winningHand.getRank() != -1) {
               for(Card c : winningHand.getWinningHand()) 
                   dialog.add(new JLabel(c.getImageIcon()));  
           }
        }
    }
    
    public void show() {
        JOptionPane.showMessageDialog(null, dialog, names.toString(), JOptionPane.PLAIN_MESSAGE);
    }
}
