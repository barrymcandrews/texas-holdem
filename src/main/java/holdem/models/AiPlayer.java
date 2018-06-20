package holdem.models;

import holdem.Game;

import java.util.Map;

public class AiPlayer extends Player {

    public AiPlayer(String name) {
        super(name);
    }

    @Override
    public TurnResult getTurn(Map<Player, TurnResult> previousResults) {
        Game game = Game.getInstance();
        if (previousResults.containsKey(game.getHumanPlayer())) {
            TurnResult humansMove = previousResults.get(game.getHumanPlayer());
            if (humansMove == TurnResult.BET) {
                return TurnResult.FOLD;
            }
        }
        return TurnResult.CALL;
    }
}
