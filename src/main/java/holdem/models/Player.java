package holdem.models;

import holdem.Game;

import java.util.Set;

public abstract class Player {

    private String name;
    private int wallet;
    private Set<Card> hand;

    public Player(String name){
        this.name = name;
        this.wallet = 1000;
        getNewCards();
    }

    abstract public TurnResult getTurn();

    public void getNewCards() {
        this.hand = Game.getInstance().deal(2);
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
    
    public Set<Card> getCard() {
        return hand;
    }
    
    public void setHand(Set<Card> cards) {
        hand = cards;
    }
    
    public String toString() {
        return "[name: " + name + 
                " | wallet: " + wallet +
                " | hand: " + hand + "]";
    }
}