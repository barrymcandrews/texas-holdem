package holdem;

import holdem.controllers.RootController;
import holdem.models.BestHand;
import holdem.models.HandScore;
import holdem.models.Player;
import holdem.models.Player.PlayerType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class GameWorker extends SwingWorker<Void, Game> {
    private final Logger log = LogManager.getLogger(GameWorker.class);
    private final RootController ROOT_CONTROLLER = Application.rootController;
    private final Game GAME = Game.getInstance();

    public static LinkedBlockingQueue<Move> gameQueue = new LinkedBlockingQueue<>();

    @Override
    protected Void doInBackground() throws Exception {

        log.debug("Game thread started: " + LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        while (true) {
            Player dealer = GAME.getDealer();
            log.debug("Dealer this round: " + dealer.getName());
            GAME.dealToPlayers();
            process(null);
            slowGame();
            
            gameQueue.clear();
            Move playerMove = gameQueue.take();
            boolean cont = handleMove(playerMove);
            handleAIMove();
            
            cont = processMove(cont, 3);
            cont = processMove(cont, 1);
            cont = processMove(cont, 1);
            
            GAME.showAllCards();
            process(null);
            handleWinner();

            GAME.checkForEliminated();
            GAME.incrementDealer();
            GAME.clearCenterCards();
            GAME.clearPot();
            GAME.shuffleDeck();
            GAME.checkForWinner();
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
                Player p = GAME.getHumanPlayer();
                log.debug(p.getName() +" "+ playerMove.toString() +"'s");
                handleMove(playerMove);
            } else {
                if(GAME.getPlayers().size() == 2) {
                    //if there are only two players, folding should immediately end the game and the other player should win
                    return false;
                } else {
                    //No longer get moves from user once they have folded but allow round to finish out normally
                    JOptionPane.showMessageDialog(null, "You folded. Skipping turn.");
                }
            }
            handleAIMove();
            return true;
        } 
        return false;
    }

    private void handleWinner(){
        HandScore bestScore = new HandScore(0, 0);
        List<Player> winners = new ArrayList<>();

        log.debug("Cards Dealt: ");
        for (Player p : GAME.getPlayers()) {
            log.debug(p.getName() +": "+ p.getHand().toString());
        }
        
        for (Player p : GAME.getPlayers()) {
            if (p.isActive()) {
                HandScore temp;
                temp = BestHand.findBestHand(p.getHand(), GAME.getCenterCards());
                if (temp.compareTo(bestScore) > 0) {
                    bestScore = temp;
                    winners.clear();
                    winners.add(p);
                } else if(temp.equals(bestScore)) {
                    winners.add(p);
                }
            }
        }
        int moneyWon = GAME.getPot() / winners.size();
        for(Player p : winners) 
            p.winMoney(moneyWon);
        
        log.debug(winners.toString() + " Wins: " + moneyWon);
        displayWinner(winners);

    }
    
    private void displayWinner(List<Player> winners) {
        StringBuilder names = new StringBuilder();
        StringBuilder hands = new StringBuilder();
        String AND = " and ";
        String COMMA = ", ";
        if(winners.size() == 1) {
            names.append(winners.get(0).getName() + " wins!");
            hands.append("Winning hand: " + winners.get(0).getHand());
        } else {
            int i = 0;
            for(Player p : winners) {
                if(i == winners.size() - 1) 
                    names.append(p.getName() + " tie!");
                else {
                    if(i == winners.size() - 2)  
                        names.append(p.getName() + AND);
                    else
                        names.append(p.getName() + COMMA);
                }
                hands.append(p.getName() + "'s best hand: " + p.getHand() + "\n");
                i++;
             }
        }
        JOptionPane.showMessageDialog(null, hands.toString(), names.toString(), JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Gets move, if it is a bet, have all players set to inactive(fold)
     * @param playerMove
     * @returns true if game continues
     */
    private boolean handleMove(Move playerMove) {
        if(playerMove == Move.BET) {
            GAME.addToPot(playerMove.getBet());
            GAME.getHumanPlayer().loseMoney(playerMove.getBet());
        } else if(playerMove == Move.FOLD) {
            GAME.getHumanPlayer().setActive(false);
        }  else if(playerMove == Move.CALL){
            //match highest bet
            playerMove.setBet(GAME.getHighestBet());
            GAME.addToPot(playerMove.getBet());
            GAME.getHumanPlayer().loseMoney(playerMove.getBet());
        }
        return true;
    }

    private boolean handleAIMove() {
        for(Player p : GAME.getPlayers()) {
            if(p.getType() == PlayerType.AI && p.isActive()) {
                Move move = p.getRandomMove(GAME.getPlayers());
                log.debug(p.getName() +" "+ p.getMove().toString() +"'s");
                //raise current bet $10
                if(move== Move.BET) {
                    move.setBet(GAME.getHighestBet() + 10);
                    GAME.setHighestBet(move.getBet());
                    GAME.addToPot(move.getBet());
                    p.loseMoney(move.getBet());
                } else if(move == Move.FOLD) {
                   p.setActive(false);
                   if(GAME.getPlayers().size() == 2)
                       return false;
                }
                else if(move == Move.CALL){
                    //match highest bet
                    move.setBet(GAME.getHighestBet());
                    GAME.addToPot(move.getBet());
                    p.loseMoney(move.getBet());
                }
            }
        }
        return true;
    }
    
    private void reactivatePlayers() {
        for(Player player : GAME.getPlayers()) {
            player.setActive(true);
            player.setMove(Move.INVALID);
        }
    }
    
    public enum Move {
        BET(0),
        CALL(0), 
        FOLD(0),
        INVALID(0);

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
