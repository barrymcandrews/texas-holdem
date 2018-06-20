package holdem;

import com.ea.async.Async;
import holdem.controllers.RootController;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import javax.swing.*;

public class Application {

    private static final Logger log = LogManager.getLogger(Application.class);

    private JFrame frame = new JFrame();
    private RootController gameController = new RootController();

    public static void main(String[] args) {
        log.debug("Starting application...");
        SwingUtilities.invokeLater(() -> {
            Application app = new Application();
            app.start();
        });
    }

    private void start() {
        log.debug("Initializing GUI...");
        frame.setTitle("Texas Hold'em");
        frame.setSize(1200, 900);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(gameController.getView());
        frame.setVisible(true);
    }
}
