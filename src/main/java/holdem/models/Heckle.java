package holdem.models;

public class Heckle {
    private static final String[] NOUN = {"bravado", "hand", "bet", "poker face"};
    private static final String[] PERSON = {"cotton-headed ninny muggins", "go fish level player", "toddler", "rookie", "amateur", "egg-sucker"};
    private static final String[] ADJ = {"lame", "weak", "incompetent", "poor", "garbage"};


    public static String generateHeckle() {
        String insult;
        switch ((int) (Math.random() * 4)) {
            case 0:
                insult = "What kind of " + choose(ADJ) + " "
                    + choose(NOUN) + " is this, you "
                    + choose(PERSON) + "?";
                break;
            case 1:
                insult = "Never try this kind of " + choose(ADJ)
                    + " " + choose(NOUN) + " again!";
                break;
            case 2:
                insult = "Wow, I've never seen such a " + choose(ADJ) + " " + choose(NOUN)
                    + " before, I'm almost impressed you " + choose(PERSON) + ".";
                break;
            case 3:
                insult = "Have you ever considered sucking less?";
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
