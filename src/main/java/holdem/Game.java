package holdem;

import holdem.models.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Game {
    private static Game gameInstance = new Game();

    private ArrayList<Player> players = new ArrayList<>();
    private Player humanPlayer;
    private Deck deck = new Deck();
    private Set<Card> deltCards = new HashSet<>();

    private Game() {
        //get start up dialog and info
        StartDialogue dialog  = new StartDialogue().show();
        humanPlayer = new HumanPlayer(dialog.getUserName());
        Constants.AI_NAMES_LIST.forEach((name) -> {
            if (name.equals(humanPlayer.getName())) {
                name = Constants.BACKUP_USERNAME;
            }
            players.add(new AiPlayer(name));
        });
    }

    public static Game getInstance() {
        return gameInstance;
    }

    public void startGame() {
        int dealerIndex = 0;

        for(int i = 1; i < players.size(); i++) {
            int index = (dealerIndex + i) % players.size();

            Player turnPlayer = players.get(index);
            TurnResult result = turnPlayer.getTurn();
        }
    }

    public Set<Card> deal(int numberOfCards) {
        Set<Card> hand = new HashSet<>();
        for (int i = 0; i < numberOfCards; i++)
            hand.add(deck.dealCard());
        return hand;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getHumanPlayer() {
        return humanPlayer;
    }
}