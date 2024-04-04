package lotto.generator;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator {
    private static final int LOTTO_LENGTH = 6;
    private static final List<Ball> ballPool = IntStream.rangeClosed(Ball.getMinBallNumber(), Ball.getMaxBallNumber())
            .mapToObj(Ball::new)
            .collect(Collectors.toList());

    public Lottos generateLottos(long lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount ; i++) {
            lottos.add(generateLotto());
        }

        return new Lottos(lottos);
    }

    public synchronized Lotto generateLotto() {
        Collections.shuffle(ballPool);
        List<Ball> randomBalls = new ArrayList<>(ballPool.subList(0, Lotto.getLottoSize()));
        return new Lotto(randomBalls);
    }
}
