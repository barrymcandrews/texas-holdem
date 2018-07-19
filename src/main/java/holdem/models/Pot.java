package holdem.models;

import holdem.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pot {
    Map<Player, Integer> potContribution;

    public Pot() {
        potContribution = new HashMap<>();
        for(Player p : Game.getInstance().getPlayers()) {
            potContribution.put(p, 0);
        }
    }

    public void addBet(Player p, int betAmount) {
        potContribution.put(p, potContribution.get(p) + betAmount);
    }

    public int getPot(Player p) {
        int potToWin = 0;
        for (Map.Entry<Player, Integer> pair : potContribution.entrySet()) {
            if (pair.getValue() <= potContribution.get(p)) {
                potToWin += pair.getValue();
                potContribution.put(pair.getKey(), pair.getValue() - potContribution.get(p));
            } else {
                potToWin += potContribution.get(p);
                potContribution.put(pair.getKey(), pair.getValue() - potContribution.get(p));
            }
        }
        return potToWin;
    }

    public int getTotalPot() {
        int totalPot = 0;
        for (Map.Entry<Player, Integer> pair : potContribution.entrySet()) {
            totalPot += pair.getValue();
        }
        return totalPot;
    }

    public void resetPot() {
        for(Player p : Game.getInstance().getPlayers()) {
            potContribution.put(p, 0);
        }
    }

    public boolean isPotEmpty() {
        for (Map.Entry<Player, Integer> pair : potContribution.entrySet()) {
            if (pair.getValue() > 0) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Integer> getSidePots() {
        ArrayList<Integer> sidePots = new ArrayList<>();
        for (Map.Entry<Player, Integer> pair : potContribution.entrySet()) {
            if (!sidePots.contains(pair.getValue())) {
                sidePots.add(pair.getValue());
            }
        }
        return sidePots;
    }
}
