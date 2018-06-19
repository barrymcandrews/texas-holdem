package holdem;

import holdem.models.*;
import holdem.models.Player.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Game {
    private static Game gameInstance = new Game();

    private String userName;
    private int numberOfOpponents;

    private ArrayList<Player> players = new ArrayList<>();
    private Player user;

    private Map<String, Player> playersMap = new HashMap<>(); // map player name
                                                              // to object for
                                                              // quick retrieval
    private ArrayList<String> aiNames = new ArrayList<>();
    private Deck deck = new Deck();

    private Game() {
        //get start up dialogue and info
        StartDialogue init  = new StartDialogue().init();
        userName = init.getUserName();
        numberOfOpponents = init.getNumberOfOpponents();
        initializePlayers();
    }

    public Set<Card> deal(int numberOfCards) {
        Set<Card> hand = new HashSet<>();
        for (int i = 0; i < numberOfCards; i++)
            hand.add(deck.dealCard());
        return hand;
    }

    private void initializePlayers() {
        aiNames.addAll(Arrays.asList(
                new String[] { "Bob", "Linda", "Tina", "Gene", "Louise", "Jimmy Jr.", "Teddy", "AndyenOllie" }));
        createPlayer(userName);
        createAI();
    }

    private void createPlayer(String name) {
        // if player name equals an AI name, delete that AI name and use the
        // backup.
        for (int i = 0; i < numberOfOpponents; i++) {
            if (aiNames.get(i).equals(name))
                aiNames.remove(i);
        }
        Player player = new Player(userName, Player.Role.PLAYER, deal(2));
        playersMap.put(player.getName(), player);
        user = player;
    }

    private void createAI() {
        for (int i = 0; i < numberOfOpponents; i++) {
            Player ai;
            if (i == 0) {
                ai = new Player(aiNames.get(i), Role.DEALER, deal(2));
            } else {
                ai = new Player(aiNames.get(i), Role.PLAYER, deal(2));
            }
            playersMap.put(aiNames.get(i), ai);
            players.add(ai);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Player getUser() {
        return user;
    } 

    public void setUser(Player user) {
        this.user = user;
    }

    public static Game getInstance() {
        return gameInstance;
    }

    public String getUserName() {
        return userName;
    }

    public int getNumberOfOpponents() {
        return numberOfOpponents;
    }

    public ArrayList<String> getAiNames() {
        return aiNames;
    }

    public Map<String, Player> getPlayersMap() {
        return playersMap;
    }
    
    public String toString() {
        return "Number of playersMap: " + (numberOfOpponents + 1) + " | Players: " + playersMap.toString();
    }
}