import holdem.models.HandScore;
import org.junit.Assert;
import org.junit.Test;

public class HandScoreTest {

    @Test
    public void testCompareToGreaterRank() {
        HandScore h1 = new HandScore(2, 1, 1);
        HandScore h2 = new HandScore(1, 1, 1);

        Assert.assertTrue(h1.compareTo(h2) > 0);
    }

    @Test
    public void testCompareToLessThanRank() {
        HandScore h1 = new HandScore(1, 1, 1);
        HandScore h2 = new HandScore(2, 1, 1);

        Assert.assertTrue(h1.compareTo(h2) < 0);
    }

    @Test
    public void testCompareToGreaterTop() {
        HandScore h1 = new HandScore(2, 2, 1);
        HandScore h2 = new HandScore(2, 1, 1);

        Assert.assertTrue(h1.compareTo(h2) > 0);
    }

    @Test
    public void testCompareToLessThanTop() {
        HandScore h1 = new HandScore(2, 1, 1);
        HandScore h2 = new HandScore(2, 2, 1);

        Assert.assertTrue(h1.compareTo(h2) < 0);
    }

    @Test
    public void testCompareToGreaterSecond() {
        HandScore h1 = new HandScore(2, 1, 2);
        HandScore h2 = new HandScore(2, 1, 1);

        Assert.assertTrue(h1.compareTo(h2) > 0);
    }

    @Test
    public void testCompareToLessThanSecond() {
        HandScore h1 = new HandScore(2, 1, 1);
        HandScore h2 = new HandScore(2, 1, 2);

        Assert.assertTrue(h1.compareTo(h2) < 0);
    }

    @Test
    public void testCompareToEqualSecond() {
        HandScore h1 = new HandScore(2, 1, 2);
        HandScore h2 = new HandScore(2, 1, 2);

        Assert.assertEquals(h1.compareTo(h2), 0);
    }
}
