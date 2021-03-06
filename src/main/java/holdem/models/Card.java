package holdem.models;
import holdem.Constants;
import javax.swing.*;

import static holdem.models.Card.Value.*;

public class Card implements Comparable<Card> {
    private Suit suit;
    private Value value;
    private ImageIcon imageIcon;
    private boolean faceUp = true;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
        imageIcon = new ImageIcon(CardImageMap.getCard(suit, value));
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Value getValue() {
        return value;
    }
    
    public int getIntValue() {
        return value.getValue();
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public ImageIcon getImageIcon() {
        return faceUp ? imageIcon : Constants.BACK_OF_CARD_IMAGE;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public static Value intToValue(int v) {
        switch(v) {
            case 1: return ACE;
            case 2: return TWO;
            case 3: return THREE;
            case 4: return FOUR;
            case 5: return FIVE;
            case 6: return SIX;
            case 7: return SEVEN;
            case 8: return EIGHT;
            case 9: return NINE;
            case 10: return TEN;
            case 11: return JACK;
            case 12: return QUEEN;
            case 13: return KING;
            case 14: return ACE;
            default : return null;
        }
    }

    public static Suit intToSuit(int v) {
        switch(v) {
            case 0: return Suit.SPADES;
            case 1: return Suit.CLUBS;
            case 2: return Suit.HEARTS;
            case 3: return Suit.DIAMONDS;
            default : return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (suit != card.suit) return false;
        return value == card.value;
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    public enum Suit {
        SPADES(0),
        CLUBS(1),
        HEARTS(2),
        DIAMONDS(3);

        private int suitVal;

        public int getSuitVal() {
            return suitVal;
        }

        Suit (int value) {
            this.suitVal = value;
        }
    }

    public enum Value {
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13),
        ACE(14);

        private int value;

        public int getValue()
        {
            return value;
        }

        Value(int value)
        {
            this.value = value;
        }
    }

    @Override
    public int compareTo(Card o) {
        if (this.getValue().getValue() < o.getValue().getValue()) {
            return -1;
        } else if (this.getValue().getValue() > o.getValue().getValue()) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toString() {
        return value + "|" + suit;
    }
}
