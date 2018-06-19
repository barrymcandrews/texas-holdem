package modelsTest

import holdem.models.HandScore

class HandScoreTest extends GroovyTestCase {
    void setUp() {
        super.setUp()
    }

    void tearDown() {
    }

    void testCompareToGreaterRank() {
        HandScore h1 = new HandScore(2, 1, 1);
        HandScore h2 = new HandScore(1, 1, 1);

        junit.framework.TestCase.assertTrue(h1.compareTo(h2) > 0);
    }

    void testCompareToLessThanRank() {
        HandScore h1 = new HandScore(1, 1, 1);
        HandScore h2 = new HandScore(2, 1, 1);

        junit.framework.TestCase.assertTrue(h1.compareTo(h2) < 0);
    }

    void testCompareToGreaterTop() {
        HandScore h1 = new HandScore(2, 2, 1);
        HandScore h2 = new HandScore(2, 1, 1);

        junit.framework.TestCase.assertTrue(h1.compareTo(h2) > 0);
    }

    void testCompareToLessThanTop() {
        HandScore h1 = new HandScore(2, 1, 1);
        HandScore h2 = new HandScore(2, 2, 1);

        junit.framework.TestCase.assertTrue(h1.compareTo(h2) < 0);
    }

    void testCompareToGreaterSecond() {
        HandScore h1 = new HandScore(2, 1, 2);
        HandScore h2 = new HandScore(2, 1, 1);

        junit.framework.TestCase.assertTrue(h1.compareTo(h2) > 0);
    }

    void testCompareToLessThanSecond() {
        HandScore h1 = new HandScore(2, 1, 1);
        HandScore h2 = new HandScore(2, 1, 2);

        junit.framework.TestCase.assertTrue(h1.compareTo(h2) < 0);
    }

    void testCompareToEqualSecond() {
        HandScore h1 = new HandScore(2, 1, 2);
        HandScore h2 = new HandScore(2, 1, 2);

        junit.framework.TestCase.assertEquals(h1.compareTo(h2), 0);
    }
}
