import holdem.models.Card;
import org.junit.Assert;
import org.junit.Test;

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
}
