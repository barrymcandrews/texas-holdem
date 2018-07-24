package holdem.controllers;

import holdem.Constants;
import holdem.Game;
import holdem.GameWorker;
import holdem.MyTimerTask;
import holdem.components.CircleImage;
import holdem.components.RoundedCornerBorder;
import holdem.components.TagLabel;
import holdem.models.Player;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;


public class MainController extends Controller {
    private final Game GAME = Game.getInstance();

    private JLabel potLabel = new JLabel();
    private JLabel sidePotLabel = new JLabel();
    private JLabel playerMoney = new JLabel();
    private JLabel currBetLabel = new JLabel();
    private JLabel playerName = new JLabel();
    private CircleImage playerImage = new CircleImage();
    private TagLabel tagLabel = new TagLabel();
    private JLabel timerLabel = new JLabel();
    private JLabel explanationLabel = new JLabel();
    private JLabel highestBetLabel = new JLabel();

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
        view.setLayout(new GridBagLayout());
        view.setBackground(Color.white);

        explanationLabel.setFont(new Font("Serif", Font.BOLD, 20));
        timerLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        potLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        sidePotLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        highestBetLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        playerMoney.setFont(new Font("Serif", Font.PLAIN, 20));
        currBetLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        playerName.setFont(new Font("Serif", Font.PLAIN, 20));


        GridBagConstraints c = new GridBagConstraints();

        // Timer Label
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        explanationLabel.setHorizontalAlignment(JLabel.CENTER);
        explanationLabel.setPreferredSize(new Dimension(getView().getWidth(), 50));
        explanationLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        view.add(explanationLabel, c);

        // Timer Label
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setPreferredSize(new Dimension(getView().getWidth(), 50));
        timerLabel.setVerticalAlignment(SwingConstants.TOP);
        timerLabel.setText("");
        view.add(timerLabel, c);

        // Dealt Cards
        JPanel deltCardsView = dealtCards.getView();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        view.add(deltCardsView, c);

        // Pot Label
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.NONE;
        view.add(potLabel, c);

        // Side Pot Label
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.NONE;
        view.add(sidePotLabel, c);

        // Highest Bet Label
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.NONE;
        view.add(highestBetLabel, c);

        // Player Cards
        JPanel playerCardsView = playerCards.getView();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        view.add(playerCardsView, c);

        // Player Money
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.NONE;
        view.add(playerMoney, c);
        
        // Player Bet
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.NONE;
        view.add(currBetLabel, c);

        // Player Name
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1;
        c.gridy = 9;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.NONE;
        view.add(playerName, c);

        // Player Image
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0,0,0,10);
        playerImage.setImage(GAME.getHumanPlayer().getImage());
        playerImage.setBorderColor(Color.black);
        view.add(playerImage, c);

        // Tag Label
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0,0,0,0);
        tagLabel.setBorder(new RoundedCornerBorder(Color.black));
        view.add(tagLabel, c);

        // Action Buttons
        JPanel actionButtonPanel = actionButtons.getView();
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        view.add(actionButtonPanel, c);


        Timer timer = new Timer(1000, e -> {
            Player player = GAME.getHumanPlayer();
            if(seconds < 0){
                timerLabel.setText("");
            }
            else if (player.isActive()) {
                long minute = TimeUnit.SECONDS.toMinutes(seconds)
                    - (TimeUnit.SECONDS.toHours(seconds) * 60);
                long second = TimeUnit.SECONDS.toSeconds(seconds)
                    - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
                timerLabel.setText("Time Remaining: " + minute + ":" + second);
                timerLabel.setForeground((minute == 0 && second <= 10) ? Color.RED : Color.BLACK);
                if (seconds == 0) {
                    timerLabel.setText("FOLDED");
                    GameWorker.gameQueue.add(GameWorker.Move.FOLD);
                }
                seconds--;
            }

            else{
                timerLabel.setText("FOLDED");
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
        tagLabel.updateTagFor(GAME.getHumanPlayer());
        explanationLabel.setText(GAME.getTurnExplanation());

        Player humanPlayer = GAME.getHumanPlayer();
        potLabel.setText("Pot: $" + Integer.toString(GAME.getPot()));
        sidePotLabel.setText("Side Pot: $" + Integer.toString(GAME.getSidePot()));
        highestBetLabel.setText("Highest bet: $" + GAME.getHighestBet());

        playerMoney.setText("Wallet: $" + humanPlayer.getWallet());
        currBetLabel.setText("Bet: $" + humanPlayer.getHandBet());
        playerName.setText(humanPlayer.getName());

        if(GAME.getSidePot() == 0)
            sidePotLabel.setVisible(false);
    }
}
