package holdem;

import holdem.controllers.RootController;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import javax.swing.*;
import java.awt.*;

public class Application {

    private static final Logger log = LogManager.getLogger(Application.class);

    private JFrame frame = new JFrame();
    static RootController rootController = new RootController();

    public static void main(String[] args) {
        log.debug("Starting application...");
        SwingUtilities.invokeLater(() -> new Application().start());
    }

    private void start() {
        if (Constants.DEBUG) log.debug("Debug mode enabled.");
        log.debug("Initializing GUI...");

        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Constants.ROBOTO_FONT);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Constants.RYE_FONT);

        frame.setTitle("Texas Hold'em");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(rootController.getView());
        rootController.reloadData();
        frame.setVisible(true);
        new GameWorker().execute();
    }

    public void resetGui() {
        start();
    }
}
