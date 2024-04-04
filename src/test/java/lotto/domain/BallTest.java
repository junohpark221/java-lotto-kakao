package lotto.domain;

import lotto.domain.Ball;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BallTest {
    @Test
    void 볼_생성자_테스트() {
        Ball ball = new Ball(1);
        assertThat(ball).isNotNull();
        assertThat(ball.getNumber()).isEqualTo(1);
    }

    @Test
    void 볼_번호_범위_테스트() {
        Assertions.assertAll(() -> {
            assertThatThrownBy(() -> {
                new Ball(0);
            }).isInstanceOf(IllegalArgumentException.class);
            Ball ball01 = new Ball(1);
            assertThat(ball01.getNumber()).isEqualTo(1);
            Ball ball45 = new Ball(45);
            assertThat(ball45.getNumber()).isEqualTo(45);
            assertThatThrownBy(() -> {
                new Ball(46);
            }).isInstanceOf(IllegalArgumentException.class);
        });
    }
}
