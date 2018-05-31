package controllers;

import javax.swing.*;
import views.GamePanel;

public class GameController implements Controller {
    GamePanel gamePanel = new GamePanel();

    public GameController() {
        //TODO: Initialize game elements here
    }

    @Override
    public JPanel getPanel() {
        return gamePanel;
    }
}