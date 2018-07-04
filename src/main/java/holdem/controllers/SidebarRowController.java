package holdem.controllers;

import holdem.Constants;
import holdem.Game;
import holdem.GameWorker;
import holdem.models.Card;
import holdem.models.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SidebarRowController extends Controller {

    private JLabel nameLabel = new JLabel();
    private CardSetController cardSetController;
    private JLabel walletLabel = new JLabel();
    private JLabel betLabel = new JLabel();

    private Player player;

    public SidebarRowController(Player player) {
        super();
        this.player = player;
        this.cardSetController = new CardSetController(player.getHand(), Constants.SIDEBAR_CARD_DIMENSION);
        setupLayout(getView());
    }

    @Override
    void setupLayout(JPanel view) {
        view.add(nameLabel);
        view.add(cardSetController.getView());
        view.add(walletLabel);
        view.add(betLabel);

        nameLabel.setForeground(Color.white);
        walletLabel.setForeground(Color.white);
        betLabel.setForeground(Color.white);

        view.setPreferredSize(new Dimension(350, 100));
        view.setBackground(new Color(40, 40, 40));
        view.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(70, 70, 70)));
    }

    @Override
    public void reloadData() {
        cardSetController.reloadData();

        nameLabel.setText(player.getName());
        walletLabel.setText("$" + Integer.toString(player.getWallet()));
        betLabel.setText("$" + Integer.toString(player.getMove().getBet()));

        if(!player.isActive()) {
            walletLabel.setText("FOLDED");
            betLabel.setVisible(false);
        } else {
            betLabel.setVisible(true);
        }
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }
}