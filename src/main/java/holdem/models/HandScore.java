package holdem.models;

public class HandScore {

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
    */

    private int rank;
    private int topCard;
    private int secondCard;

    public HandScore(int rank, int topCard, int secondCard) {
        this.rank = rank;
        this.topCard = topCard;
        this.secondCard = secondCard;
    }

    public HandScore(int rank, int topCard) {
        this.rank = rank;
        this.topCard = topCard;
        this.secondCard = 0;
    }

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
        } else {
            return 0;
        }
    }
}
