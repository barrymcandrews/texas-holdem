package holdem.models;

import holdem.models.Card;

import java.util.HashMap;
import java.util.Map;

public class CardImageMap {
    static Map<Card.Value, String> heartMap = new HashMap<Card.Value, String>() {
        {
            put(Card.Value.TWO, "resources/png/2_of_hearts");
            put(Card.Value.THREE, "resources/png/3_of_hearts");
            put(Card.Value.FOUR, "resources/png/4_of_hearts");
            put(Card.Value.FIVE, "resources/png/5_of_hearts");
            put(Card.Value.SIX, "resources/png/6_of_hearts");
            put(Card.Value.SEVEN, "resources/png/7_of_hearts");
            put(Card.Value.EIGHT, "resources/png/8_of_hearts");
            put(Card.Value.NINE, "resources/png/9_of_hearts");
            put(Card.Value.TEN, "resources/png/10_of_hearts");
            put(Card.Value.JACK, "resources/png/jack_of_hearts");
            put(Card.Value.QUEEN, "resources/png/queen_of_hearts");
            put(Card.Value.KING, "resources/png/king_of_hearts");
            put(Card.Value.ACE, "resources/png/ace_of_hearts");
        }
    };

    static Map<Card.Value, String> diamondMap = new HashMap<Card.Value, String>() {
        {
            put(Card.Value.TWO, "resources/png/2_of_diamonds");
            put(Card.Value.THREE, "resources/png/3_of_diamonds");
            put(Card.Value.FOUR, "resources/png/4_of_diamonds");
            put(Card.Value.FIVE, "resources/png/5_of_diamonds");
            put(Card.Value.SIX, "resources/png/6_of_diamonds");
            put(Card.Value.SEVEN, "resources/png/7_of_diamonds");
            put(Card.Value.EIGHT, "resources/png/8_of_diamonds");
            put(Card.Value.NINE, "resources/png/9_of_diamonds");
            put(Card.Value.TEN, "resources/png/10_of_diamonds");
            put(Card.Value.JACK, "resources/png/jack_of_diamonds");
            put(Card.Value.QUEEN, "resources/png/queen_of_diamonds");
            put(Card.Value.KING, "resources/png/king_of_diamonds");
            put(Card.Value.ACE, "resources/png/ace_of_diamonds");
        }
    };

    static Map<Card.Value, String> clubMap = new HashMap<Card.Value, String>() {
        {
            put(Card.Value.TWO, "resources/png/2_of_clubs");
            put(Card.Value.THREE, "resources/png/3_of_clubs");
            put(Card.Value.FOUR, "resources/png/4_of_clubs");
            put(Card.Value.FIVE, "resources/png/5_of_clubs");
            put(Card.Value.SIX, "resources/png/6_of_clubs");
            put(Card.Value.SEVEN, "resources/png/7_of_clubs");
            put(Card.Value.EIGHT, "resources/png/8_of_clubs");
            put(Card.Value.NINE, "resources/png/9_of_clubs");
            put(Card.Value.TEN, "resources/png/10_of_clubs");
            put(Card.Value.JACK, "resources/png/jack_of_clubs");
            put(Card.Value.QUEEN, "resources/png/queen_of_clubs");
            put(Card.Value.KING, "resources/png/king_of_clubs");
            put(Card.Value.ACE, "resources/png/ace_of_clubs");
        }
    };

    static Map<Card.Value, String> spadeMap = new HashMap<Card.Value, String>() {
        {
            put(Card.Value.TWO, "resources/png/2_of_spades");
            put(Card.Value.THREE, "resources/png/3_of_spades");
            put(Card.Value.FOUR, "resources/png/4_of_spades");
            put(Card.Value.FIVE, "resources/png/5_of_spades");
            put(Card.Value.SIX, "resources/png/6_of_spades");
            put(Card.Value.SEVEN, "resources/png/7_of_spades");
            put(Card.Value.EIGHT, "resources/png/8_of_spades");
            put(Card.Value.NINE, "resources/png/9_of_spades");
            put(Card.Value.TEN, "resources/png/10_of_spades");
            put(Card.Value.JACK, "resources/png/jack_of_spades");
            put(Card.Value.QUEEN, "resources/png/queen_of_spades");
            put(Card.Value.KING, "resources/png/king_of_spades");
            put(Card.Value.ACE, "resources/png/ace_of_spades");
        }
    };

    public static String getCard(Card.Suit s, Card.Value v) {
        if (s == Card.Suit.HEARTS) {
            return heartMap.get(v);
        } else if (s == Card.Suit.DIAMONDS) {
            return diamondMap.get(v);
        } else if (s == Card.Suit.CLUBS) {
            return clubMap.get(v);
        } else {
            return spadeMap.get(v);
        }
    }
}
