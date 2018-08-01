package holdem;

import holdem.models.Player;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class Constants {

    public static final boolean DEBUG = Boolean.parseBoolean(System.getenv("DEBUG"));

    public static final Player[] AI_PLAYERS = {
        new Player("Bob", new ImageIcon("src/main/resources/ai-images/bob.jpg")),
        new Player("Linda", new ImageIcon("src/main/resources/ai-images/linda.jpg")),
        new Player("Tina", new ImageIcon("src/main/resources/ai-images/tina.jpg")),
        new Player("Gene", new ImageIcon("src/main/resources/ai-images/gene.png")),
        new Player("Louise", new ImageIcon("src/main/resources/ai-images/louise.png")),
        new Player("Jim Jr", new ImageIcon("src/main/resources/ai-images/jj.png")),
        new Player("Teddy", new ImageIcon("src/main/resources/ai-images/teddy.jpg"))
    };

    public static final Player BACKUP_AI_PLAYER = new Player("Benadryl Cucumber",
        new ImageIcon("src/main/resources/ai-images/human.jpg"));

    public static final ImageIcon BACK_OF_CARD_IMAGE = new ImageIcon("src/main/resources/cards/back.png");

    public static final Dimension LARGE_CARD_DIMENSION = new Dimension(120, 180);
    public static final Dimension DEFAULT_CARD_DIMENSION = new Dimension(60, 90);
    public static final Dimension SIDEBAR_CARD_DIMENSION = new Dimension(60, 90);
    public static final Dimension SIDEBAR_CARD_CONTROLLER_DIMENSION = new Dimension(136, 110);
    public static final Dimension PLAYER_IMAGE_DIMENSION = new Dimension(50, 50);
    public static final Dimension TAG_LABEL_DIMENSION = new Dimension(90,24);

    public static final Color SIDEBAR_COLOR = new Color(40, 40, 40);
    public static final Color SIDEBAR_COLOR_HIGHLIGHTED = new Color(90, 90, 90);

    public static final Font RYE_FONT = loadFont("src/main/resources/fonts/Rye-Regular.ttf");
    public static final Font ROBOTO_FONT = loadFont("src/main/resources/fonts/Roboto-Regular.ttf");

    private static Font loadFont(String path) {
        try {
            FileInputStream fontStream = new FileInputStream(path);
            return Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
