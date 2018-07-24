package holdem;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.*;

import holdem.models.Card;
import holdem.models.HandScore;
import holdem.models.Player;

public class WinnerDialog {
    private JPanel dialog = new JPanel();
    private StringBuilder names;
    private StringBuilder hands;
    
    public WinnerDialog(List<Player> winners, HandScore winningHand) {
        names = new StringBuilder();
        hands = new StringBuilder();
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
        
        setUpLayout(winners, winningHand);
       
    }
    
    public void show() {
        JOptionPane.showMessageDialog(null, dialog, names.toString(), JOptionPane.PLAIN_MESSAGE);
    }
    
    private void setUpLayout(List<Player> winners, HandScore winningHand) {
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        int y = 0;
        for (Player p : winners) {
            // add win explantion to top of dialog
            JLabel winExplanation = new JLabel(hands.toString() + ":");
            layout.anchor = GridBagConstraints.CENTER;
            layout.gridx = 0;
            layout.gridy = y;
            layout.fill = GridBagConstraints.NONE;
            dialog.add(winExplanation, layout);

            if (winningHand.getRank() != -1) {
                ArrayList<Card> cards = getAllCards(p);
                ArrayList<Integer> hand = getWinningHand(winningHand, cards);
                Set<ImageIcon> cardImages = convertValueToCard(hand, cards);
                layout.gridy = ++y;
                layout.fill = GridBagConstraints.NONE;
                int x = 1;
                for (ImageIcon img : cardImages) {
                    if(x <= 5) {
                        JLabel label = new JLabel(img);
                        layout.gridx = x;
                        dialog.add(label, layout);
                        x++;
                    }
                }
            }
            y+=2;
        }
    }
    
    private ArrayList<Card> getAllCards(Player p) {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(p.getHand());
        cards.addAll(Game.getInstance().getCenterCards());
        return cards;
    }
    
    private ArrayList<Integer> getWinningHand(HandScore winningHand, ArrayList<Card> cards) {
        ArrayList<Integer> hand = winningHand.getCardValues();
        //if winning hand doesn't have 5 cards, add cards from all available cards until we have a hand of 5
        if(hand.size() != 5) {
            for(int i = hand.size(); i <= 5; i++) {
                if(!hand.contains(cards.get(i).getIntValue()))
                        hand.add(cards.get(i).getIntValue());
            }
        }
        return hand;
    }
    
    private Set<ImageIcon> convertValueToCard(ArrayList<Integer> hand, ArrayList<Card> cards) {
        // convert card value to card image and add all to dialog
        Set<ImageIcon> images = new HashSet<>();
        while(images.size() < 5) {
            for (Integer v : hand) {
                for (Card c : cards) {
                    if (c.getIntValue() == v) 
                        images.add(resize(c.getImageIcon()));
                }
            }
        }
        return images;

    }
    
    private ImageIcon resize(ImageIcon img) {
        Image image = img.getImage(); // transform it 
        Image newimg = image.getScaledInstance(60, 90,  Image.SCALE_SMOOTH); // scale it the smooth way  
        return new ImageIcon(newimg);  // transform it back
    }
}
