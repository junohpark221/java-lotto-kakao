package lotto.domain;

import lotto.domain.Ball;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BallTest {
    @Test
    void 볼_생성자_테스트() {
        Ball ball = Ball.of(1);
        assertThat(ball).isNotNull();
        assertThat(ball.getNumber()).isEqualTo(1);
    }

    @Test
    void 볼_번호_범위_테스트() {
        Assertions.assertAll(() -> {
            assertThatThrownBy(() -> {
                Ball.of(0);
            }).isInstanceOf(IllegalArgumentException.class);
            Ball ball01 = Ball.of(1);
            assertThat(ball01.getNumber()).isEqualTo(1);
            Ball ball45 = Ball.of(45);
            assertThat(ball45.getNumber()).isEqualTo(45);
            assertThatThrownBy(() -> {
                Ball.of(46);
            }).isInstanceOf(IllegalArgumentException.class);
        });
    }
}
