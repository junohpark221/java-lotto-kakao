package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
    Lotto lotto;
    @BeforeEach
    void setUp() {
        lotto = new Lotto(Arrays.asList(
                new Ball(1),
                new Ball(2),
                new Ball(3),
                new Ball(4),
                new Ball(5),
                new Ball(6)
        ));
    }

    @Test
    void 정답_생성자_테스트() {
        WinningLotto winningLotto = new WinningLotto(
                lotto,
                new Ball(7)
        );
        assertThat(winningLotto).isNotNull();
    }

    @Test
    void 정답과_중복된_보너스_볼_테스트() {
        assertThatThrownBy(() -> {
            new WinningLotto(
                    lotto,
                    new Ball(6)
            );
        }).isInstanceOf(RuntimeException.class);
    }
}
