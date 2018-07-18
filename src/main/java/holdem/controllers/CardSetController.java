package holdem.controllers;

import holdem.Constants;
import holdem.models.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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

        view.setPreferredSize(new Dimension(view.getWidth(), cardDimension.height + 10));
        view.setLayout(gridBagLayout);
        view.setBackground(Color.BLUE);
        view.setBackground(new Color(0,0,0, 0));
    }

    @Override
    public void reloadData() {
        ArrayList<CardController> cardControllers = new ArrayList<>();
        for (Card c : cards) {
            CardController cardController = new CardController(c, cardDimension);
            cardController.reloadData();
            cardControllers.add(cardController);
        }

        // This loop must be separated from the loop above to ensure the
        // UI is not repainted in between when the cards are added.
        JPanel view = getView();
        view.removeAll();
        for (int i = 0; i < cardControllers.size(); i++){
            view.add(cardControllers.get(i).getView(), gridBagConstraints);
        }
    }
}
