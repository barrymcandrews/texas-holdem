package holdem.controllers;

import holdem.Constants;
import holdem.Game;
import holdem.components.CircleImage;
import holdem.components.RoundedCornerBorder;
import holdem.components.TagLabel;
import holdem.models.Player;

import javax.swing.*;
import java.awt.*;

public class SidebarRowController extends Controller {
    private final Game GAME = Game.getInstance();

    private CircleImage imageLabel = new CircleImage();
    private TagLabel tagLabel = new TagLabel();
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

        tagLabel.setBorder(new RoundedCornerBorder(Color.white));
        tagLabel.setForeground(Color.white);
        nameLabel.setForeground(Color.white);
        walletLabel.setForeground(Color.white);
        betLabel.setForeground(Color.white);

        if (Constants.DEBUG) {
            tagLabel.setBackground(Color.RED);
            tagLabel.setOpaque(true);

            nameLabel.setBackground(Color.RED);
            nameLabel.setOpaque(true);

            walletLabel.setBackground(Color.RED);
            walletLabel.setOpaque(true);

            betLabel.setBackground(Color.RED);
            betLabel.setOpaque(true);
        }


        // Image Label
        c.insets = new Insets(0, 5, 0, 5);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0;
        c.weightx = 0;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        imageLabel.setImage(player.getImage());
        imageLabel.setPreferredSize(Constants.PLAYER_IMAGE_DIMENSION);
        view.add(imageLabel, c);

        // Name Label
        c.insets = new Insets(0, 5, 5, 5);
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0;
        c.weightx = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        view.add(nameLabel, c);

        // Card Controller
        c.insets = new Insets(0,0,0,0);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 0;
        c.weighty = 0;
        c.weightx = 0;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.VERTICAL;
        cardSetController.getView().setPreferredSize(Constants.SIDEBAR_CARD_CONTROLLER_DIMENSION);
        view.add(cardSetController.getView(), c);

        // Tag Label
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 2;
        c.gridy = 0;
        c.weighty = 0.5;
        c.weightx = 0.5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        tagLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tagLabel.setMinimumSize(Constants.TAG_LABEL_DIMENSION);
        tagLabel.setPreferredSize(Constants.TAG_LABEL_DIMENSION);
        view.add(tagLabel, c);

        // Wallet Label
        c.insets = new Insets(4, 8, 4, 8);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 2;
        c.gridy = 1;
        c.weighty = 0.5;
        c.weightx = 0.5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        walletLabel.setPreferredSize(new Dimension(90, walletLabel.getHeight()));
        view.add(walletLabel, c);

        // Bet Label
        c.insets = new Insets(4, 8, 4, 8);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 2;
        c.gridy = 2;
        c.weighty = 0.5;
        c.weightx = 0.5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        betLabel.setPreferredSize(new Dimension(90, walletLabel.getHeight()));
        view.add(betLabel, c);
    }

    @Override
    public void reloadData() {
        cardSetController.reloadData();

        Color color = (GAME.getTurnPlayer() == player) ? Constants.SIDEBAR_COLOR_HIGHLIGHTED : Constants.SIDEBAR_COLOR;
        getView().setBackground(color);


        tagLabel.updateTagFor(player);
        nameLabel.setText(player.getName());

        walletLabel.setText("<html>Wallet: $" + Integer.toString(player.getWallet()) + "</html>");
        betLabel.setText("<html>Bet: $" + Integer.toString(player.getHandBet()) + "</html>");

        if(!player.isActive()) {
            betLabel.setText("FOLDED");
        }

        if(player.isEliminated()) {
            betLabel.setForeground(Color.red);
            betLabel.setText("ELIMINATED");
        }
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }
}