package views;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends View {
    private JPanel mainPanel;
    private JPanel sidebar;

    public GamePanel() {
        mainPanel = new JPanel();
        sidebar = new JPanel();

        sidebar.setBackground(Color.BLUE);
        sidebar.setPreferredSize(new Dimension(300, 500));

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(sidebar, BorderLayout.LINE_START);
    }
}
