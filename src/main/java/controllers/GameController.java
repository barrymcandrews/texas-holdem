package controllers;

import views.GamePanel;
import javax.swing.*;

public class GameController extends Controller<JPanel> {
    GamePanel gamePanel = new GamePanel();

    public GameController() {
        setView(gamePanel);
    }


}