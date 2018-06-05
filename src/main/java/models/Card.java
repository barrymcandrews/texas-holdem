package models;

import javax.swing.*;

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

    public enum Suit {
        SPADES,
        CLUBS,
        HEARTS,
        DIAMONDS
    }

    public enum Value {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
        TEN, JACK, QUEEN, KING, ACE
    }
}
