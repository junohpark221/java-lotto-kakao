package lotto.enums;

import java.util.Arrays;

public enum Ranking {
    FAIL(0, 0),
    FIFTH(5000, 3),
    FOURTH(50000, 4),
    THIRD(1500000, 5),
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

    public static Ranking getRank(long answerCount, boolean isCorrectBonusBall) {
        if (answerCount == 5 && isCorrectBonusBall) {
            return SECOND;
        }

        if (answerCount < FIFTH.matchCount) {
            return FAIL;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == answerCount)
                .findFirst()
                .orElse(null);
    }
}
