package holdem.models;

import holdem.controllers.ActionButtonController;

import java.util.Map;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public TurnResult getTurn(Map<Player, TurnResult> previousResults) {
        // TODO: Dialog
        return null;
    }

}
