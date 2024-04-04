package lotto.domain;

import lotto.enums.Ranking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto(
                new Lotto(Arrays.asList(
                        new Ball(1),
                        new Ball(2),
                        new Ball(3),
                        new Ball(4),
                        new Ball(5),
                        new Ball(6)
                )), new Ball(7));
    }

    @Test
    void 결과_객체_생성자_테스트() {
        Result result = new Result();
        assertThat(result).isNotNull();
    }

    @Test
    void 낙첨_테스트() {
        Result result = new Result();
        Lotto lotto = new Lotto(Arrays.asList(
                new Ball(1),
                new Ball(12),
                new Ball(13),
                new Ball(14),
                new Ball(15),
                new Ball(16)
        ));

        result.scoreLotto(lotto, winningLotto);
        assertThat(result.getScore(Ranking.FAIL)).isEqualTo(1);
    }

    @Test
    void 일등_테스트() {
        Result result = new Result();
        Lotto lotto = new Lotto(Arrays.asList(
                new Ball(1),
                new Ball(2),
                new Ball(3),
                new Ball(4),
                new Ball(5),
                new Ball(6)
        ));

        result.scoreLotto(lotto, winningLotto);
        assertThat(result.getScore(Ranking.FIRST)).isEqualTo(1);
    }

    @Test
    void 이등_테스트() {
        Result result = new Result();
        Lotto lotto = new Lotto(Arrays.asList(
                new Ball(1),
                new Ball(2),
                new Ball(3),
                new Ball(4),
                new Ball(5),
                new Ball(7)
        ));

        result.scoreLotto(lotto, winningLotto);
        assertThat(result.getScore(Ranking.SECOND)).isEqualTo(1);
    }

    @Test
    void 삼등_테스트() {
        Result result = new Result();
        Lotto lotto = new Lotto(Arrays.asList(
                new Ball(1),
                new Ball(2),
                new Ball(3),
                new Ball(4),
                new Ball(5),
                new Ball(16)
        ));

        result.scoreLotto(lotto, winningLotto);
        assertThat(result.getScore(Ranking.THIRD)).isEqualTo(1);
    }

    @Test
    void 사등_테스트() {
        Result result = new Result();
        Lotto lotto = new Lotto(Arrays.asList(
                new Ball(1),
                new Ball(2),
                new Ball(3),
                new Ball(4),
                new Ball(15),
                new Ball(16)
        ));

        result.scoreLotto(lotto, winningLotto);
        assertThat(result.getScore(Ranking.FOURTH)).isEqualTo(1);
    }

    @Test
    void 오등_테스트() {
        Result result = new Result();
        Lotto lotto = new Lotto(Arrays.asList(
                new Ball(1),
                new Ball(2),
                new Ball(3),
                new Ball(14),
                new Ball(15),
                new Ball(16)
        ));

        result.scoreLotto(lotto, winningLotto);
        assertThat(result.getScore(Ranking.FIFTH)).isEqualTo(1);
    }

    @Test
    void 당첨금_계산_테스트() {
        Result result = new Result();
        Lotto lotto1 = new Lotto(Arrays.asList(
                new Ball(1),
                new Ball(2),
                new Ball(3),
                new Ball(4),
                new Ball(5),
                new Ball(6)
        ));
        Lotto lotto2 = new Lotto(Arrays.asList(
                new Ball(1),
                new Ball(2),
                new Ball(3),
                new Ball(4),
                new Ball(5),
                new Ball(7)
        ));

        result.scoreLotto(lotto1, winningLotto);
        result.scoreLotto(lotto2, winningLotto);

        long reward = result.calculateReward();
        assertThat(reward).isEqualTo(2000000000+30000000);
    }
}
