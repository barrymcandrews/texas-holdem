package holdem.controllers;

import javax.swing.*;

import holdem.Game;
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
    
    @Override
    public void reloadData() {
        if(Game.getInstance().getHumanPlayer().isActive() && Game.getInstance().isHumanPlayersTurn()) {
            foldButton.setEnabled(true);
            callButton.setEnabled(true);
            callButton.setText("Call $" + Game.getInstance().getHighestBet());
            betButton.setEnabled(true);
        } else {
            foldButton.setEnabled(false);
            callButton.setEnabled(false);
            betButton.setEnabled(false);
        }
    }
    
    public void handleBet(BlockingQueue<Move> moveQueue) {
        String bet = getBet();
        Move move = Move.BET;
        if(bet != null) {
            move.setBet(Integer.parseInt(bet));
            moveQueue.add(move);
        }
    }
    
    private String getBet() {
        String bet = JOptionPane.showInputDialog("How much would you like to bet? ");
        Boolean valid = false;
        Game GAME = Game.getInstance();
        int maxBet = GAME.getHumanPlayer().getWallet();
        int minBet = 10;
        if(GAME.getHighestBet() > 0) 
            minBet = GAME.getHighestBet();
        while(!valid) {
            if(bet == null || (bet != null && ("".equals(bet))))
                return null;
            else if(!isNumeric(bet)) 
                bet = JOptionPane.showInputDialog("Invalid bet input. Bet must be a numeric integer.");
            else if(Integer.parseInt(bet) < minBet || Integer.parseInt(bet) > maxBet) 
                bet = JOptionPane.showInputDialog("Invalid bet input. Bet must be between $" + minBet + " and $" + maxBet);
            else
                valid = true;
        }
        Game.getInstance().setHighestBet(Integer.parseInt(bet));
        return bet;
    }
    
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e){
            //ignore it
            return false;
        }
        return true;
    }
}