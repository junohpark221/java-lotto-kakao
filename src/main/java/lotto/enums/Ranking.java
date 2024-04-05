package lotto.enums;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Ranking {
    FAIL(0, 0, (answerCount, isCorrectBonusBall) -> answerCount <= 2),
    FIFTH(5000, 3, (answerCount, isCorrectBonusBall) -> answerCount == 3),
    FOURTH(50000, 4, (answerCount, isCorrectBonusBall) -> answerCount == 4),
    THIRD(1500000, 5, (answerCount, isCorrectBonusBall) -> answerCount == 5 && !isCorrectBonusBall),
    SECOND(30000000, 5, (answerCount, isCorrectBonusBall) -> answerCount == 5 && isCorrectBonusBall),
    FIRST(2000000000, 6, (answerCount, isCorrectBonusBall) -> answerCount == 6);

    private final long reward;
    private final int matchCount;
    private final BiFunction<Long, Boolean, Boolean> checkMatch;

    Ranking(long reward, int matchCount, BiFunction<Long, Boolean, Boolean> checkMatch) {
        this.reward = reward;
        this.matchCount = matchCount;
        this.checkMatch = checkMatch;
    }

    public long getReward() {
        return reward;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public static Ranking getRank(long answerCount, boolean isCorrectBonusBall) {
        return Arrays.stream(values())
                .filter(ranking -> ranking.checkMatch.apply(answerCount, isCorrectBonusBall))
                .findFirst()
                .orElse(FAIL);
    }
}
