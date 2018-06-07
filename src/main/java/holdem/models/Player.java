package holdem.models;

import java.util.Set;

public class Player {

    private String name;
    private Role role;
    private int wallet;
    private Set<Card> hand;

    public Player() {
        this.wallet = 1000;
    }

    public Player(String name, Role role, Set<Card> hand){
        this.name = name;
        this.role = role;
        this.hand = hand;
        this.wallet = 1000;
    }

    public enum Role {
        PLAYER, DEALER
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }
    
    public Set<Card> getCard() {
        return hand;
    }
    
    public void setHand(Set<Card> cards) {
        hand = cards;
    }
    
    public String toString() {
        return "[name: " + name + 
                " | wallet: " + wallet +
                " | role: " + role + 
                " | hand: " + hand + "]";
    }
}