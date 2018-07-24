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
        GAME.initPot();
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
        ArrayList<Player> winners = new ArrayList<>();

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
        
        int[] moneyWon = GAME.getPot(winners);
        int i = 0;
        for(Player p : winners) {
            p.winMoney(moneyWon[i]);
            i++;
        }

        log.debug(winners.toString() + " Wins: " + moneyWon);
        
        ArrayList<Player> leftoverWinners = new ArrayList<>();
        while (!GAME.checkPotEmpty()){
            bestScore = new HandScore(0, 0);
            ArrayList<Player> tempPlayers = GAME.getActivePlayers();
            tempPlayers.removeAll(winners);
            for (Player p : tempPlayers) {
                HandScore currBestHand;
                currBestHand = BestHand.findBestHand(p.getHand(), GAME.getCenterCards());
                if (currBestHand.compareTo(bestScore) > 0) {
                    bestScore = currBestHand;
                    leftoverWinners.clear();
                    leftoverWinners.add(p);
                } else if (currBestHand.equals(bestScore)) {
                    leftoverWinners.add(p);
                }
            }

            moneyWon = GAME.getPot(leftoverWinners);
            i = 0;
            for(Player p : leftoverWinners) {
                p.winMoney(moneyWon[i]);
                i++;
            }
        }
        
        log.debug(leftoverWinners.toString() + "Wins: " + moneyWon);
        displayWinner(winners, bestScore);

    }
    
    private void displayWinner(List<Player> winners, HandScore winningHand) {
        StringBuilder names = new StringBuilder();
        StringBuilder hands = new StringBuilder();
        String AND = " and ";
        String COMMA = ", ";
        if(winners.size() == 1) {
            names.append(winners.get(0).getName() + " wins!");
            hands.append("Winning hand: " + winningHand.toString());
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
     * Gets move
     * @throws InterruptedException 
     * @returns true if game continues
     */
    private boolean handleMove() throws InterruptedException {
        do {
            for (Player p : GAME.getPlayerOrder()) {

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
                    if (p.getHandBet() > GAME.getHighestBet()) {
                        GAME.setHighestBet(p.getHandBet());
                    }
                    GAME.addToPot(p, p.getHandBet() - prebet);
                    p.setMove(move);
                } else if (move == Move.FOLD) {
                    p.setActive(false);
                    p.setMove(move);
                    if (GAME.getActivePlayers().size() == 1)
                        return false;
                } else if (move == Move.CALL) {
                    // match highest bet
                    int prebet = p.getHandBet();
                    p.setHandBet(GAME.getHighestBet());
                    GAME.addToPot(p, p.getHandBet() - prebet);
                    p.setMove(move);
                }

                GAME.setTurnExplanation(p.getName() + " " + p.getMove().getFriendlyName() + "s");
                process(null);
                slowGame();
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
            p.setHandBet(GAME.getHighestBet() + 10);
        } else {
            p.setHandBet(move.getBet());
        }
    }
    
    private void reactivatePlayers() {
        for(Player player : GAME.getPlayers()) {
            player.setActive(true);
            player.setMove(Move.INVALID);
        }
    }

    private boolean hasEnoughMoney(Player p, int betAmount) {
        if (p.getWallet() < betAmount - p.getHandBet()) {
            return false;
        }
        return true;
    }

    private boolean allPlayersMaxBet() {
        for (Player p : GAME.getPlayers()) {
            if (p.isActive() && (p.getHandBet() < GAME.getHighestBet())) {
                if (p.getWallet() != 0) {
                    return false;
                }
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
        GAME.addToPot(GAME.getBigBlind(), 20);
        GAME.addToPot(GAME.getLittleBlind(), 10);
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

        public void setBet(int bet) {
            this.bet = bet;
        }

        public String getFriendlyName() {
            String uglyName = toString().toLowerCase();
            return Character.toUpperCase(uglyName.charAt(0)) + uglyName.substring(1);
        }
    }
}
