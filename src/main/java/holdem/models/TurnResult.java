package holdem.models;

public enum TurnResult {
    CALL,
    FOLD,
    BET;

    private int betAmount = 0;

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }
}
