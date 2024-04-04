package lotto.generator;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomLottoGeneratorTest {
    @Test
    void 랜덤_숫자_생성_생성자_테스트() {
        RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();
        assertThat(randomLottoGenerator).isNotNull();
    }

    @Test
    void 랜덤_숫자_기반_로또_생성_테스트() {
        RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();
        Lotto lotto = randomLottoGenerator.generateLotto();

        TreeSet<Ball> balls = lotto.getBalls();

        List<Integer> numbers = balls.stream()
                .map(Ball::getNumber)
                .collect(Collectors.toList());

        int uniqueNumbers = new HashSet<>(numbers).size();

        Assertions.assertAll(() -> {
            assertThat(numbers.size()).isEqualTo(6);
            for (int i = 0; i < 6; i++) {
                assertThat(numbers.get(i)).isBetween(1, 45);
            }
            assertThat(uniqueNumbers).isEqualTo(6);
        });
    }
}
