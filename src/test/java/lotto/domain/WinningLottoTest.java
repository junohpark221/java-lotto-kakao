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
                Ball.of(1),
                Ball.of(2),
                Ball.of(3),
                Ball.of(4),
                Ball.of(5),
                Ball.of(6)
        ));
    }

    @Test
    void 정답_생성자_테스트() {
        WinningLotto winningLotto = new WinningLotto(
                lotto,
                Ball.of(7)
        );
        assertThat(winningLotto).isNotNull();
    }

    @Test
    void 정답과_중복된_보너스_볼_테스트() {
        assertThatThrownBy(() -> {
            new WinningLotto(
                    lotto,
                    Ball.of(6)
            );
        }).isInstanceOf(RuntimeException.class);
    }
}
