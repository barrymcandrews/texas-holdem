package holdem;

import holdem.controllers.Controller;
import holdem.controllers.RootController;

import javax.swing.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class Application {

    public static final Logger log = LogManager.getLogger(Application.class);

    private JFrame frame = new JFrame();
    private RootController gameController = new RootController();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Application app = new Application();
            app.start();
        });
    }

    private void start() {
        log.debug(Game.getInstance().toString());
        frame.setTitle("Texas Hold'em");
        frame.setSize(1200, 900);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(gameController.getView());
        frame.setVisible(true);
    }
}
