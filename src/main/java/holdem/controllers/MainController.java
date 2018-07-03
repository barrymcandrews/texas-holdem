package holdem.controllers;

import holdem.Game;
import holdem.models.Player;

import javax.swing.*;
import java.awt.*;


public class MainController extends Controller {
    private final Game GAME = Game.getInstance();

    private JLabel potLabel = new JLabel();
    private JLabel playerMoney = new JLabel();
    private JLabel playerName = new JLabel();

    private CardSetController deltCards;
    private CardSetController playerCards;
    private ActionButtonController actionButtons;


    public MainController() {
        super();
        deltCards = new CardSetController(GAME.getCenterCards());
        playerCards = new CardSetController(GAME.getHumanPlayer().getHand());
        actionButtons = new ActionButtonController();
        setupLayout(getView());
    }

    @Override
    public void setupLayout(JPanel view) {
        view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));

        deltCards.getView().setAlignmentX(Component.CENTER_ALIGNMENT);
        potLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerCards.getView().setAlignmentX(Component.CENTER_ALIGNMENT);
        playerMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        view.setBackground(Color.white);

        view.add(deltCards.getView());
        view.add(potLabel);
        view.add(playerCards.getView());
        view.add(playerMoney);
        view.add(playerName);
        view.add(actionButtons.getView());
    }

    @Override
    public void reloadData() {
        deltCards.reloadData();
        playerCards.reloadData();
        actionButtons.reloadData();

        Player humanPlayer = GAME.getHumanPlayer();
        potLabel.setText("$" + Integer.toString(GAME.getPot()));

        playerMoney.setText("$" + humanPlayer.getWallet());
        playerName.setText(humanPlayer.getName());
    }
}
