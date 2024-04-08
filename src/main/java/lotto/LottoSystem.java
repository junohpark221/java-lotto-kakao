package lotto;

import lotto.domain.*;
import lotto.generator.RandomLottoGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoSystem {
    private final RandomLottoGenerator numberGenerator;
    private final Result result;

    public LottoSystem() {
        this.numberGenerator = new RandomLottoGenerator();
        this.result = new Result();
    }

    public Lottos buyAutoLottos(Money money, long manualCount) {
        return numberGenerator.generateLottos(money.getTotalLottoCount() - manualCount);
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

    public WinningLotto convertToAnswer(List<Integer> answerNumber, int bonusNumber) {
        List<Ball> answerBalls = answerNumber
                .stream()
                .map(Ball::of)
                .collect(Collectors.toList());
        Ball bonusBall = Ball.of(bonusNumber);

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

    public Profit calculateProfit(Money money) {
        long reward = result.calculateReward();
        long seed = money.getTotalLottoCount() * Money.getLottoPrice();

        return new Profit(reward, seed);
    }

    public Result getResult() {
        return result;
    }
}
