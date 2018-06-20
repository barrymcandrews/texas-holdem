package holdem;

import holdem.controllers.RootController;
import holdem.models.BestHand;
import holdem.models.Player;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.omg.CORBA.INTERNAL;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameWorker extends SwingWorker<Void, Game> {
    private final Logger log = LogManager.getLogger(GameWorker.class);
    private final RootController ROOT_CONTROLLER = Application.rootController;
    private final Game GAME = Game.getInstance();

    public static Queue<Integer> GameQueue = new LinkedList<>();


    @Override
    protected Void doInBackground() throws Exception {
        log.debug("Game thread started.");

        while (true) {
            Player dealer = GAME.getDealer();
            log.debug("Dealer this round: " + dealer.getName());
            GAME.dealToPlayers();
            process(null);

            slowGame();
            if (!GameQueue.isEmpty()) {
                switch (GameQueue.remove()) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }

            GAME.dealToCenter(3);
            slowGame();
            GAME.dealToCenter(1);
            slowGame();
            GAME.dealToCenter(1);
            slowGame();

//            BestHand.findBestHand()
            GAME.incrementDealer();
            GAME.shuffleDeck();
            process(null);
        }
    }

    @Override
    protected void process(List<Game> chunks) {
        ROOT_CONTROLLER.reloadData();
    }

    @Override
    protected void done() {
        log.debug("Game thread stopped.");
    }

    private void slowGame() throws Exception {
            Thread.sleep(1000);
    }

}
