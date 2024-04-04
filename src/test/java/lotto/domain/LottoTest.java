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
                new Ball(1),
                new Ball(2),
                new Ball(3),
                new Ball(4),
                new Ball(5),
                new Ball(6)
        ));

        assertThat(lotto).isNotNull();
    }

    @Test
    void 로또_중복_숫자_검증_테스트() {
        assertThatThrownBy(() -> {
            new Lotto(Arrays.asList(
                    new Ball(1),
                    new Ball(1),
                    new Ball(1),
                    new Ball(1),
                    new Ball(1),
                    new Ball(1)
            ));
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    void 로또_내_볼_개수_테스트() {
        assertThatThrownBy(() -> {
            new Lotto(Arrays.asList(
                    new Ball(1),
                    new Ball(2),
                    new Ball(3),
                    new Ball(4),
                    new Ball(5)
            ));
        }).isInstanceOf(RuntimeException.class);
    }
}
