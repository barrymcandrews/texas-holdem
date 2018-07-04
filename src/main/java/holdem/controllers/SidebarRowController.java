package holdem.controllers;

import holdem.Constants;
import holdem.Game;
import holdem.GameWorker;
import holdem.components.RoundedCornerBorder;
import holdem.models.Card;
import holdem.models.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SidebarRowController extends Controller {
    private final Game GAME = Game.getInstance();

    private JLabel tagLabel = new JLabel();
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
        view.setLayout(new GridBagLayout());
        view.setPreferredSize(new Dimension(350, 110));
        view.setBackground(new Color(40, 40, 40));
        view.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(70, 70, 70)));

        GridBagConstraints c = new GridBagConstraints();

        tagLabel.setVisible(false);
        tagLabel.setBorder(new RoundedCornerBorder(Color.white));
        tagLabel.setForeground(Color.white);
        nameLabel.setForeground(Color.white);
        walletLabel.setForeground(Color.white);
        betLabel.setForeground(Color.white);

        c.fill = GridBagConstraints.VERTICAL;


        c.insets = new Insets(4, 10, 0, 8);
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        view.add(nameLabel, c);

        c.insets = new Insets(5, 10, 5, 10);
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0;
        view.add(tagLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.insets = new Insets(0,5,4,5);
        view.add(cardSetController.getView(), c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 2;
        c.weightx = 1;
        view.add(walletLabel, c);

        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 2;
        c.weightx = 1;
        view.add(betLabel, c);
    }

    @Override
    public void reloadData() {
        cardSetController.reloadData();

        if (player == GAME.getDealer()) {
            tagLabel.setText("Dealer");
            tagLabel.setVisible(true);
        } else if (player == GAME.getBigBlind()) {
            tagLabel.setText("Big Blind");
            tagLabel.setVisible(true);
        }else if (player == GAME.getLittleBlind()) {
            tagLabel.setText("Little Blind");
            tagLabel.setVisible(true);
        } else {
            tagLabel.setVisible(false);
        }

        nameLabel.setText(player.getName());
        walletLabel.setText("<html>Wallet:<br>$" + Integer.toString(player.getWallet()) + "</html>");
        if(player.getMove() == GameWorker.Move.BET)
            betLabel.setText("<html>Bet:<br>$" + Integer.toString(player.getMove().getBet()) + "</html>");
        else
            betLabel.setText("<html>Bet:<br>$0</html>");

        if(!player.isActive()) {
            walletLabel.setText("FOLDED");
            betLabel.setVisible(false);
        }
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }
}