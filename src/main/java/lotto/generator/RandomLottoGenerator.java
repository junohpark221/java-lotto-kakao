package lotto.generator;

import lotto.domain.Ball;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator {
    public List<Lotto> generateLottos(long lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount ; i++) {
            lottos.add(generateLotto());
        }

        return lottos;
    }

    public synchronized Lotto generateLotto() {
        List<Ball> ballPool = new ArrayList<>(Ball.allBalls());
        Collections.shuffle(ballPool);
        return new Lotto(ballPool.subList(0, Lotto.getLottoSize()));
    }
}
