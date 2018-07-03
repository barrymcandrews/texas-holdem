package holdem.controllers;

import holdem.models.Card;

import javax.swing.*;
import java.awt.*;

public class CardController extends Controller {

    private Card card;
    private Dimension dimension;
    private JLabel cardLabel = new JLabel();


    public CardController(Card card, Dimension dimension) {
        this.card = card;
        this.dimension = dimension;
        setupLayout(getView());
    }

    @Override
    public void setupLayout(JPanel view) {
        view.setBackground(Color.white);
        view.setPreferredSize(dimension);
        view.setLayout(new BoxLayout(view, BoxLayout.X_AXIS));
        view.setBorder(BorderFactory.createMatteBorder(1, 1,1,1, new Color(190, 190, 190)));

        cardLabel.setPreferredSize(view.getPreferredSize());
        view.add(cardLabel);
    }

    @Override
    public void reloadData() {
        cardLabel.setIcon(getScaledImage());
    }

    private ImageIcon getScaledImage() {
        Image unscaledImage = card.getImageIcon().getImage();
        Image scaledImage = unscaledImage.getScaledInstance(this.dimension.width, this.dimension.height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
