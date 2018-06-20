package holdem.controllers;

import javax.swing.*;
import java.awt.*;

public class RootController extends Controller {
    private SidebarController rightSidebarController = new SidebarController();
    private MainController mainController = new MainController();

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
        view.add(mainController.getView(), BorderLayout.CENTER);
    }

    @Override
    public void reloadData() {

    }
}