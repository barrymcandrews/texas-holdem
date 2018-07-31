package holdem.controllers;

import holdem.Constants;
import holdem.Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.concurrent.LinkedBlockingQueue;

public class ChatBarController extends Controller {

    private static int childCount = 0;

    private JPanel innerPanel = new JPanel();
    private LinkedBlockingQueue<ChatBarRow> heckleQueue = new LinkedBlockingQueue<>();
    private JScrollPane jScrollPane;

    public ChatBarController() {
        super();
        setupLayout(getView());
    }

    @Override
    void setupLayout(JPanel view) {
        view.setLayout(new GridLayout(0, 1));
        view.setBackground(Color.white);
        view.setPreferredSize(new Dimension(350, 800));

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


        Timer timer = new Timer(1000, (e) -> {
            String message = "The quick brown fox jumped over the lazy dog.";
            ChatBarRow row = new ChatBarRow(Constants.BACKUP_AI_PLAYER, message);
            try {
                heckleQueue.put(row);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        timer.start();
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
