package holdem;

import holdem.models.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.HashMap;
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

    public void startGame() {
        while (true) {
            log.debug("Dealer this round: " + dealer.getName());
            dealToPlayers(dealer);
            int[] dealAmounts = { 3, 1, 1 };

            HashMap<Player, TurnResult> results = new HashMap<>();
            results = getTurnMoves(results);
            for (int dealAmount : dealAmounts) {
                centerCards.addAll(deck.dealCards(dealAmount));
                results = getTurnMoves(results);
            }


            incrementDealer();
        }
    }

    public HashMap<Player, TurnResult> getTurnMoves(HashMap<Player, TurnResult> previousMoves) {
        for (int i = 1; i < players.size(); i++) {
            int dealerIndex = players.indexOf(dealer);
            int index = (dealerIndex + i) % players.size();
            Player turnPlayer = players.get(index);
            // log.debug(turnPlayer.getName() + "'s Turn");

            TurnResult result = turnPlayer.getTurn(previousMoves);
            previousMoves.put(turnPlayer, result);
            log.debug(turnPlayer.getName() + ": " + result.toString());
        }
        return previousMoves;
    }

    /**
     * Deals two cards to each player in the game excluding the dealer.
     *
     * @param dealer player that does not get two cards
     */
    private void dealToPlayers(Player dealer) {
        for (Player p : players) {
            if (p != dealer) {
                p.setHand(deck.dealCards(2));
            }
        }
    }

    /**
     * Shifts the dealer to the player one person to the right
     */
    private void incrementDealer() {
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
}