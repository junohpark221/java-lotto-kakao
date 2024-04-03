package lotto.generator;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator{
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_LENGTH = 6;
    private List<Ball> ballPool;

    public RandomLottoGenerator() {
        this.ballPool = IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                .mapToObj(Ball::new)
                .collect(Collectors.toList());
    }

    public Lottos generateLottos(long lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount ; i++) {
            lottos.add(generateLotto());
        }

        return new Lottos(lottos);
    }

    public synchronized Lotto generateLotto() {
        Collections.shuffle(ballPool);
        List<Ball> randomBalls = new ArrayList<>(ballPool.subList(0, LOTTO_LENGTH));
        return new Lotto(randomBalls);
    }
}
