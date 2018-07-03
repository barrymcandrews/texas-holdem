package holdem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Constants {
    public static final String[] AI_NAMES = {
        "Bob",
        "Linda",
        "Tina",
        "Gene",
        "Louise",
        "Jimmy Jr.",
        "Teddy"
    };

    public static final ArrayList<String> AI_NAMES_LIST = new ArrayList<>(Arrays.asList(AI_NAMES));

    public static final String BACKUP_USERNAME = "Benadryl Cucumber";

    public static final ImageIcon BACK_OF_CARD_IMAGE = new ImageIcon("src/main/resources/png/back.png");
    public static final Dimension DEFAULT_CARD_DIMENSION = new Dimension(60, 90);
    public static final Dimension SIDEBAR_CARD_DIMENSION = new Dimension(60, 90);

}
