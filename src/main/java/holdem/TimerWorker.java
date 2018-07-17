package holdem;

import holdem.controllers.RootController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.List;

public class TimerWorker extends SwingWorker<Void, Game> {

    private final Logger log = LogManager.getLogger(TimerWorker.class);
    private final RootController ROOT_CONTROLLER = Application.rootController;
    private final Game GAME = Game.getInstance();

    @Override
    protected Void doInBackground() {
        log.debug("Timer Started");
        return null;
    }

    @Override
    protected void process(List<Game> chunks) {
        ROOT_CONTROLLER.reloadData();
    }

    @Override
    protected void done() {
        log.debug("Game thread stopped.");
    }
}
