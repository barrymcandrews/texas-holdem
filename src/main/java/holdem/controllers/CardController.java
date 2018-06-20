package holdem.controllers;

import holdem.models.Card;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class CardController extends Controller {

    private Set<Card> cards;

    public CardController(Set<Card> cards) {
        super();
        this.cards = cards;
        setupLayout(getView());
    }

    @Override
    void setupLayout(JPanel view) {
        view.setBackground(Color.lightGray);
    }

    @Override
    public void reloadData() {
        JPanel view = getView();
        view.removeAll();
        for (Card c : cards) {
            JPanel surround = new JPanel();
            surround.setPreferredSize(new Dimension(60, 90));
            surround.setLayout(new BoxLayout(surround, BoxLayout.X_AXIS));
            JLabel img = new JLabel();
            ImageIcon ii = new ImageIcon(c.getFrontOfCard().getImage().getScaledInstance(60, 90, java.awt.Image.SCALE_SMOOTH));
            img.setIcon(ii);
            img.setPreferredSize(new Dimension(60, 90));
            view.add(surround);
            surround.add(img);
        }
    }
}
