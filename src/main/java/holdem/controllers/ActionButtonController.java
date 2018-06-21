package holdem.controllers;

import javax.swing.*;

import holdem.GameWorker;
import holdem.GameWorker.Move;

import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class ActionButtonController extends Controller {
    public JButton foldButton = new JButton();
    private JButton callButton = new JButton();
    private JButton betButton = new JButton();

    public ActionButtonController() {
        super();
        setupLayout(getView());
    }

    @Override
    public void setupLayout(JPanel view) {
        foldButton.setText("Fold");
        callButton.setText("Call");
        betButton.setText("Bet");
        
        BlockingQueue<Move> moveQueue = GameWorker.gameQueue;
        foldButton.addActionListener((e) -> moveQueue.add(Move.FOLD));
        callButton.addActionListener((e) -> moveQueue.add(Move.CALL));
        betButton.addActionListener((e) -> handleBet(moveQueue));
        
        view.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));  
        view.add(foldButton);
        view.add(callButton);
        view.add(betButton);
    }
    
    public void handleBet(BlockingQueue<Move> moveQueue) {
        String bet = JOptionPane.showInputDialog("How much would you like to bet? ");
        Move move = Move.BET;
        move.setBet(Integer.parseInt(bet));
        moveQueue.add(move);
    }
}
