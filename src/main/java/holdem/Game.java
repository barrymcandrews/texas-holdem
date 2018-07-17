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
    private int pot = 0;
    private int sidePot = 0;
    private int numOpponents;
    private int highestBet;

    private Game() {
        //get start up dialog and info
        StartDialog.DialogResult dialogResult  = new StartDialog().show();
        humanPlayer = new Player(dialogResult.userName, Player.PlayerType.HUMAN);
        numOpponents = dialogResult.numberOfOpponents;

        log.debug("Player Name: " + humanPlayer.getName());
        Constants.AI_NAMES_LIST.forEach((name) -> {
            if (name.equals(humanPlayer.getName())) {
                name = Constants.BACKUP_USERNAME;
            }

            if (players.size() < numOpponents) {
                players.add(new Player(name));
            }
        });
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
     * Flips every card in the game face up
     */
    public void showAllCards() {
        for (Player p : players) {
            for (Card c : p.getHand()) {
                c.setFaceUp(true);
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

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }
    
    public void addToPot(int bet) {
        pot += bet;
    }

    public int getSidePot() {
        return sidePot;
    }

    public void setSidePot(int sidePot) {
        this.sidePot = sidePot;
    }

    public void addToSidePot(int sidePot) {
        this.sidePot += sidePot;
    }
    
    public void clearPot() {
        pot = 0;
    }

    public int getHighestBet() {
        return highestBet;
    }

    public void setHighestBet(int highestBet) {
        this.highestBet = highestBet;
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
        if(players.size() == 1 || humanPlayer.isEliminated()) {
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
}