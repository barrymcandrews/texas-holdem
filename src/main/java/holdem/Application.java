package holdem;

import holdem.controllers.RootController;

import javax.swing.*;
import java.util.concurrent.Executor;

public class Application {
    static final Executor SWING = runnable -> {
        if (SwingUtilities.isEventDispatchThread())
            runnable.run();
        else
            SwingUtilities.invokeLater(runnable);
    };
    private JFrame frame = new JFrame();
    private RootController gameController = new RootController();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Application app = new Application();
            app.start();
        });
    }

    private void start() {
        System.out.println(Game.getInstance().toString());
        frame.setTitle("Texas Hold'em");
        frame.setSize(1200, 900);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(gameController.getView());
        frame.setVisible(true);
    }
}
