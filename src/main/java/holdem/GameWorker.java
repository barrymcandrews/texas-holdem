package holdem;

import holdem.controllers.RootController;
import holdem.models.BestHand;
import holdem.models.HandScore;
import holdem.models.Player;
import holdem.models.Player.PlayerType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class GameWorker extends SwingWorker<Void, Game> {
    private final Logger log = LogManager.getLogger(GameWorker.class);
    private final RootController ROOT_CONTROLLER = Application.rootController;
    private final Game GAME = Game.getInstance();

    public static LinkedBlockingQueue<Move> gameQueue = new LinkedBlockingQueue<>();

    @Override
    protected Void doInBackground() throws Exception {
        log.debug("Game thread started.");

        while (true) {
            Player dealer = GAME.getDealer();
            log.debug("Dealer this round: " + dealer.getName());
            GAME.dealToPlayers();
            GAME.setEndOfRound(false);
            process(null);
            slowGame();
            
            gameQueue.clear();
            Move playerMove = gameQueue.take();
            boolean cont = handleMove(playerMove);
            
            cont = processMove(cont, 3);
            cont = processMove(cont, 1);
            cont = processMove(cont, 1);
            
            GAME.setEndOfRound(true);
            process(null);
            handleWinner();
            
            GAME.incrementDealer();
            GAME.clearCenterCards();
            GAME.clearPot();
            GAME.shuffleDeck();
            reactivatePlayers();
            process(null);
        }
    }

    @Override
    protected void process(List<Game> chunks) {
        ROOT_CONTROLLER.reloadData();
    }

    @Override
    protected void done() {
        log.debug("Game thread stopped.");
    }

    private void slowGame() throws Exception {
        Thread.sleep(1000);
    }
    
    /**
     * Handle turns for each betting cycle
     * 
     * @param cards - number of cards to be dealt
     * @returns true if game is to continue
     * @throws Exception
     */
    private boolean processMove(boolean cont, int cards) throws Exception {
        if(cont) {
            GAME.dealToCenter(cards);
            process(null);
            slowGame();
            if(GAME.getHumanPlayer().isActive()) {
                Move playerMove = gameQueue.take();
                return handleMove(playerMove);
            } else {
                if(GAME.getPlayers().size() == 2) {
                    //if there are only two players, folding should immediately end the game and the other player should win
                    return false;
                } else {
                    //No longer get moves from user once they have folded but allow round to finish out normally
                    JOptionPane.showMessageDialog(null, "You folded. Skipping turn.");
                    return true;
                }
            }
        } 
        return false;
    }

    private void handleWinner(){
        HandScore bestScore = new HandScore(0, 0);
        Player winner = null;

        for (Player p : GAME.getPlayers()) {
            if (p.isActive()) {
                HandScore temp;
                temp = BestHand.findBestHand(p.getHand(), GAME.getCenterCards());
                if (temp.compareTo(bestScore) > 0) {
                    bestScore = temp;
                    winner = p;
                }
                // TODO: Handle ties with 2 winners
            }
        }
        winner.winMoney(GAME.getPot());
        JOptionPane.showMessageDialog(null, "Winning hand: " + winner.getHand().toString(), winner.getName() + " wins!", JOptionPane.PLAIN_MESSAGE);
      
    }
    
    /**
     * Gets move, if it is a bet, have all players set to inactive(fold)
     * @param playerMove
     * @returns false if move was a bet, used to stop turns
     */
    private boolean handleMove(Move playerMove) {
        if(playerMove == Move.BET) {
            GAME.addToPot(playerMove.getBet());
            GAME.getHumanPlayer().loseMoney(playerMove.getBet());
            
            for(Player player : GAME.getPlayers()) {
                if(player.getType() == PlayerType.AI) {
                    player.setActive(false);
                }
            } 
            return false;
        }
        if(playerMove == Move.FOLD) {
            GAME.getHumanPlayer().setActive(false);
        }
        return true;
    }
    
    private void reactivatePlayers() {
        for(Player player : GAME.getPlayers()) {
            player.setActive(true);
        }
    }
    
    public enum Move {
        BET(0),
        CALL(0), 
        FOLD(0);
        
        private int bet;
        
        public int getBet() {
            return bet;
        }
        public void setBet(int bet) {
            this.bet = bet;
        }
        
        Move(int bet) {
            this.setBet(bet);
        }   
    }
}
