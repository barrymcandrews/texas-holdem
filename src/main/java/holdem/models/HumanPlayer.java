package holdem.models;

import holdem.controllers.ActionButtonController;

import javax.swing.*;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static com.ea.async.Async.await;

public class HumanPlayer extends Player {
    private ActionButtonController actionButtonController;

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public TurnResult getTurn(Map<Player, TurnResult> previousResults) {
        return await(getTurnFuture());
    }

    private CompletableFuture<TurnResult> getTurnFuture() {
        CompletableFuture<TurnResult> future = new CompletableFuture<>();
        actionButtonController.foldButton.addActionListener((e) -> {
            future.complete(TurnResult.FOLD);
        });
        return future;
    }


    public void setActionButtonController(ActionButtonController abc) {
        actionButtonController = abc;
    }
}
