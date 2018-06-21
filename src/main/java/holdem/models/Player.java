package holdem.models;

import java.util.HashSet;
import java.util.Set;

public class Player {

    public PlayerType getType() {
        return type;
    }

    public enum PlayerType { HUMAN, AI }

    private String name;
    private int wallet;
    private Set<Card> hand = new HashSet<>();
    private PlayerType type = PlayerType.AI;
    private boolean isActive = true;

    public Player(String name){
        this.name = name;
        this.wallet = 1000;
    }

    public Player(String name, PlayerType type){
        this.name = name;
        this.wallet = 1000;
        this.type = type;
    }

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
    
    public void winMoney(int money) {
        this.wallet += money;
    }
    
    public void loseMoney(int money) {
        this.wallet -= money;
    }
    
    public Set<Card> getHand() {
        return hand;
    }
    
    public void setHand(Set<Card> cards) {
        hand.clear();
        hand.addAll(cards);
    }
    
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String toString() {
        return "[name: " + name + 
                " | wallet: " + wallet +
                " | hand: " + hand + "]";
    }
}