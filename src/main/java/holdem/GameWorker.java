package holdem;

import holdem.controllers.RootController;
import holdem.models.BestHand;
import holdem.models.HandScore;
import holdem.models.Player;
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
            process(null);
            slowGame();
            
            gameQueue.clear();
            Move playerMove = gameQueue.take();
            handleBet(playerMove);

            GAME.dealToCenter(3);
            process(null);
            slowGame();
            playerMove = gameQueue.take();
            handleBet(playerMove);
            
            GAME.dealToCenter(1);
            process(null);
            slowGame();
            playerMove = gameQueue.take();
            handleBet(playerMove);
            
            GAME.dealToCenter(1);
            process(null);
            slowGame();
            playerMove = gameQueue.take();
            handleBet(playerMove);

            findWinner();
            GAME.incrementDealer();
            GAME.clearCenterCards();
            GAME.clearPot();
            GAME.shuffleDeck();
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

    private Player findWinner(){
        HandScore bestScore = new HandScore(0, 0);
        Player winner = null;

        for(Player p: GAME.getPlayers()){
            HandScore temp;
            temp = BestHand.findBestHand(p.getHand(), GAME.getCenterCards());
            if (temp.compareTo(bestScore) > 0) {
                bestScore = temp;
                winner = p;
            }
            //TODO: Handle ties with 2 winners
        }
        JOptionPane.showMessageDialog(null, winner.getName() + " wins!");
        return winner;
    }
    
    private void handleBet(Move playerMove) {
        if(playerMove == Move.BET) {
            GAME.addToPot(playerMove.getBet());
            GAME.getHumanPlayer().loseMoney(playerMove.getBet());
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
