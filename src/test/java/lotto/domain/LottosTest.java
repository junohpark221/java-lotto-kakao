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
                new Ball(1),
                new Ball(2),
                new Ball(3),
                new Ball(4),
                new Ball(5),
                new Ball(6)
        ));
        lotto2 = new Lotto(Arrays.asList(
                new Ball(11),
                new Ball(12),
                new Ball(13),
                new Ball(14),
                new Ball(15),
                new Ball(16)
        ));
        lotto3 = new Lotto(Arrays.asList(
                new Ball(21),
                new Ball(22),
                new Ball(23),
                new Ball(24),
                new Ball(25),
                new Ball(26)
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
