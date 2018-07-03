import holdem.models.Card;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CardTest {

    @Test
    public void testIntToValue() {
        Assert.assertEquals(Card.intToValue(2), Card.Value.TWO);
    }

    @Test
    public void testEquals() {
        Card c1 = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        Card c2 = new Card(Card.Suit.CLUBS, Card.Value.ACE);

        Assert.assertEquals(c1, c2);
    }

    @Test
    public void testCompare() {
        Card c1 = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        Card c2 = new Card(Card.Suit.CLUBS, Card.Value.KING);

        Set<Card> hand = new HashSet<>();

        hand.add(c1);
        hand.add(c2);

        List<Card> list = new ArrayList<>();

        list.addAll(hand);
        Collections.sort(list);

        Assert.assertEquals(list.get(0), c2);
    }
}
