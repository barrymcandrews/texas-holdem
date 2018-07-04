package holdem.controllers;

import holdem.Constants;
import holdem.models.Card;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class CardSetController extends Controller {

    private Set<Card> cards;
    private Dimension cardDimension;
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();


    public CardSetController(Set<Card> cards) {
        this(cards, Constants.DEFAULT_CARD_DIMENSION);
    }

    public CardSetController(Set<Card> cards, Dimension cardDimension) {
        super();
        this.cards = cards;
        this.cardDimension = cardDimension;
        setupLayout(getView());
    }

    @Override
    void setupLayout(JPanel view) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagConstraints.insets = new Insets(0, 5,0, 5);
        view.setLayout(gridBagLayout);

        view.setBackground(new Color(0,0,0, 0));
    }

    @Override
    public void reloadData() {
        JPanel view = getView();
        view.removeAll();
        for (Card c : cards) {
            CardController cardController = new CardController(c, cardDimension);
            cardController.reloadData();
            view.add(cardController.getView(), gridBagConstraints);
        }
    }
}
