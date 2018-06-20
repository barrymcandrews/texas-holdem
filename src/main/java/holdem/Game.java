package holdem;

import holdem.models.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Game {
    private static Logger log = LogManager.getLogger(Game.class);
    private static Game gameInstance = new Game();

    private ArrayList<Player> players = new ArrayList<>();
    private Deck deck = new Deck();
    private Set<Card> centerCards = new HashSet<>();
    private HumanPlayer humanPlayer;
    private Player dealer;

    private Game() {
        //get start up dialog and info
        StartDialogue dialog  = new StartDialogue().show();
        humanPlayer = new HumanPlayer(dialog.getUserName());
        Constants.AI_NAMES_LIST.forEach((name) -> {
            if (name.equals(humanPlayer.getName())) {
                name = Constants.BACKUP_USERNAME;
            }
            if (players.size() < dialog.getNumberOfOpponents())
            players.add(new AiPlayer(name));
        });
        dealer = players.get(0);
        players.add(humanPlayer);
    }

    public static Game getInstance() {
        return gameInstance;
    }

    /**
     * Deals two cards to each player in the game excluding the dealer.
     */
    public void dealToPlayers() {
        for (Player p : players) {
            if (p != dealer) {
                p.setHand(deck.dealCards(2));
            }
        }
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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public HumanPlayer getHumanPlayer() {
        return humanPlayer;
    }

    public Set<Card> getCenterCards() {
        return centerCards;
    }

    public Player getDealer() {
        return dealer;
    }
}