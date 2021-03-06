package holdem.models;

import java.util.ArrayList;

public class HandScore implements Comparable<HandScore>{

    /*Rankings for a hand are:
    9 - straight flush
    8 - four of a kind
    7 - full house
    6 - flush
    5 - straight
    4 - three of a kind
    3 - two pair
    2 - pair
    1 - high card
    -1 - all players folded
    */

    private int rank;
    private int topCard;
    private int secondCard;
    private int thirdCard;
    private int fourthCard;
    private int fifthCard;

    public HandScore(int rank, int topCard, int secondCard,
                     int thirdCard, int fourthCard, int fifthCard) {
        this.rank = rank;
        this.topCard = topCard;
        this.secondCard = secondCard;
        this.thirdCard = thirdCard;
        this.fourthCard = fourthCard;
        this.fifthCard = fifthCard;
    }

    public HandScore(int rank, int topCard) {
        this.rank = rank;
        this.topCard = topCard;
        this.secondCard = 0;
        this.thirdCard = 0;
        this.fourthCard = 0;
        this.fifthCard = 0;
    }
    
    public HandScore(int rank) {
        this.rank = rank;
        this.topCard = 0;
        this.secondCard = 0;
        this.thirdCard = 0;
        this.fourthCard = 0;
        this.fifthCard = 0;
    }

    @Override
    public int compareTo(HandScore other) {
        if (this.rank < other.rank) {
            return -1;
        } else if (this.rank > other.rank)
        {
            return 1;
        }

        if (this.topCard < other.topCard) {
            return -1;
        } else if (this.topCard > other.topCard) {
            return 1;
        }

        if (this.secondCard < other.secondCard) {
            return -1;
        } else if (this.secondCard > other.secondCard) {
            return 1;
        }

        if (this.thirdCard < other.thirdCard) {
            return -1;
        } else if (this.thirdCard > other.thirdCard) {
            return 1;
        }

        if (this.fourthCard < other.fourthCard) {
            return -1;
        } else if (this.fourthCard > other.fourthCard) {
            return 1;
        }

        if (this.fifthCard < other.fifthCard) {
            return -1;
        } else if (this.fifthCard > other.fifthCard) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HandScore handScore = (HandScore) o;

        if (rank != handScore.rank) return false;
        if (topCard != handScore.topCard) return false;
        return secondCard == handScore.secondCard;
    }

    @Override
    public int hashCode()
    {
        int result = rank;
        result = 31 * result + topCard;
        result = 31 * result + secondCard;
        return result;
    }
    
    public String toString() {
        switch(this.rank) {
        case -1:
            return "All other players folded!";
        case 1:
            return Card.intToValue(this.topCard) + " high";
        case 2:
            return "Pair of " + Card.intToValue(this.topCard) + "'s";
        case 3:
            return "Pair of " + Card.intToValue(this.topCard) + "'s and Pair of " + Card.intToValue(this.secondCard) + "'s";
        case 4:
            return "Three of " + Card.intToValue(this.topCard);
        case 5:
            return "Straight " + Card.intToValue(this.topCard) + "'s";
        case 6:
            return "Flush";
        case 7:
            return "Full house of " + Card.intToValue(this.topCard) + " and " + Card.intToValue(this.secondCard);
        case 8:
            return "Four of " + Card.intToValue(this.topCard);
        case 9:
            return "Straight flush " + " high card " + Card.intToValue(this.topCard);
        default:
            return "";
        }
    }
    
    public int getRank() {
        return this.rank;
    }
    
    public ArrayList<Integer> getCardValues() {
        ArrayList<Integer> hand = new ArrayList<>();
        hand.add(topCard);
        hand.add(secondCard);
        hand.add(thirdCard);
        hand.add(fourthCard);
        hand.add(fifthCard);
        return hand;
    }
}
