package holdem.models;

import holdem.models.Card;

import java.util.HashMap;
import java.util.Map;

public class CardImageMap {
    static Map<Card.Value, String> heartMap = new HashMap<Card.Value, String>() {
        {
            put(Card.Value.TWO, "src/main/resources/cards/2_of_hearts.png");
            put(Card.Value.THREE, "src/main/resources/cards/3_of_hearts.png");
            put(Card.Value.FOUR, "src/main/resources/cards/4_of_hearts.png");
            put(Card.Value.FIVE, "src/main/resources/cards/5_of_hearts.png");
            put(Card.Value.SIX, "src/main/resources/cards/6_of_hearts.png");
            put(Card.Value.SEVEN, "src/main/resources/cards/7_of_hearts.png");
            put(Card.Value.EIGHT, "src/main/resources/cards/8_of_hearts.png");
            put(Card.Value.NINE, "src/main/resources/cards/9_of_hearts.png");
            put(Card.Value.TEN, "src/main/resources/cards/10_of_hearts.png");
            put(Card.Value.JACK, "src/main/resources/cards/jack_of_hearts.png");
            put(Card.Value.QUEEN, "src/main/resources/cards/queen_of_hearts.png");
            put(Card.Value.KING, "src/main/resources/cards/king_of_hearts.png");
            put(Card.Value.ACE, "src/main/resources/cards/ace_of_hearts.png");
        }
    };

    static Map<Card.Value, String> diamondMap = new HashMap<Card.Value, String>() {
        {
            put(Card.Value.TWO, "src/main/resources/cards/2_of_diamonds.png");
            put(Card.Value.THREE, "src/main/resources/cards/3_of_diamonds.png");
            put(Card.Value.FOUR, "src/main/resources/cards/4_of_diamonds.png");
            put(Card.Value.FIVE, "src/main/resources/cards/5_of_diamonds.png");
            put(Card.Value.SIX, "src/main/resources/cards/6_of_diamonds.png");
            put(Card.Value.SEVEN, "src/main/resources/cards/7_of_diamonds.png");
            put(Card.Value.EIGHT, "src/main/resources/cards/8_of_diamonds.png");
            put(Card.Value.NINE, "src/main/resources/cards/9_of_diamonds.png");
            put(Card.Value.TEN, "src/main/resources/cards/10_of_diamonds.png");
            put(Card.Value.JACK, "src/main/resources/cards/jack_of_diamonds.png");
            put(Card.Value.QUEEN, "src/main/resources/cards/queen_of_diamonds.png");
            put(Card.Value.KING, "src/main/resources/cards/king_of_diamonds.png");
            put(Card.Value.ACE, "src/main/resources/cards/ace_of_diamonds.png");
        }
    };

    static Map<Card.Value, String> clubMap = new HashMap<Card.Value, String>() {
        {
            put(Card.Value.TWO, "src/main/resources/cards/2_of_clubs.png");
            put(Card.Value.THREE, "src/main/resources/cards/3_of_clubs.png");
            put(Card.Value.FOUR, "src/main/resources/cards/4_of_clubs.png");
            put(Card.Value.FIVE, "src/main/resources/cards/5_of_clubs.png");
            put(Card.Value.SIX, "src/main/resources/cards/6_of_clubs.png");
            put(Card.Value.SEVEN, "src/main/resources/cards/7_of_clubs.png");
            put(Card.Value.EIGHT, "src/main/resources/cards/8_of_clubs.png");
            put(Card.Value.NINE, "src/main/resources/cards/9_of_clubs.png");
            put(Card.Value.TEN, "src/main/resources/cards/10_of_clubs.png");
            put(Card.Value.JACK, "src/main/resources/cards/jack_of_clubs.png");
            put(Card.Value.QUEEN, "src/main/resources/cards/queen_of_clubs.png");
            put(Card.Value.KING, "src/main/resources/cards/king_of_clubs.png");
            put(Card.Value.ACE, "src/main/resources/cards/ace_of_clubs.png");
        }
    };

    static Map<Card.Value, String> spadeMap = new HashMap<Card.Value, String>() {
        {
            put(Card.Value.TWO, "src/main/resources/cards/2_of_spades.png");
            put(Card.Value.THREE, "src/main/resources/cards/3_of_spades.png");
            put(Card.Value.FOUR, "src/main/resources/cards/4_of_spades.png");
            put(Card.Value.FIVE, "src/main/resources/cards/5_of_spades.png");
            put(Card.Value.SIX, "src/main/resources/cards/6_of_spades.png");
            put(Card.Value.SEVEN, "src/main/resources/cards/7_of_spades.png");
            put(Card.Value.EIGHT, "src/main/resources/cards/8_of_spades.png");
            put(Card.Value.NINE, "src/main/resources/cards/9_of_spades.png");
            put(Card.Value.TEN, "src/main/resources/cards/10_of_spades.png");
            put(Card.Value.JACK, "src/main/resources/cards/jack_of_spades.png");
            put(Card.Value.QUEEN, "src/main/resources/cards/queen_of_spades.png");
            put(Card.Value.KING, "src/main/resources/cards/king_of_spades.png");
            put(Card.Value.ACE, "src/main/resources/cards/ace_of_spades.png");
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
