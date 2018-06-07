package holdem.controllers;

import holdem.Game;
import holdem.models.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MainController extends Controller {
    private final Game GAME = Game.getInstance();

    private JLabel potLabel = new JLabel();
    private JLabel playerMoney = new JLabel();
    private JLabel playerName = new JLabel();

    private CardController deltCards;
    private CardController playerCards;


    public MainController() {
        super();
        deltCards = new CardController(GAME.deal(5));
        playerCards = new CardController(GAME.deal(2));
        setupLayout(getView());
        reloadData();
    }

    @Override
    public void setupLayout(JPanel view) {
        view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));

        deltCards.getView().setAlignmentX(Component.CENTER_ALIGNMENT);
        potLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerCards.getView().setAlignmentX(Component.CENTER_ALIGNMENT);
        playerMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerName.setAlignmentX(Component.CENTER_ALIGNMENT);

        view.add(deltCards.getView());
        view.add(potLabel);
        view.add(playerCards.getView());
        view.add(playerMoney);
        view.add(playerName);
        view.setBackground(Color.lightGray);
    }

    @Override
    public void reloadData() {

        potLabel.setText("$0");

        playerMoney.setText("$" + GAME.getUser().getWallet());
        playerName.setText(GAME.getUserName());
    }
}
