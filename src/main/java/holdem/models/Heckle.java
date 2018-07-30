package holdem.models;

public class Heckle {
    private static final String[] NOUN = {"garbage", "hand", "cards", "poker face"};
    private static final String[] PERSON = {"cotton-headed ninny muggins", "go fish level player", "toddler", "pinocchio"};
    private static final String[] ADJ = {"amateur", "rookie", "loser", "weakling"};


    public static String generateHeckle() {
        String insult;
        switch ((int) (Math.random() * 2)) {
            case 0:
                insult = "What kind of " + choose(ADJ) + " "
                    + choose(NOUN) + " is this, you "
                    + choose(PERSON) + "?";
                break;
            case 1:
                insult = "Never try this kind of " + choose(ADJ)
                    + " " + choose(NOUN) + " again!";
                break;
            default:
                insult = "Oops this is a mistake";
                break;
        }
        return insult;
    }

    private static String choose(String[] words) {
        int randomIndex = (int) (Math.random() * words.length);
        return words[randomIndex];
    }
}
