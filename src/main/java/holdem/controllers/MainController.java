package holdem.controllers;

import holdem.Constants;
import holdem.Game;
import holdem.components.RoundedCornerBorder;
import holdem.models.Player;

import javax.swing.*;
import java.awt.*;


public class MainController extends Controller {
    private final Game GAME = Game.getInstance();

    private JLabel potLabel = new JLabel();
    private JLabel sidePotLabel = new JLabel();
    private JLabel playerMoney = new JLabel();
    private JLabel playerName = new JLabel();
    private JLabel tagLabel = new JLabel();

    private CardSetController deltCards;
    private CardSetController playerCards;
    private ActionButtonController actionButtons;


    public MainController() {
        super();
        deltCards = new CardSetController(GAME.getCenterCards(), Constants.LARGE_CARD_DIMENSION);
        playerCards = new CardSetController(GAME.getHumanPlayer().getHand());
        actionButtons = new ActionButtonController();
        setupLayout(getView());
    }

    @Override
    public void setupLayout(JPanel view) {
        view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));

        deltCards.getView().setAlignmentX(Component.CENTER_ALIGNMENT);
        potLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePotLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerCards.getView().setAlignmentX(Component.CENTER_ALIGNMENT);
        playerMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        tagLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        view.setBackground(Color.white);

        JPanel deltCardsView = deltCards.getView();
        deltCardsView.setPreferredSize(new Dimension(deltCardsView.getWidth(), 250));

        potLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        sidePotLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        playerMoney.setFont(new Font("Serif", Font.PLAIN, 20));
        playerName.setFont(new Font("Serif", Font.PLAIN, 20));

        JPanel playerCardsView = playerCards.getView();
        playerCardsView.setPreferredSize(new Dimension(playerCardsView.getWidth(), 250));

        tagLabel.setBorder(new RoundedCornerBorder(Color.black));
        tagLabel.setVisible(false);

        view.add(deltCards.getView());
        view.add(potLabel);
        view.add(sidePotLabel);
        view.add(playerCards.getView());
        view.add(playerMoney);
        view.add(playerName);
        view.add(tagLabel);

        view.add(actionButtons.getView());
    }

    @Override
    public void reloadData() {
        deltCards.reloadData();
        playerCards.reloadData();
        actionButtons.reloadData();

        Player player = GAME.getHumanPlayer();
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

        Player humanPlayer = GAME.getHumanPlayer();
        potLabel.setText("Pot: $" + Integer.toString(GAME.getPot()));
        sidePotLabel.setText("Side Pot: $" + Integer.toString(GAME.getSidePot()));

        playerMoney.setText("$" + humanPlayer.getWallet());
        playerName.setText(humanPlayer.getName());

        if(GAME.getSidePot() == 0)
            sidePotLabel.setVisible(false);
    }
}
