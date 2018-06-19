package holdem.models;

import javax.swing.*;

import static holdem.models.Card.Value.*;

public class Card {
    private Suit suit;
    private Value value;
    private ImageIcon frontOfCard;
    private ImageIcon backOfCard;
    private boolean isVisible;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
        backOfCard = new ImageIcon("resouces/png/back.png");
        frontOfCard = new ImageIcon(CardImageMap.getCard(suit, value));
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

    public void setValue(Value value) {
        this.value = value;
    }

    public ImageIcon getFrontOfCard() {
        return frontOfCard;
    }

    public ImageIcon getBackOfCard() {
        return backOfCard;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (suit != card.suit) return false;
        return value == card.value;
    }

    @Override
    public int hashCode()
    {
        int result = suit.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    public enum Suit {
        SPADES,
        CLUBS,
        HEARTS,
        DIAMONDS
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
    
    public String toString() {
        return "[Suit: " + suit + "Value: " + value + "]";
    }
}
