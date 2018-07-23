package holdem;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;
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
        
//        JLabel nameLabel = new JLabel("Your Name:");
//        c.anchor = LINE_END;
//        c.gridx = 0;
//        c.gridy = 1;
//        c.gridwidth = 1;
//        c.fill = GridBagConstraints.NONE;
//        dialogPanel.add(nameLabel, c);
        
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        for(Player p : winners) {
           JLabel hand = new JLabel(hands.toString() + ":");
           layout.anchor = GridBagConstraints.CENTER;
           layout.gridx = 0;
           layout.gridy = 0;
           layout.fill = GridBagConstraints.NONE;
           dialog.add(hand, layout);
           //TODO format cards correctly
           ArrayList<Card> cards = new ArrayList<>();
           cards.addAll(p.getHand());
           cards.addAll(Game.getInstance().getCenterCards());
           if(winningHand.getRank() != -1) {
               layout.gridy = 1;
               layout.fill = GridBagConstraints.NONE;
               int x = 1;
               for(Integer v : winningHand.getCardValues()) 
                   for(Card c : cards) {
                       if(c.getIntValue() == v) {
                           JLabel label = new JLabel(resize(c.getImageIcon()));
                           layout.gridx = x;
                           dialog.add(label, layout);
                           x++;
                       }
                   }
           }
        }
    }
    
    public void show() {
        JOptionPane.showMessageDialog(null, dialog, names.toString(), JOptionPane.PLAIN_MESSAGE);
    }
    
    private ImageIcon resize(ImageIcon img) {
        Image image = img.getImage(); // transform it 
        Image newimg = image.getScaledInstance(60, 90,  Image.SCALE_SMOOTH); // scale it the smooth way  
        return new ImageIcon(newimg);  // transform it back
    }
}
