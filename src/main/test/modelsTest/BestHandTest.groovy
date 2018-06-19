package modelsTest

import holdem.models.*;

class BestHandTest extends GroovyTestCase {

    void setUp() {
        super.setUp()
    }

    void tearDown() {
    }

    void testFindBestHandStraightFlush() {
        Card c1 = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        Card c2 = new Card(Card.Suit.HEARTS, Card.Value.ACE);

        Card c3 = new Card(Card.Suit.DIAMONDS, Card.Value.ACE);
        Card c4 = new Card(Card.Suit.CLUBS, Card.Value.TEN);
        Card c5 = new Card(Card.Suit.CLUBS, Card.Value.KING);
        Card c6 = new Card(Card.Suit.CLUBS, Card.Value.QUEEN);
        Card c7 = new Card(Card.Suit.CLUBS, Card.Value.JACK);

        Set<Card> hand = new HashSet<>();
        Set<Card> river = new HashSet<>();

        hand.add(c1);
        hand.add(c2);

        river.add(c3);
        river.add(c4);
        river.add(c5);
        river.add(c6);
        river.add(c7);

        groovy.util.GroovyTestCase.assertEquals(new HandScore(9, 14, 0), BestHand.findBestHand(hand, river));
    }

    void testFindBestHandStraightFlushAceLow() {
        Card c1 = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        Card c2 = new Card(Card.Suit.HEARTS, Card.Value.ACE);

        Card c3 = new Card(Card.Suit.DIAMONDS, Card.Value.ACE);
        Card c4 = new Card(Card.Suit.CLUBS, Card.Value.TWO);
        Card c5 = new Card(Card.Suit.CLUBS, Card.Value.THREE);
        Card c6 = new Card(Card.Suit.CLUBS, Card.Value.FOUR);
        Card c7 = new Card(Card.Suit.CLUBS, Card.Value.FIVE);

        Set<Card> hand = new HashSet<>();
        Set<Card> river = new HashSet<>();

        hand.add(c1);
        hand.add(c2);

        river.add(c3);
        river.add(c4);
        river.add(c5);
        river.add(c6);
        river.add(c7);

        groovy.util.GroovyTestCase.assertEquals(new HandScore(9, 5, 0), BestHand.findBestHand(hand, river));
    }

    void testFindBestHandFourOfAKind() {
        Card c1 = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        Card c2 = new Card(Card.Suit.HEARTS, Card.Value.ACE);

        Card c3 = new Card(Card.Suit.DIAMONDS, Card.Value.ACE);
        Card c4 = new Card(Card.Suit.SPADES, Card.Value.ACE);
        Card c5 = new Card(Card.Suit.CLUBS, Card.Value.KING);
        Card c6 = new Card(Card.Suit.CLUBS, Card.Value.QUEEN);
        Card c7 = new Card(Card.Suit.CLUBS, Card.Value.JACK);

        Set<Card> hand = new HashSet<>();
        Set<Card> river = new HashSet<>();

        hand.add(c1);
        hand.add(c2);

        river.add(c3);
        river.add(c4);
        river.add(c5);
        river.add(c6);
        river.add(c7);

        groovy.util.GroovyTestCase.assertEquals(new HandScore(8, 14, 0), BestHand.findBestHand(hand, river));
    }

    void testFindBestHandFullHouse() {
        Card c1 = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        Card c2 = new Card(Card.Suit.HEARTS, Card.Value.ACE);

        Card c3 = new Card(Card.Suit.DIAMONDS, Card.Value.ACE);
        Card c4 = new Card(Card.Suit.SPADES, Card.Value.KING);
        Card c5 = new Card(Card.Suit.CLUBS, Card.Value.KING);
        Card c6 = new Card(Card.Suit.CLUBS, Card.Value.QUEEN);
        Card c7 = new Card(Card.Suit.CLUBS, Card.Value.JACK);

        Set<Card> hand = new HashSet<>();
        Set<Card> river = new HashSet<>();

        hand.add(c1);
        hand.add(c2);

        river.add(c3);
        river.add(c4);
        river.add(c5);
        river.add(c6);
        river.add(c7);

        groovy.util.GroovyTestCase.assertEquals(new HandScore(7, 14, 13), BestHand.findBestHand(hand, river));
    }

    void testFindBestHandFlush() {
        Card c1 = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        Card c2 = new Card(Card.Suit.CLUBS, Card.Value.TWO);

        Card c3 = new Card(Card.Suit.DIAMONDS, Card.Value.ACE);
        Card c4 = new Card(Card.Suit.SPADES, Card.Value.TEN);
        Card c5 = new Card(Card.Suit.CLUBS, Card.Value.KING);
        Card c6 = new Card(Card.Suit.CLUBS, Card.Value.QUEEN);
        Card c7 = new Card(Card.Suit.CLUBS, Card.Value.JACK);

        Set<Card> hand = new HashSet<>();
        Set<Card> river = new HashSet<>();

        hand.add(c1);
        hand.add(c2);

        river.add(c3);
        river.add(c4);
        river.add(c5);
        river.add(c6);
        river.add(c7);

        groovy.util.GroovyTestCase.assertEquals(new HandScore(6, 14), BestHand.findBestHand(hand, river));
    }

