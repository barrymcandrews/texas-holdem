package holdem.models;

import java.util.ArrayList;
import java.util.Collections;

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
    
    public Card dealCard() {
        Card c = deck.get(0);
        deck.remove(0);
        return c;
    }
}
