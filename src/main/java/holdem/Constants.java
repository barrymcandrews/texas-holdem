package holdem;

import holdem.models.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Constants {

    public static final boolean DEBUG = Boolean.parseBoolean(System.getenv("DEBUG"));

    public static final Player[] AI_PLAYERS = {
        new Player("Bob", new ImageIcon("src/main/resources/ai-images/human.jpg")),
        new Player("Linda", new ImageIcon("src/main/resources/ai-images/human.jpg")),
        new Player("Tina", new ImageIcon("src/main/resources/ai-images/human.jpg")),
        new Player("Gene", new ImageIcon("src/main/resources/ai-images/human.jpg")),
        new Player("Louise", new ImageIcon("src/main/resources/ai-images/human.jpg")),
        new Player("Jimmy Jr.", new ImageIcon("src/main/resources/ai-images/human.jpg")),
        new Player("Teddy", new ImageIcon("src/main/resources/ai-images/human.jpg"))
    };

    public static final Player BACKUP_AI_PLAYER = new Player("Benadryl Cucumber",
        new ImageIcon("src/main/resources/ai-images/human.jpg"));

    public static final ImageIcon BACK_OF_CARD_IMAGE = new ImageIcon("src/main/resources/cards/back.png");

    public static final Dimension LARGE_CARD_DIMENSION = new Dimension(120, 180);
    public static final Dimension DEFAULT_CARD_DIMENSION = new Dimension(60, 90);
    public static final Dimension SIDEBAR_CARD_DIMENSION = new Dimension(60, 90);
    public static final Dimension SIDEBAR_CARD_CONTROLLER_DIMENSION = new Dimension(136, 110);
    public static final Dimension PLAYER_IMAGE_DIMENSION = new Dimension(50, 50);
}
