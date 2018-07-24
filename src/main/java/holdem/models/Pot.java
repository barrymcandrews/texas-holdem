package holdem.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pot {
    Map<Player, Integer> potContribution;

    public Pot(ArrayList<Player> players) {
        potContribution = new HashMap<>();
        for(Player p : players) {
            potContribution.put(p, 0);
        }
    }

    public void addBet(Player p, int betAmount) {
        potContribution.put(p, potContribution.get(p) + betAmount);
    }

    public int[] getPot(ArrayList<Player> winners) {
        int i = 0;
        int[] potsToWin = new int[winners.size()];
        for (Player p: winners) {
            for (Map.Entry<Player, Integer> pair : potContribution.entrySet()) {
                if (pair.getValue() <= potContribution.get(p)) {
                    potsToWin[i] += pair.getValue() / (winners.size() - i);
                    potContribution.put(pair.getKey(), (pair.getValue() - pair.getValue() / (winners.size() - i)));
                } else {
                    potsToWin[i] += potContribution.get(p) / (winners.size() - i);
                    potContribution.put(pair.getKey(), (pair.getValue() - potContribution.get(p) / (winners.size() - i)));
                }
            }
            i++;
        }
        return potsToWin;
    }

    public int getTotalPot() {
        int totalPot = 0;
        for (Map.Entry<Player, Integer> pair : potContribution.entrySet()) {
            totalPot += pair.getValue();
        }
        return totalPot;
    }

    public void resetPot(ArrayList<Player> players) {
        for(Player p : players) {
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
