package lotto.enums;

public enum Ranking {
    FAIL(0, 0),
    FIFTH(5000, 2),
    FOURTH(50000, 3),
    THIRD(1500000, 4),
    SECOND(30000000, 5),
    FIRST(2000000000, 6);

    private final long reward;
    private final int matchCount;

    Ranking(long reward, int matchCount) {
        this.reward = reward;
        this.matchCount = matchCount;
    }

    public long getReward() {
        return reward;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
