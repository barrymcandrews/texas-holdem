package holdem;

import holdem.controllers.RootController;
import holdem.models.Player;
import holdem.models.TurnResult;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameWorker extends SwingWorker<Void, Void> {
    private final Logger log = LogManager.getLogger(GameWorker.class);
    private final RootController ROOT_CONTROLLER = Application.rootController;
    private final Game GAME = Game.getInstance();


    @Override
    protected Void doInBackground() {
        log.debug("Game thread started.");

        while (true) {
            Player dealer = GAME.getDealer();
            log.debug("Dealer this round: " + dealer.getName());
            GAME.dealToPlayers();
            int[] dealAmounts = { 3, 1, 1 };

            HashMap<Player, TurnResult> results = new HashMap<>();
            results = getTurnMoves(results);
            for (int dealAmount : dealAmounts) {
                GAME.dealToCenter(dealAmount);
                results = getTurnMoves(results);
            }


            GAME.incrementDealer();
            process(null);
        }
    }

    public HashMap<Player, TurnResult> getTurnMoves(HashMap<Player, TurnResult> previousMoves) {
        ArrayList<Player> players = GAME.getPlayers();
        for (int i = 1; i < players.size(); i++) {
            int dealerIndex = players.indexOf(GAME.getDealer());
            int index = (dealerIndex + i) % players.size();
            Player turnPlayer = players.get(index);
            // log.debug(turnPlayer.getName() + "'s Turn");

            TurnResult result = turnPlayer.getTurn(previousMoves);
            previousMoves.put(turnPlayer, result);
            log.debug(turnPlayer.getName() + ": " + result.toString());
        }
        return previousMoves;
    }

    @Override
    protected void process(List<Void> chunks) {
        ROOT_CONTROLLER.reloadData();
    }

    @Override
    protected void done() {
        log.debug("Game thread stopped.");
    }
}
