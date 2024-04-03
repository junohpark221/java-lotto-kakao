package lotto.enums;

public enum Ranking {
    FAIL(0),
    FIFTH(5000),
    FOURTH(50000),
    THIRD(1500000),
    SECOND(30000000),
    FIRST(2000000000);

    private final long reward;

    Ranking(long reward) {
        this.reward = reward;
    }

    public long getReward() {
        return reward;
    }
}
