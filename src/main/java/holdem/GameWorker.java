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
            boolean cont = processMove(true, 0, true);
            cont = processMove(cont, 3, false);
            cont = processMove(cont, 1, false);
            cont = processMove(cont, 1, false);
            
            GAME.showAllCards();
            process(null);
            handleWinner();

            GAME.checkForEliminated();
            GAME.incrementDealer();
            GAME.clearCenterCards();
            GAME.clearPot();
            GAME.clearPlayerRoundBets();
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

    private void slowGame() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Handle turns for each betting cycle
     * 
     * @param cards - number of cards to be dealt
     * @returns true if game is to continue
     * @throws Exception
     */
    private boolean processMove(boolean cont, int cards, boolean blinds) throws Exception {
        if(cont) {
            clearHighestBet();
            GAME.clearPlayerHandBets();
            if (blinds) {
                 handleBlinds();
            }
            GAME.dealToCenter(cards);
            process(null);
            slowGame();
            return handleMove();
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
        
        if (GAME.getActivePlayers().size() > 1) {
            for (Player p : GAME.getActivePlayers()) {
                HandScore currBestHand;
                currBestHand = BestHand.findBestHand(p.getHand(), GAME.getCenterCards());
                if (currBestHand.compareTo(bestScore) > 0) {
                    bestScore = currBestHand;
                    winners.clear();
                    winners.add(p);
                } else if (currBestHand.equals(bestScore)) {
                    winners.add(p);
                }
            }
        } else {
            //if only one active player left, they win
            winners = GAME.getActivePlayers();
            bestScore = BestHand.findBestHand(winners.get(0).getHand(), GAME.getCenterCards());
        }
        
        int moneyWon = GAME.getPot() / winners.size();
        for(Player p : winners) 
            p.winMoney(moneyWon);
        
        log.debug(winners.toString() + " Wins: " + moneyWon);
        new WinnerDialog(winners, bestScore).show();

    }
    
    /**
     * Gets move
     * @throws InterruptedException 
     * @returns true if game continues
     */
    private boolean handleMove() throws InterruptedException {
        do {
            for (Player p : GAME.getPlayerOrder()) {

                if (p.isActive()) {
                    GAME.setTurnPlayer(p);
                    GAME.setTurnExplanation(p.getName() + "'s Turn");
                    process(null);

                    if (GAME.getHumanPlayer() != p) {
                        slowGame();
                    }

                    Move move = getMove(p);
                    log.debug(p.getName() + " " + p.getMove().getFriendlyName() + "s");
                    if (move == Move.BET) {
                        int prebet = p.getHandBet();
                        handleBet(p, move);
                        GAME.setHighestBet(p.getHandBet());
                        GAME.addToPot(p.getHandBet() - prebet);
                        p.setMove(move);
                    } else if (move == Move.FOLD) {
                        p.setActive(false);
                        p.setMove(move);
                        if (GAME.getActivePlayers().size() == 1)
                            return false;
                    } else if (move == Move.CALL) {
                        // match highest bet
                        int prebet = p.getHandBet();
                        if (hasEnoughMoney(p, GAME.getHighestBet())) {
                            p.setHandBet(GAME.getHighestBet());
                        } else {
                            p.setHandBet(p.getWallet());
                        }
                        GAME.addToPot(p.getHandBet() - prebet);
                        move.setBet(p.getHandBet());
                        p.setMove(move);
                    }

                    GAME.setTurnExplanation(p.getName() + " " + p.getMove().getFriendlyName() + "s");
                    process(null);
                    slowGame();
                }
            }
        } while(!allPlayersMaxBet());
        return true;
    }
    
    private Move getMove(Player p) throws InterruptedException {
        Move move = null;
        if (p.getType() == PlayerType.AI && p.isActive() && !p.isEliminated()) {
            GAME.setHumanPlayersTurn(false);
            move = p.getRandomMove(GAME.getPlayers());
        } else if (p.getType() == PlayerType.HUMAN) {
            GAME.setHumanPlayersTurn(true);
            process(null);
            if(p.isActive()) 
                move = gameQueue.take(); 
            //else
                //JOptionPane.showMessageDialog(null, "You folded. Skipping turn.");
        }
        return move;
    }
    
    private void handleBet(Player p, Move move) {
        if(p.getType() == PlayerType.AI) {
            if (hasEnoughMoney(p, GAME.getHighestBet() + 10)) {
                p.setHandBet(GAME.getHighestBet() + 10);
            } else if (hasEnoughMoney(p, GAME.getHighestBet())) {
                p.setHandBet(GAME.getHighestBet());
                p.setMove(Move.CALL);
            } else {
                p.setHandBet(p.getWallet());
            }
        } else {
            if (hasEnoughMoney(p, move.getBet())) {
                p.setHandBet(move.getBet());
            } else {
                p.setHandBet(p.getWallet());
            }
        }
    }
    
    private void reactivatePlayers() {
        for(Player player : GAME.getPlayers()) {
            player.setActive(true);
            player.setMove(Move.INVALID);
        }
    }

    private boolean hasEnoughMoney(Player p, int betAmount) {
        if (p.getWallet() < betAmount) {
            return false;
        }
        return true;
    }

    private boolean allPlayersMaxBet() {
        for (Player p : GAME.getPlayers()) {
            if (p.isActive() && p.getHandBet() < GAME.getHighestBet()) {
                return false;
            }
        }
        return true;
    }

    private void clearHighestBet() {
        GAME.setHighestBet(0);
    }

    public void handleBlinds() {
        GAME.getBigBlind().setHandBet(20);
        GAME.getLittleBlind().setHandBet(10);
        GAME.setHighestBet(20);
        GAME.addToPot(30);
    }
    
    public enum Move {
        BET,
        CALL,
        FOLD,
        INVALID;

        int bet;

        public int getBet() {
            return bet;
        }

        public Move setBet(int bet) {
            this.bet = bet;
            return this;
        }

        public String getFriendlyName() {
            String uglyName = toString().toLowerCase();
            if(uglyName.equalsIgnoreCase("call") && this.getBet() == 0)
                uglyName = "check";
            
            return Character.toUpperCase(uglyName.charAt(0)) + uglyName.substring(1);
        }
    }
}
