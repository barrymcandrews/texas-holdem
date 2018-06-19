package holdem.models;

import java.util.HashSet;
import java.util.Set;

public class BestHand {

    public static HandScore findBestHand(Set<Card> playerHand, Set<Card> riverCards) {

        Set<Card> cardsAvailable = new HashSet<>();

        cardsAvailable.addAll(playerHand);
        cardsAvailable.addAll(riverCards);

        int rank = 0;
        int topCard = 0;

        //check for straight flush

        for(Card c : cardsAvailable) {
            int cardValue = c.getValue().getValue();

            //Ace would be low if initial card in a straight
            if (cardValue == 14) {
                cardValue = 1;
            }

            //10 is the lowest starting card of a straight
            if (cardValue <= 10) {
                for (int i = cardValue; i < cardValue + 5; i++) {
                    Card temp = new Card(c.getSuit(), Card.intToValue(i));

                    if (cardsAvailable.contains(temp) == false) {
                        break;
                    } else if (i == cardValue + 4) {
                        //found a straight flush
                        rank = 9;
                        if (cardValue + 4 > topCard) {
                            topCard = cardValue + 4;
                        }
                    }
                }
            }
        }

        if (rank > 0) {
            return new HandScore(rank, topCard);
        }

        return new HandScore(0, 0);
    }
}
