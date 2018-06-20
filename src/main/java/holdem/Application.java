package holdem;

import holdem.controllers.RootController;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import javax.swing.*;

public class Application {

    private static final Logger log = LogManager.getLogger(Application.class);

    private JFrame frame = new JFrame();
    static RootController rootController = new RootController();

    public static void main(String[] args) {
        log.debug("Starting application...");
        SwingUtilities.invokeLater(() -> new Application().start());
    }

    private void start() {
        log.debug("Initializing GUI...");
        frame.setTitle("Texas Hold'em");
        frame.setSize(1200, 900);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(rootController.getView());
        rootController.reloadData();
        frame.setVisible(true);
        new GameWorker().execute();
    }
}