    void testFindBestHandStraight() {
        Card c1 = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        Card c2 = new Card(Card.Suit.HEARTS, Card.Value.ACE);

        Card c3 = new Card(Card.Suit.DIAMONDS, Card.Value.ACE);
        Card c4 = new Card(Card.Suit.SPADES, Card.Value.TEN);
        Card c5 = new Card(Card.Suit.CLUBS, Card.Value.KING);
        Card c6 = new Card(Card.Suit.CLUBS, Card.Value.QUEEN);
        Card c7 = new Card(Card.Suit.CLUBS, Card.Value.JACK);

        Set<Card> hand = new HashSet<>();
        Set<Card> river = new HashSet<>();

        hand.add(c1);
        hand.add(c2);

        river.add(c3);
        river.add(c4);
        river.add(c5);
        river.add(c6);
        river.add(c7);

        groovy.util.GroovyTestCase.assertEquals(new HandScore(5, 14), BestHand.findBestHand(hand, river));
    }

    void testFindBestHandThreeOfAKind() {
        Card c1 = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        Card c2 = new Card(Card.Suit.HEARTS, Card.Value.ACE);

        Card c3 = new Card(Card.Suit.DIAMONDS, Card.Value.ACE);
        Card c4 = new Card(Card.Suit.SPADES, Card.Value.TWO);
        Card c5 = new Card(Card.Suit.CLUBS, Card.Value.KING);
        Card c6 = new Card(Card.Suit.CLUBS, Card.Value.QUEEN);
        Card c7 = new Card(Card.Suit.CLUBS, Card.Value.JACK);

        Set<Card> hand = new HashSet<>();
        Set<Card> river = new HashSet<>();

        hand.add(c1);
        hand.add(c2);

        river.add(c3);
        river.add(c4);
        river.add(c5);
        river.add(c6);
        river.add(c7);

        groovy.util.GroovyTestCase.assertEquals(new HandScore(4, 14, 0), BestHand.findBestHand(hand, river));
    }

    void testFindBestHandTwoPair() {
        Card c1 = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        Card c2 = new Card(Card.Suit.HEARTS, Card.Value.ACE);

        Card c3 = new Card(Card.Suit.DIAMONDS, Card.Value.TWO);
        Card c4 = new Card(Card.Suit.SPADES, Card.Value.TWO);
        Card c5 = new Card(Card.Suit.CLUBS, Card.Value.KING);
        Card c6 = new Card(Card.Suit.CLUBS, Card.Value.QUEEN);
        Card c7 = new Card(Card.Suit.CLUBS, Card.Value.JACK);

        Set<Card> hand = new HashSet<>();
        Set<Card> river = new HashSet<>();

        hand.add(c1);
        hand.add(c2);

        river.add(c3);
        river.add(c4);
        river.add(c5);
        river.add(c6);
        river.add(c7);

        groovy.util.GroovyTestCase.assertEquals(new HandScore(3, 14, 2), BestHand.findBestHand(hand, river));
    }

    void testFindBestHandPair() {
        Card c1 = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        Card c2 = new Card(Card.Suit.HEARTS, Card.Value.ACE);

        Card c3 = new Card(Card.Suit.DIAMONDS, Card.Value.THREE);
        Card c4 = new Card(Card.Suit.SPADES, Card.Value.TWO);
        Card c5 = new Card(Card.Suit.CLUBS, Card.Value.KING);
        Card c6 = new Card(Card.Suit.CLUBS, Card.Value.QUEEN);
        Card c7 = new Card(Card.Suit.CLUBS, Card.Value.JACK);

        Set<Card> hand = new HashSet<>();
        Set<Card> river = new HashSet<>();

        hand.add(c1);
        hand.add(c2);

        river.add(c3);
        river.add(c4);
        river.add(c5);
        river.add(c6);
        river.add(c7);

        groovy.util.GroovyTestCase.assertEquals(new HandScore(2, 14, 0), BestHand.findBestHand(hand, river));
    }

    void testFindBestHandHighCard() {
        Card c1 = new Card(Card.Suit.CLUBS, Card.Value.THREE);
        Card c2 = new Card(Card.Suit.HEARTS, Card.Value.FOUR);

        Card c3 = new Card(Card.Suit.DIAMONDS, Card.Value.SEVEN);
        Card c4 = new Card(Card.Suit.SPADES, Card.Value.TWO);
        Card c5 = new Card(Card.Suit.CLUBS, Card.Value.KING);
        Card c6 = new Card(Card.Suit.CLUBS, Card.Value.EIGHT);
        Card c7 = new Card(Card.Suit.CLUBS, Card.Value.JACK);

        Set<Card> hand = new HashSet<>();
        Set<Card> river = new HashSet<>();

        hand.add(c1);
        hand.add(c2);

        river.add(c3);
        river.add(c4);
        river.add(c5);
        river.add(c6);
        river.add(c7);

        groovy.util.GroovyTestCase.assertEquals(new HandScore(1, 13, 0), BestHand.findBestHand(hand, river));
    }

}
