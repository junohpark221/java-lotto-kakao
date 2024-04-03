package lotto;

import lotto.domain.*;
import lotto.generator.RandomLottoGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class LottoSystem {
    private static final Money LOTTO_PRICE = new Money(1000);
    private RandomLottoGenerator numberGenerator;
    private Result result;
    private long lottoCount;

    public LottoSystem() {
        this.numberGenerator = new RandomLottoGenerator();
        this.result = new Result();
        this.lottoCount = 0;
    }

    public Lottos buyLottos(Money money) {
        lottoCount = calculateLottoCount(money);
        return generateLottos();
    }

    private long calculateLottoCount(Money money) {
        return money.divide(LOTTO_PRICE);
    }

    private Lottos generateLottos() {
        return numberGenerator.generateLottos(lottoCount);
    }

    public WinningLotto convertToAnswer(List<Integer> answerAndBonusNumber) {
        List<Ball> answerBalls = answerAndBonusNumber.subList(0, 6)
                .stream()
                .map(Ball::new)
                .collect(Collectors.toList());
        Ball bonusBall = new Ball(answerAndBonusNumber.get(6));

        Lotto lotto = new Lotto(answerBalls);
        return new WinningLotto(lotto, bonusBall);
    }

    public void scoreLottos(Lottos lottos, WinningLotto winningLotto) {
        for (int i = 0; i < lottoCount; i++) {
            scoreLotto(lottos.get(i), winningLotto);
        }
    }

    public void scoreLotto(Lotto lotto, WinningLotto winningLotto) {
        result.scoreLotto(lotto, winningLotto);
    }

    public Profit calculateProfit() {
        long reward = result.calculateReward();
        long seed = lottoCount * LOTTO_PRICE.getValue();

        return new Profit(reward, seed);
    }

    public Result getResult() {
        return result;
    }
}
