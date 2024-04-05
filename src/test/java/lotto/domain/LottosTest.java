package lotto.domain;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    Lotto lotto1, lotto2, lotto3;
    @BeforeEach
    void setUp() {
        lotto1 = new Lotto(Arrays.asList(
                Ball.of(1),
                Ball.of(2),
                Ball.of(3),
                Ball.of(4),
                Ball.of(5),
                Ball.of(6)
        ));
        lotto2 = new Lotto(Arrays.asList(
                Ball.of(11),
                Ball.of(12),
                Ball.of(13),
                Ball.of(14),
                Ball.of(15),
                Ball.of(16)
        ));
        lotto3 = new Lotto(Arrays.asList(
                Ball.of(21),
                Ball.of(22),
                Ball.of(23),
                Ball.of(24),
                Ball.of(25),
                Ball.of(26)
        ));
    }
    @Test
    void 생성자_테스트() {
        Lottos lottos = new Lottos(Arrays.asList(
                lotto1, lotto2, lotto3
        ));
        assertThat(lottos).isNotNull();

    }
}
