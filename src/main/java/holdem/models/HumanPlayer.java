package holdem.models;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public TurnResult getTurn() {
        return null;
    }
}
