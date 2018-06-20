package holdem.controllers;

import holdem.Game;
import holdem.models.HumanPlayer;
import holdem.models.TurnResult;

import javax.swing.*;
import java.awt.*;

public class ActionButtonController extends Controller {
    private final HumanPlayer humanPlayer = Game.getInstance().getHumanPlayer();

    public JButton foldButton = new JButton();
    private JButton callButton = new JButton();
    private JButton betButton = new JButton();

    public ActionButtonController() {
        super();
        humanPlayer.setActionButtonController(this);
        setupLayout(getView());
    }

    @Override
    public void setupLayout(JPanel view) {
        foldButton.setText("Fold");
        callButton.setText("Call");
        betButton.setText("Bet");
        view.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        view.add(foldButton);
        view.add(callButton);
        view.add(betButton);
    }
}
