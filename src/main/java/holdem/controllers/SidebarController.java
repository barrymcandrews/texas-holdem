package holdem.controllers;

import holdem.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SidebarController extends Controller {
    private final Game GAME = Game.getInstance();

    private ArrayList<SidebarRowController> rows = new ArrayList<>();

    public SidebarController() {
        super();
        setupLayout(getView());
        reloadData();
    }

    @Override
    public void setupLayout(JPanel view) {
        view.setBackground(Color.BLUE);
        view.setPreferredSize(new Dimension(300, 900));
        GAME.getPlayers().forEach(p -> rows.add(new SidebarRowController(p)));
        rows.forEach(r -> view.add(r.getView()));

    }

    @Override
    public void reloadData() {

    }
}
