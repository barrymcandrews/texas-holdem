package holdem.controllers;

import holdem.Game;
import holdem.models.Card;
import holdem.models.Player;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SidebarRowController extends Controller {
    private final ImageIcon BACK_OF_CARD = new ImageIcon("src/main/resources/png/back.png");
    private final ImageIcon BOC_SCALED = new ImageIcon(BACK_OF_CARD.getImage().getScaledInstance(30, 45, Image.SCALE_SMOOTH));


    private JLabel nameLabel = new JLabel();
    private JLabel firstCardLabel = new JLabel();
    private JLabel secondCardLabel = new JLabel();
    private JLabel walletLabel = new JLabel();
    private JLabel betLabel = new JLabel();

    private Player player;

    public SidebarRowController(Player player) {
        super();
        this.player = player;
        setupLayout(getView());
    }

    @Override
    void setupLayout(JPanel view) {
        firstCardLabel.setPreferredSize(new Dimension(30, 45));
        secondCardLabel.setPreferredSize(new Dimension(30, 45));
        view.add(nameLabel); 
        view.add(firstCardLabel);
        view.add(secondCardLabel);
        view.add(walletLabel);
        view.add(betLabel);
        view.setPreferredSize(new Dimension(300, 60));
        view.setBackground(Color.RED);
    }

    @Override
    public void reloadData() {
        nameLabel.setText(player.getName());
        if(!Game.getInstance().isEndOfRound()) {
            firstCardLabel.setIcon(BOC_SCALED);
            secondCardLabel.setIcon(BOC_SCALED);
        } else {
           ArrayList<Card> hand = new ArrayList<>();
           hand.addAll(player.getHand());
           firstCardLabel.setIcon(scale(hand.get(0).getFrontOfCard()));
           secondCardLabel.setIcon(scale(hand.get(1).getFrontOfCard()));
        }
        walletLabel.setText("$" + Integer.toString(player.getWallet()));
        betLabel.setText("$0");
    }
    
    private ImageIcon scale(ImageIcon imageIcon) {
        return new ImageIcon(imageIcon.getImage().getScaledInstance(30, 45, Image.SCALE_SMOOTH));
    }
}