package views;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    JPanel sidebar = new JPanel();

    public GamePanel() {
        super();

        sidebar.setBackground(Color.BLUE);

        setLayout(new BorderLayout());
        add(sidebar, BorderLayout.BEFORE_LINE_BEGINS);
    }

}
