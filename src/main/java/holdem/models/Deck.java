package holdem.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Value v : Card.Value.values()) {
                deck.add(new Card(s, v));
            }
        }

        Collections.shuffle(deck);
    }
    
    public Card dealCard(boolean faceUp) {
        Card c = deck.get(0);
        deck.remove(0);
        c.setFaceUp(faceUp);
        return c;
    }

    public Set<Card> dealCards(int numberOfCards, boolean faceUp) {
        Set<Card> hand = new HashSet<>();
        for (int i = 0; i < numberOfCards; i++)
            hand.add(dealCard(faceUp));
        return hand;
    }

    public Set<Card> dealCards(int numberOfCards) {
        return dealCards(numberOfCards, true);
    }
}
