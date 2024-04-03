package lotto.domain;

import lotto.enums.Ranking;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private final Map<Ranking, Integer> score;
    private long reward;

    public Result() {
        score = new HashMap<>();
        reward = 0L;
    }

    public void scoreLotto(Lotto lotto, WinningLotto winningLotto) {
        long answerCount = winningLotto.getWinningNumbers().getBalls().stream().filter(lotto::contain).count();
        boolean isCorrectBonusBall = lotto.contain(winningLotto.getBonusNumber());

        Ranking rank = Ranking.getRank(answerCount, isCorrectBonusBall);
        addScore(rank);
    }

    private void addScore(Ranking rank) {
        int rankScore = score.getOrDefault(rank, 0);
        score.put(rank, rankScore + 1);
    }

    public int getScore(Ranking rank) {
        return score.getOrDefault(rank, 0);
    }


    public long calculateReward() {
        this.reward = 0L;

        for (Map.Entry<Ranking, Integer> entry: this.score.entrySet()) {
            this.reward += entry.getKey().getReward() * entry.getValue();
        }

        return this.reward;
    }
}
