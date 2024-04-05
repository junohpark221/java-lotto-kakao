package lotto;

import lotto.domain.*;
import lotto.generator.RandomLottoGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoSystem {
    private static final Money LOTTO_PRICE = new Money(1000);
    private RandomLottoGenerator numberGenerator;
    private Result result;
    private long manualLottoCount;
    private long autoLottoCount;

    public LottoSystem() {
        this.numberGenerator = new RandomLottoGenerator();
        this.result = new Result();
        this.manualLottoCount = 0;
        this.autoLottoCount = 0;
    }

    public void checkLottoCount(Money money, long manualCount) {
        long totalLottoCount = calculateTotalCount(money);
        validateManualCount(totalLottoCount, manualCount);
        this.manualLottoCount = manualCount;
        this.autoLottoCount = totalLottoCount - manualCount;
    }

    public Lottos buyAutoLottos() {
        return numberGenerator.generateLottos(autoLottoCount);
    }

    public Lottos buyManualLottos(List<List<Integer>> manualLottoNumbers) {
        List<Lotto> manaulLottos = new ArrayList<>();
        for (List<Integer> manualLotto : manualLottoNumbers) {
            List<Ball> manaulLottoBalls = manualLotto.stream()
                    .map(Ball::of)
                    .collect(Collectors.toList());
            manaulLottos.add(new Lotto(manaulLottoBalls));
        }

        return new Lottos(manaulLottos);
    }

    private long calculateTotalCount(Money money) {
        return money.divide(LOTTO_PRICE);
    }

    private void validateManualCount(long totalCount, long manualCount) {
        if (manualCount > totalCount) {
            throw new IllegalArgumentException("금액보다 많은 로또를 수동으로 구매할 수 없습니다.");
        }
    }

    public WinningLotto convertToAnswer(List<Integer> answerAndBonusNumber) {
        List<Ball> answerBalls = answerAndBonusNumber.subList(0, 6)
                .stream()
                .map(Ball::of)
                .collect(Collectors.toList());
        Ball bonusBall = Ball.of(answerAndBonusNumber.get(6));

        Lotto lotto = new Lotto(answerBalls);
        return new WinningLotto(lotto, bonusBall);
    }

    public void scoreLottos(Lottos userManualLottos, Lottos userAutoLottos, WinningLotto winningLotto) {
        for (int i = 0; i < userManualLottos.getSize(); i++) {
            scoreLotto(userManualLottos.get(i), winningLotto);
        }

        for (int i = 0; i < userAutoLottos.getSize(); i++) {
            scoreLotto(userAutoLottos.get(i), winningLotto);
        }
    }

    public void scoreLotto(Lotto lotto, WinningLotto winningLotto) {
        result.scoreLotto(lotto, winningLotto);
    }

    public Profit calculateProfit() {
        long reward = result.calculateReward();
        long seed = (manualLottoCount + autoLottoCount) * LOTTO_PRICE.getValue();

        return new Profit(reward, seed);
    }

    public Result getResult() {
        return result;
    }
}
