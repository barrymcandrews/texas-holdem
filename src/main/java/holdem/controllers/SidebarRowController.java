package holdem.controllers;

import holdem.models.Player;
import javax.swing.*;
import java.awt.*;

public class SidebarRowController extends Controller {

    private JLabel nameLabel = new JLabel();
    private JLabel firstCardLabel = new JLabel();
    private JLabel secondCardLabel = new JLabel();
    private JLabel walletLabel = new JLabel();

    private Player player;

    public SidebarRowController(Player player) {
        super();
        this.player = player;
        setupLayout(getView());
        reloadData();
    }

    @Override
    void setupLayout(JPanel view) {
        view.setLayout(new BoxLayout(view, BoxLayout.X_AXIS));
        view.add(nameLabel);
        view.add(firstCardLabel);
        view.add(secondCardLabel);
        view.add(walletLabel);
        view.setPreferredSize(new Dimension(300, 50));
        view.setBackground(Color.RED);
    }

    @Override
    public void reloadData() {
        nameLabel.setText(player.getName());
    }
}
