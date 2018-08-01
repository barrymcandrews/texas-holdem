package holdem.controllers;

import holdem.Constants;
import holdem.Game;
import holdem.MyTimerTask;
import holdem.models.Heckle;
import holdem.models.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class ChatBarController extends Controller {

    private static int childCount = 0;

    private JPanel innerPanel = new JPanel();
    private LinkedBlockingQueue<ChatBarRow> heckleQueue = new LinkedBlockingQueue<>();
    private JScrollPane jScrollPane;
    private boolean heckleEnabled = MyTimerTask.getHeckleEnabled();


    public ChatBarController() {
        super();
        setupLayout(getView());
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

        //Get a random time for the timer period
        int delay = (5 + new Random().nextInt(5)*10000) - 10000;
        Game game = Game.getInstance();
        Timer heckleTimer = new Timer(delay, e -> {
            if(heckleEnabled){
                ArrayList<Player> players = game.getActivePlayers();
                players.remove(game.getHumanPlayer());
                int randomIndex = new Random().nextInt(players.size());
                String message = Heckle.generateHeckle();
                ChatBarRow row = new ChatBarRow(players.get(randomIndex), message);
                try {
                    heckleQueue.put(row);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        heckleTimer.start();
    }

    @Override
    public void reloadData() {
        super.reloadData();


        while (!heckleQueue.isEmpty()) {
            JPanel view = heckleQueue.poll().getView();

            if (childCount % 2 == 0) {
                view.setBackground(Color.white);
            }

            innerPanel.add(view);
            childCount++;
        }
        jScrollPane.revalidate();
        jScrollPane.repaint();
        JScrollBar vertical = jScrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }
}
