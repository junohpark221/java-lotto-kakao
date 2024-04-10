package lotto.domain;

import lotto.enums.Ranking;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private final Map<Ranking, Integer> score;
    private long reward;

    private Result(Map<Ranking, Integer> scores) {
        this.score = scores;
        reward = 0L;
    }

    public static Result scoreLottos(Lottos userManualLottos, Lottos userAutoLottos, WinningLotto winningLotto) {
        Map<Ranking, Integer> scores = new HashMap<>();

        for (int i = 0; i < userManualLottos.getSize(); i++) {
            Ranking rank = scoreLotto(userManualLottos.get(i), winningLotto);
            int rankScore = scores.getOrDefault(rank, 0);
            scores.put(rank, rankScore + 1);
        }

        for (int i = 0; i < userAutoLottos.getSize(); i++) {
            Ranking rank = scoreLotto(userAutoLottos.get(i), winningLotto);
            int rankScore = scores.getOrDefault(rank, 0);
            scores.put(rank, rankScore + 1);
        }

        return new Result(scores);
    }

    public static Ranking scoreLotto(Lotto lotto, WinningLotto winningLotto) {
        long answerCount = winningLotto.getWinningNumbers().getBalls().stream().filter(lotto::contain).count();
        boolean isCorrectBonusBall = lotto.contain(winningLotto.getBonusNumber());

        return Ranking.getRank(answerCount, isCorrectBonusBall);
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
