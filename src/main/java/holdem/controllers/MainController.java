package holdem.controllers;

import holdem.Constants;
import holdem.Game;
import holdem.GameWorker;
import holdem.MyTimerTask;
import holdem.components.RoundedCornerBorder;
import holdem.models.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;


public class MainController extends Controller {
    private final Game GAME = Game.getInstance();

    private JLabel potLabel = new JLabel();
    private JLabel sidePotLabel = new JLabel();
    private JLabel playerMoney = new JLabel();
    private JLabel playerName = new JLabel();
    private JLabel tagLabel = new JLabel();
    private JLabel timerLabel = new JLabel();

    private CardSetController dealtCards;
    private CardSetController playerCards;
    private ActionButtonController actionButtons;
    private int seconds = MyTimerTask.getMyTimerPeriod();


    public MainController() {
        super();
        dealtCards = new CardSetController(GAME.getCenterCards(), Constants.LARGE_CARD_DIMENSION);
        playerCards = new CardSetController(GAME.getHumanPlayer().getHand());
        actionButtons = new ActionButtonController();
        setupLayout(getView());
    }

    @Override
    public void setupLayout(JPanel view) {
        view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));

        dealtCards.getView().setAlignmentX(Component.CENTER_ALIGNMENT);
        potLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePotLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerCards.getView().setAlignmentX(Component.CENTER_ALIGNMENT);
        playerMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        tagLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timerLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        timerLabel.setAlignmentY(Component.TOP_ALIGNMENT);
        view.setBackground(Color.white);

        JPanel deltCardsView = dealtCards.getView();
        deltCardsView.setPreferredSize(new Dimension(deltCardsView.getWidth(), 250));

        potLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        sidePotLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        playerMoney.setFont(new Font("Serif", Font.PLAIN, 20));
        playerName.setFont(new Font("Serif", Font.PLAIN, 20));
        timerLabel.setFont(new Font("Serif", Font.BOLD, 20));

        JPanel playerCardsView = playerCards.getView();
        playerCardsView.setPreferredSize(new Dimension(playerCardsView.getWidth(), 250));

        tagLabel.setBorder(new RoundedCornerBorder(Color.black));
        tagLabel.setVisible(false);

        view.add(timerLabel);
        view.add(dealtCards.getView());
        view.add(potLabel);
        view.add(sidePotLabel);
        view.add(playerCards.getView());
        view.add(playerMoney);
        view.add(playerName);
        view.add(tagLabel);

        view.add(actionButtons.getView());

        Timer timer = new Timer(1000, e -> {
            seconds--;
            long minute = TimeUnit.SECONDS.toMinutes(seconds)
                - (TimeUnit.SECONDS.toHours(seconds) * 60);
            long second = TimeUnit.SECONDS.toSeconds(seconds)
                - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
            timerLabel.setText(minute + " Minute(s) and " + second + " Second(s)");
            if (seconds == 0) {
                playerName.setText("FOLDED");
                Player player = GAME.getHumanPlayer();
                player.setActive(false);
                reloadData();
            }
        });
        timer.start();
    }

    @Override
    public void reloadData() {
        dealtCards.reloadData();
        playerCards.reloadData();
        actionButtons.reloadData();
        seconds = MyTimerTask.getMyTimerPeriod();

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
