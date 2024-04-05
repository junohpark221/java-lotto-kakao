package lotto.domain;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    void 생성자_테스트 () {
        Lotto lotto = new Lotto(Arrays.asList(
                Ball.of(1),
                Ball.of(2),
                Ball.of(3),
                Ball.of(4),
                Ball.of(5),
                Ball.of(6)
        ));

        assertThat(lotto).isNotNull();
    }

    @Test
    void 로또_중복_숫자_검증_테스트() {
        assertThatThrownBy(() -> {
            new Lotto(Arrays.asList(
                    Ball.of(1),
                    Ball.of(1),
                    Ball.of(1),
                    Ball.of(1),
                    Ball.of(1),
                    Ball.of(1)
            ));
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    void 로또_내_볼_개수_테스트() {
        assertThatThrownBy(() -> {
            new Lotto(Arrays.asList(
                    Ball.of(1),
                    Ball.of(2),
                    Ball.of(3),
                    Ball.of(4),
                    Ball.of(5)
            ));
        }).isInstanceOf(RuntimeException.class);
    }
}
