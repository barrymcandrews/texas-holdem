package holdem.models;

public class AiPlayer extends Player {
    public AiPlayer(String name) {
        super(name);
    }

    @Override
    public TurnResult getTurn() {
        return null;
    }
}
