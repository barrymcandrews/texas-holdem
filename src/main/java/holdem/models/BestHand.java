package holdem.models;

import java.util.HashSet;
import java.util.Set;

public class BestHand {

    public static HandScore findBestHand(Set<Card> playerHand, Set<Card> riverCards) {

        Set<Card> cardsAvailable = new HashSet<>();

        cardsAvailable.addAll(playerHand);
        cardsAvailable.addAll(riverCards);

        HandScore bestScore;

        bestScore = checkForStraightFlush(cardsAvailable);
        if (bestScore != null) {
            return bestScore;
        }

        int[] cardValTotals = new int[14];
        // [spades, clubs, hearts, diamonds]
        int[] suitTotals = new int[4];

        for (Card c : cardsAvailable) {
            int cardValue = c.getValue().getValue();
            if (cardValue == 14) {
                cardValTotals[0]++;
            }
            cardValTotals[cardValue-1]++;

            if (c.getSuit() == Card.Suit.SPADES) {
                suitTotals[0]++;
            } else if (c.getSuit() == Card.Suit.CLUBS) {
                suitTotals[1]++;
            } else if (c.getSuit() == Card.Suit.HEARTS) {
                suitTotals[2]++;
            } else {
                suitTotals[3]++;
            }
        }

        bestScore = checkForFourOfAKind(cardValTotals);
        if (bestScore != null) {
            return bestScore;
        }

        bestScore = checkForFullHouse(cardValTotals);
        if (bestScore != null) {
            return bestScore;
        }

        bestScore = checkForFlush(cardsAvailable, suitTotals);
        if (bestScore != null) {
            return bestScore;
        }

        bestScore = checkForStraight(cardValTotals);
        if (bestScore != null) {
            return bestScore;
        }

        bestScore = checkForThreeOfAKind(cardValTotals);
        if (bestScore != null) {
            return bestScore;
        }

        bestScore = checkForTwoPair(cardValTotals);
        if (bestScore != null) {
            return bestScore;
        }

        bestScore = checkForPair(cardValTotals);
        if (bestScore != null) {
            return bestScore;
        }

        bestScore = checkForHighCard(cardValTotals);
        if (bestScore != null) {
            return bestScore;
        }

        return new HandScore(0, 0);
    }

    public static HandScore checkForStraightFlush(Set<Card> cardsAvailable) {
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

        //returns if found a straight flush
        if (rank > 0) {
            return new HandScore(rank, topCard);
        }

        return null;
    }

    public static HandScore checkForFourOfAKind(int[] cardValTotals) {
        //checks for four of a kind
        for (int i = 13; i > 0; i--) {
            if (cardValTotals[i] == 4) {
                return new HandScore(8, i+1);
            }
        }
        return null;
    }

    public static HandScore checkForFullHouse(int[] cardValTotals) {
        //checks for full house
        int highestTriple = 0;
        int highestDouble = 0;
        for (int i = 1; i < 14; i++) {
            if (cardValTotals[i] == 3) {
                highestTriple = i+1;
            } else if (cardValTotals[i] == 2) {
                highestDouble = i+1;
            }
        }

        if (highestDouble > 0 && highestTriple > 0) {
            return new HandScore(7, highestTriple, highestDouble);
        }

        return null;
    }

    public static HandScore checkForFlush(Set<Card> cardsAvailable, int[] suitTotals) {
        //checks for four of a kind
        for (int i = 0; i < 4; i++) {
            if (suitTotals[i] == 5) {
                int topCard = 0;
                for (Card c : cardsAvailable) {
                    if (c.getSuit() == Card.intToSuit(i)) {
                        if (c.getValue().getValue() > topCard) {
                            topCard = c.getValue().getValue();
                        }
                    }
                }
                return new HandScore(6, topCard);
            }
        }
        return null;
    }

    public static HandScore checkForStraight(int[] cardValTotals) {

        for (int i = 13; i >= 4; i--) {
            for (int j = i; j > i-5; j--) {
                if (cardValTotals[j] == 0) {
                    break;
                } else if (j == i-4) {
                    return new HandScore(5, i + 1);
                }
            }
        }

        return null;
    }

    public static HandScore checkForThreeOfAKind(int[] cardValTotals) {
        for (int i = 13; i > 0; i--) {
            if (cardValTotals[i] == 3) {
                return new HandScore(4, i+1);
            }
        }
        return null;
    }

    public static HandScore checkForTwoPair(int[] cardValTotals) {
        int topCard = 0;
        for (int i = 13; i > 0; i--) {
            if (cardValTotals[i] == 2) {
                if (topCard > 0) {
                    return new HandScore(3, topCard, i+1);
                } else {
                    topCard = i + 1;
                }

            }
        }
        return null;
    }

    public static HandScore checkForPair(int[] cardValTotals) {
        for (int i = 13; i > 0; i--) {
            if (cardValTotals[i] == 2) {
                return new HandScore(2, i+1);
            }
        }
        return null;
    }

    public static HandScore checkForHighCard(int[] cardValTotals) {
        for (int i = 13; i > 0; i--) {
            if (cardValTotals[i] == 1) {
                return new HandScore(1, i+1);
            }
        }
        return null;
    }
}
