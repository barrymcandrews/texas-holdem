package holdem;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.*;

import holdem.controllers.CardSetController;
import holdem.models.Card;
import holdem.models.HandScore;
import holdem.models.Player;

public class WinnerDialog {
    private JPanel dialog = new JPanel();
    private StringBuilder names;
    
    public WinnerDialog(List<Player> winners, HandScore winningHand) {
        names = new StringBuilder();
        String AND = " and ";
        String COMMA = ", ";
        if(winners.size() == 1) {
            names.append(winners.get(0).getName() + " wins!");
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
        for (int i = 0; i < winners.size(); i++) {
            // add win explanation to top of dialog
            JLabel winExplanation = new JLabel(winners.get(i).getName() + "'s best hand: " + winningHand.toString());
            layout.anchor = GridBagConstraints.CENTER;
            layout.gridx = 0;
            int y = i*3;
            layout.gridy = y;
            layout.fill = GridBagConstraints.NONE;
            dialog.add(winExplanation, layout);

            if (winningHand.getRank() != -1) {
                Set<Card> hand = getWinningHand(winningHand, winners.get(i));
                CardSetController imgs = new CardSetController(hand, Constants.SIDEBAR_CARD_DIMENSION);
                layout.gridx = 0;
                layout.gridy = ++y;
                imgs.getView().setPreferredSize(new Dimension(60*6 + 60, 110));
                dialog.add(imgs.getView(), layout);
                imgs.reloadData();
            }
        }
    }
    
    private Set<Card> getWinningHand(HandScore winningHand, Player p) {
        ArrayList<Card> cards = getAllCards(p);
        ArrayList<Integer> hand = winningHand.getCardValues();
        //if winning hand doesn't have 5 cards, add cards from all available cards until we have a hand of 5
        if(hand.size() < 5) {
            for(int i = hand.size(); i < 5; i++) {
                if(!hand.contains(cards.get(i).getIntValue()))
                        hand.add(cards.get(i).getIntValue());
            }
        }
        
        return convertValueToCard(hand, cards);
    }
    
    private ArrayList<Card> getAllCards(Player p) {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(p.getHand());
        cards.addAll(Game.getInstance().getCenterCards());
        return cards;
    }
    
    private Set<Card> convertValueToCard(ArrayList<Integer> hand, ArrayList<Card> cards) {
        // convert card value to card image and add all to dialog
        Set<Card> res = new HashSet<>();
        for (Integer v : hand) {
            for (Card c : cards) {
                if (res.size() == 5)
                    return res;
                else if (c.getIntValue() == v)
                    res.add(c);
            }
        }
        return res;

    }
}
