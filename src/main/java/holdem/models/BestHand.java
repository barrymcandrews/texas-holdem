package holdem.models;

import java.util.HashSet;
import java.util.Set;

public class BestHand {

    public static int findBestHand(Set<Card> playerHand, Set<Card> riverCards) {

        Set<Card> cardsAvailable = new HashSet<>();

        cardsAvailable.addAll(playerHand);
        cardsAvailable.addAll(riverCards);

        //check for straight flush

        for(Card.Suit s : Card.Suit.values()) {
            for(int i = 14; i > 1; i--) {
                Card c = new Card(s, Card.intToValue(i));

            }
        }

        return 7;
    }
}
