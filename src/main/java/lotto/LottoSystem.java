package lotto;

import lotto.domain.*;
import lotto.generator.RandomLottoGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoSystem {
    private final RandomLottoGenerator numberGenerator;

    public LottoSystem() {
        this.numberGenerator = new RandomLottoGenerator();
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


}
