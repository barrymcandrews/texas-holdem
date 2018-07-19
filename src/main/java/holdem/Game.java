package holdem;

import holdem.models.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class Game {
    private static Logger log = LogManager.getLogger(Game.class);
    private static Game gameInstance = new Game();

    private ArrayList<Player> players = new ArrayList<>();
    private Deck deck = new Deck();
    private Set<Card> centerCards = new HashSet<>();
    private Player humanPlayer;
    private Player dealer;
    private Pot pot;
    private int numOpponents;
    private int highestBet;
    private boolean isHumanPlayersTurn;

    private Game() {
        //get start up dialog and info
        StartDialog.DialogResult dialogResult  = new StartDialog().show();
        humanPlayer = new Player(dialogResult.userName, null, Player.PlayerType.HUMAN);
        numOpponents = dialogResult.numberOfOpponents;
        MyTimerTask.setMyTimer(dialogResult.timer);

        log.debug("Player Name: " + humanPlayer.getName());

        for (int i = 0; i < numOpponents; i++) {
            Player ai = Constants.AI_PLAYERS[i];
            if (ai.getName().equals(humanPlayer.getName())) {
                ai = Constants.BACKUP_AI_PLAYER;
            }
            players.add(ai);
        }

        dealer = players.get(new Random().nextInt(players.size()));
        players.add(humanPlayer);
        highestBet = 0;
        log.debug("AI Players: " + getAIPlayers(humanPlayer).toString());
    }

    public static Game getInstance() {
        return gameInstance;
    }

    /**
     * Deals two cards to each player in the game excluding the dealer.
     */
    public void dealToPlayers() {
        for (Player p : players) {
            if(!p.isEliminated())
                p.setHand(deck.dealCards(2, p == humanPlayer));
        }
    }

    public void shuffleDeck(){
        deck = new Deck();
    }

    /**
     * Deals a number of cards into the center of the table
     *
     * @param numberOfCards number of cards to deal
     */
    public void dealToCenter(int numberOfCards) {
        centerCards.addAll(deck.dealCards(numberOfCards));
    }

    /**
     * Shifts the dealer to the player one person to the right
     */
    public void incrementDealer() {
        int dealerIndex = players.indexOf(dealer);
        dealer = players.get((dealerIndex + 1) % players.size());
    }

    /**
     * Flips every card in the game face up for all active players if player did
     * not win just because everyone else folded.
     */
    public void showAllCards() {
        ArrayList<Player> active = getActivePlayers();
        if(active.size() > 1) {
            for (Player p : active) {
                for (Card c : p.getHand()) {
                    c.setFaceUp(true);
                }
            }
        }
    }

    public Player getBigBlind() {
        return players.get((players.indexOf(dealer) + 1) % players.size());
    }

    public Player getLittleBlind() {
        return players.get((players.indexOf(dealer) + 2) % players.size());
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    public ArrayList<Player> getActivePlayers() {
        ArrayList<Player> active = new ArrayList<>();
        for(Player p : players) {
            if(p.isActive())
                active.add(p);
        }
        return active;
    }

    public ArrayList<Player> getNotEliminatedPlayers() {
        ArrayList<Player> notEliminated = new ArrayList<>();
        for(Player p : players) {
            if(p.isEliminated())
                notEliminated.add(p);
        }
        return notEliminated;
    }
    
    public ArrayList<Player> getPlayerOrder() {
        ArrayList<Player> order = new ArrayList<>();
        int dealerIndex = players.indexOf(getDealer()) + 1;
        for(int i = 0; i < players.size(); i++) 
            order.add(players.get((i + dealerIndex) % players.size()));
        return order;
    }

    public Player getHumanPlayer() {
        return humanPlayer;
    }

    private ArrayList<Player> getAIPlayers(Player humanPlayer) {
        ArrayList<Player> aiList = new ArrayList<Player>(players);
        aiList.remove(humanPlayer);
        return aiList;
    }

    public Set<Card> getCenterCards() {
        return centerCards;
    }
    
    public void clearCenterCards() {
        centerCards.clear();
    }

    public Player getDealer() {
        return dealer;
    }
    
    public void initPot() {
        this.pot = new Pot();
    }
    
    public int getPot() {
        if(pot != null)
            return pot.getTotalPot();
        else
            return 0;
    }

    public void addToPot(Player p, int bet) {
        pot.addBet(p, bet);
    }
    
    public void clearPot() {
        pot.resetPot();
    }

    public int getHighestBet() {
        return highestBet;
    }

    public void setHighestBet(int highestBet) {
        this.highestBet = highestBet;
    }

    public boolean isHumanPlayersTurn() {
        return isHumanPlayersTurn;
    }

    public void setHumanPlayersTurn(boolean isHumanPlayersTurn) {
        this.isHumanPlayersTurn = isHumanPlayersTurn;
    }

    public void checkForEliminated() {
        for (Player p : players) {
            if (p.getWallet() == 0) {
                p.setEliminated(true);
                p.setHand(new HashSet<>(0));
            }
        }
    }

    public void checkForWinner() {
        if(getNotEliminatedPlayers().size() == 1 || humanPlayer.isEliminated()) {
            askForRestart();
        }
    }

    public void askForRestart() {
        RestartDialog restart = new RestartDialog().show();
        if (restart.getRestart() == 1) {
            dealer = players.get(0);
            for (Player p : players) {
                p.setActive(true);
                p.setWallet(1000);
                p.setEliminated(false);
                p.setMove(GameWorker.Move.INVALID);
            }
        } else {
            System.exit(0);
        }
    }

    public void clearPlayerHandBets() {
        for (Player p : players) {
            p.resetHandBet();
        }
    }

    public void clearPlayerRoundBets() {
        for (Player p : players) {
            p.setTotalRoundBet(0);
        }
    }
}