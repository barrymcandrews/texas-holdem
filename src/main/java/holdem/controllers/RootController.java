package holdem.controllers;

import holdem.Game;

import javax.swing.*;
import java.awt.*;

public class RootController extends Controller {
    private static Game GAME = Game.getInstance();

    private SidebarController rightSidebarController = new SidebarController();
    private MainController mainController = new MainController();
    private ChatBarController chatBarController = new ChatBarController();

    public RootController() {
        super();
        setupLayout(getView());
    }

    @Override
    public void setupLayout(JPanel view) {
        view.setBackground(Color.RED);
        view.setLayout(new BorderLayout());
        view.add(rightSidebarController.getView(), BorderLayout.BEFORE_LINE_BEGINS);
        view.add(mainController.getView(), BorderLayout.CENTER);

        if (GAME.isHecklingEnabled()) {
            view.add(chatBarController.getView(), BorderLayout.AFTER_LINE_ENDS);
        }
    }

    @Override
    public void reloadData() {
        rightSidebarController.reloadData();
        mainController.reloadData();

        if (GAME.isHecklingEnabled()) {
            chatBarController.reloadData();
        }

        getView().revalidate();
        getView().repaint();
    }
}