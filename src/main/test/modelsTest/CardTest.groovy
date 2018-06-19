package modelsTest

import holdem.models.Card

class CardTest extends GroovyTestCase {
    void setUp() {
        super.setUp()
    }

    void tearDown() {
    }

    void testIntToValue() {
        groovy.util.GroovyTestCase.assertEquals(Card.intToValue(2), Card.Value.TWO)
    }

    void testEquals() {
        Card c1 = new Card(Card.Suit.CLUBS, Card.Value.ACE);
        Card c2 = new Card(Card.Suit.CLUBS, Card.Value.ACE);

        groovy.util.GroovyTestCase.assertEquals(c1, c2);
    }
}
