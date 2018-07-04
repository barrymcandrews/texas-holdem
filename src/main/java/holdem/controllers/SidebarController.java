package holdem.controllers;

import holdem.Game;
import holdem.models.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SidebarController extends Controller {
    private final Game GAME = Game.getInstance();

    private ArrayList<SidebarRowController> rows = new ArrayList<>();

    public SidebarController() {
        super();
        setupLayout(getView());
    }

    @Override
    public void setupLayout(JPanel view) {
        view.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        view.setBackground(new Color(40, 40, 40));
        view.setPreferredSize(new Dimension(350, 900));
        GAME.getPlayers().forEach(p -> {
            if (p.getType() == Player.PlayerType.AI) rows.add(new SidebarRowController(p));
        });

        for (SidebarRowController r : rows) {
            view.add(r.getView());
        }
    }

    @Override
    public void reloadData() {
        for (SidebarRowController c : rows) {
            c.reloadData();
        }
    }
}