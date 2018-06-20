package holdem.models;

import holdem.Game;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Player {

    private String name;
    private int wallet;
    private Set<Card> hand = new HashSet<>();

    public Player(String name){
        this.name = name;
        this.wallet = 1000;
    }

    abstract public TurnResult getTurn(Map<Player, TurnResult> previousResults);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }
    
    public Set<Card> getHand() {
        return hand;
    }
    
    public void setHand(Set<Card> cards) {
        hand.clear();
        hand.addAll(cards);
    }
    
    public String toString() {
        return "[name: " + name + 
                " | wallet: " + wallet +
                " | hand: " + hand + "]";
    }
}