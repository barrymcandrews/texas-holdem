package holdem.models;

import holdem.GameWorker.Move;

import javax.swing.*;
import java.util.*;

public class Player {

    private String name;
    private int wallet;
    private Set<Card> hand = new HashSet<>();
    private PlayerType type = PlayerType.AI;
    private boolean isActive = true;
    private Move move;
    private boolean isEliminated = false;
    private int handBet;
    private int totalRoundBet;
    private ImageIcon image;

    public Player(String name, ImageIcon image) {
        this.name = name;
        this.wallet = 1000;
        this.move = Move.INVALID;
        this.handBet = 0;
        this.totalRoundBet = 0;
        this.image = image;
    }

    public Player(String name, ImageIcon image, PlayerType type) {
        this.name = name;
        this.wallet = 1000;
        this.type = type;
        this.move = Move.INVALID;
        this.handBet = 0;
        this.totalRoundBet = 0;
        this.image = image;
    }

    public PlayerType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public void winMoney(int money) {
        this.wallet += money;
    }

    public void loseMoney(int money) {
        this.wallet -= money;
    }

    public Set<Card> getHand() {
        return hand;
    }

    public void setHand(Set<Card> cards) {
        hand.clear();
        hand.addAll(cards);
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String toString() {
        return name;
    }

    public Move getRandomMove(ArrayList<Player> players) {
        ArrayList<Move> options = new ArrayList<>(Arrays.asList(Move.CALL, Move.CALL, Move.CALL, Move.CALL, Move.CALL, Move.BET, Move.BET));
        for (Player p : players) {
            if (p.getMove() == Move.BET) {
                options.add(Move.FOLD);
                break;
            }
        }
        Move move = options.get(new Random().nextInt(options.size()));
        this.setMove(move);
        return move;
    }

    public boolean isEliminated() {
        return isEliminated;
    }

    public void setEliminated(boolean eliminated) {
        isEliminated = eliminated;
    }

    public int getHandBet() {
        return handBet;
    }

    public void setHandBet(int handBet) {
        if (getWallet() >= handBet) {
            int prebet = this.handBet;
            this.handBet = handBet;
            loseMoney(handBet - prebet);
            this.totalRoundBet += this.handBet - prebet;
        } else {
            this.handBet = getWallet();
            int prebet = this.handBet;
            loseMoney(this.handBet - prebet);
            this.totalRoundBet += this.handBet - prebet;
        }
    }
    
    public void resetHandBet() {
        handBet = 0;
    }

    public int getTotalRoundBet() {
        return totalRoundBet;
    }

    public void setTotalRoundBet(int totalRoundBet) {
        this.totalRoundBet = totalRoundBet;
    }

    public ImageIcon getImage() {
        return image;
    }

    public enum PlayerType {HUMAN, AI}
}