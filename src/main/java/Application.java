import javax.swing.*;

public class Application {
    private JFrame frame = new JFrame();
    private Controller gameController = new GameController();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Application app = new Application();
            app.start();
        });
    }

    private void start() {
        frame.setTitle("Texas Hold'em");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(gameController.getPanel());
        frame.setVisible(true);
    }
}
