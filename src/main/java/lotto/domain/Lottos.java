package lotto.domain;

import lotto.generator.RandomLottoGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
    private static final RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();
    private final List<Lotto> values;

    private Lottos(List<Lotto> values) {
        this.values = values;
    }

    public static Lottos buyRandomLottos(Money money, long manualCount) {
        List<Lotto> randomLottos = randomLottoGenerator.generateLottos(money.getTotalLottoCount() - manualCount);
        return new Lottos(randomLottos);
    }

    public static Lottos buyManualLottos(List<List<Integer>> manualLottoNumbers) {
        List<Lotto> manaulLottos = new ArrayList<>();
        for (List<Integer> manualLotto : manualLottoNumbers) {
            List<Ball> manaulLottoBalls = manualLotto.stream()
                    .map(Ball::of)
                    .collect(Collectors.toList());
            manaulLottos.add(new Lotto(manaulLottoBalls));
        }

        return new Lottos(manaulLottos);
    }

    public int getSize() {
        return this.values.size();
    }

    public Lotto get(int ix) {
        return values.get(ix);
    }
}
