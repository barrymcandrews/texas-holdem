package holdem.controllers;

import holdem.Game;
import holdem.models.Heckle;
import holdem.models.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class ChatBarController extends Controller {
    private static Game GAME = Game.getInstance();

    private static int childCount = 0;

    private JPanel innerPanel = new JPanel();
    private JScrollPane jScrollPane;

    private ArrayList<Player> otherPlayers = new ArrayList<>(GAME.getPlayers());
    private Timer heckleTimer = new Timer(5000, this::addHeckle);

    public ChatBarController() {
        super();
        setupLayout(getView());
        otherPlayers.remove(GAME.getHumanPlayer());
        heckleTimer.start();
    }

    @Override
    void setupLayout(JPanel view) {
        view.setLayout(new GridLayout(0, 1));
        view.setBackground(Color.white);
        view.setPreferredSize(new Dimension(350, 800));
        view.setBorder(new MatteBorder(0, 1,0,0, new Color(0xF0F0F0)));

        jScrollPane = new JScrollPane(innerPanel);
        jScrollPane.setPreferredSize(new Dimension(350, 800));
        jScrollPane.getViewport().setView(innerPanel);
        jScrollPane.setViewportBorder(new EmptyBorder(new Insets(0,0,0,0)));
        jScrollPane.setBorder(new EmptyBorder(new Insets(0,0,0,0)));
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        view.add(jScrollPane);

        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(Color.white);
        //innerPanel.setPreferredSize(new Dimension(350, innerPanel.getHeight()));
    }

    @Override
    public void reloadData() {
        super.reloadData();
    }

    private void startNewTimer() {
        int delay = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);
        heckleTimer = new Timer(delay, this::addHeckle);
        heckleTimer.start();
    }

    private void addHeckle(ActionEvent actionEvent) {
        int randomIndex = new Random().nextInt(otherPlayers.size());
        String message = Heckle.generateHeckle();
        ChatBarRow row = new ChatBarRow(otherPlayers.get(randomIndex), message);
        JPanel view = row.getView();
        if (childCount % 2 == 0) {
            view.setBackground(Color.white);
        }

        innerPanel.add(view);
        childCount++;

        getView().revalidate();
        getView().repaint();
        JScrollBar vertical = jScrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());

        heckleTimer.stop();
        startNewTimer();
    }
}
