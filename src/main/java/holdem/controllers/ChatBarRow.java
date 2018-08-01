package holdem.controllers;

import holdem.Constants;
import holdem.components.CircleImage;
import holdem.models.Player;

import javax.swing.*;
import java.awt.*;


public class ChatBarRow extends Controller {

     private CircleImage imageLabel = new CircleImage();
     private JLabel nameLabel = new JLabel();
     private JLabel messageLabel = new JLabel();

     private Player player;
     private String message;

    public ChatBarRow(Player player, String message) {
        super();
        this.player = player;
        this.message = message;
        setupLayout(getView());
    }

    @Override
    void setupLayout(JPanel view) {
        view.setLayout(new GridBagLayout());
        view.setBackground(new Color(0xF0F0F0));

        GridBagConstraints c = new GridBagConstraints();

        Font roboto = Constants.ROBOTO_FONT.deriveFont(14f);
        Font robotoBold = Constants.ROBOTO_FONT.deriveFont(Font.BOLD, 14f);

        // Image Label
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.5;
        c.weightx = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        imageLabel.setImage(player.getImage());
        imageLabel.setPreferredSize(Constants.PLAYER_IMAGE_DIMENSION);
        imageLabel.setBorderColor(Color.black);
        view.add(imageLabel, c);

        // Name Label
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 0;
        c.weighty = 0.5;
        c.weightx = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        nameLabel.setText("<html>" + player.getName() + "</html>");
        nameLabel.setFont(robotoBold);
        view.add(nameLabel, c);

        // Message Label
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 2;
        c.gridy = 0;
        c.weighty = 0.5;
        c.weightx = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        messageLabel.setText("<html>" + message + "</html>");
        messageLabel.setFont(roboto);
        view.add(messageLabel, c);

        Dimension d = new Dimension(350, Math.max(60, messageLabel.getHeight() + 10));
        view.setMinimumSize(d);
        view.setMaximumSize(d);
        view.setPreferredSize(d);

        if (Constants.DEBUG) {
            view.setBackground(Color.blue);
            nameLabel.setBackground(Color.red);
            nameLabel.setOpaque(true);
            messageLabel.setBackground(Color.red);
            messageLabel.setOpaque(true);
        }
    }

    @Override
    public void reloadData() {
        super.reloadData();
    }
}
