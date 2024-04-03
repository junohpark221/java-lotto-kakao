package lotto.generator;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator implements NumberGenerator {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_LENGTH = 6;
    private List<Integer> numberPool;

    public RandomNumberGenerator() {
        this.numberPool = new ArrayList<>();
        IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                .forEach(number -> numberPool.add(number));
    }

    private List<Integer> generateNumbers(int length) {
        Collections.shuffle(numberPool);
        return numberPool.subList(0, length);
    }

    public Lottos generateLottos(long lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount ; i++) {
            lottos.add(generateLotto());
        }

        return new Lottos(lottos);
    }

    public Lotto generateLotto() {
        List<Integer> numbers = generateNumbers(LOTTO_LENGTH);
        return convertNumbersToLotto(numbers);
    }

    private Lotto convertNumbersToLotto(List<Integer> numbers) {
        List<Ball> balls = numbers.stream()
                .map(Ball::new)
                .collect(Collectors.toList());
        return new Lotto(balls);
    }
}
