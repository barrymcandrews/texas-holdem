package holdem.controllers;

import javax.swing.*;
import java.awt.*;

public class RootController extends Controller {
    private SidebarController leftSidebarController = new SidebarController();
    private SidebarController rightSidebarController = new SidebarController();
    private JPanel gamePanel = new JPanel();

    public RootController() {
        super();
        setupLayout(getView());
        reloadData();
    }

    @Override
    public void setupLayout(JPanel view) {
        view.setBackground(Color.RED);
        view.setLayout(new BorderLayout());
        view.add(rightSidebarController.getView(), BorderLayout.BEFORE_LINE_BEGINS);
        view.add(gamePanel, BorderLayout.CENTER);
        view.add(leftSidebarController.getView(), BorderLayout.AFTER_LINE_ENDS);
    }
}