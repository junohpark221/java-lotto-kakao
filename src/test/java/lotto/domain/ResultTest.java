package lotto.domain;

import lotto.enums.Ranking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto(
                new Lotto(Arrays.asList(
                        Ball.of(1),
                        Ball.of(2),
                        Ball.of(3),
                        Ball.of(4),
                        Ball.of(5),
                        Ball.of(6)
                )), Ball.of(7));
    }

    @Test
    void 낙첨_테스트() {
        Lotto lotto = new Lotto(Arrays.asList(
                Ball.of(1),
                Ball.of(2),
                Ball.of(13),
                Ball.of(14),
                Ball.of(15),
                Ball.of(16)
        ));

        Lottos lottos1 = new Lottos(List.of(lotto));
        Lottos lottos2 = new Lottos(new ArrayList<>());

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);
        assertThat(result.getScore(Ranking.FAIL)).isEqualTo(1);
    }

    @Test
    void 일등_테스트() {
        Lotto lotto = new Lotto(Arrays.asList(
                Ball.of(1),
                Ball.of(2),
                Ball.of(3),
                Ball.of(4),
                Ball.of(5),
                Ball.of(6)
        ));

        Lottos lottos1 = new Lottos(List.of(lotto));
        Lottos lottos2 = new Lottos(new ArrayList<>());

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);
        assertThat(result.getScore(Ranking.FIRST)).isEqualTo(1);
    }

    @Test
    void 이등_테스트() {
        Lotto lotto = new Lotto(Arrays.asList(
                Ball.of(1),
                Ball.of(2),
                Ball.of(3),
                Ball.of(4),
                Ball.of(5),
                Ball.of(7)
        ));

        Lottos lottos1 = new Lottos(List.of(lotto));
        Lottos lottos2 = new Lottos(new ArrayList<>());

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);
        assertThat(result.getScore(Ranking.SECOND)).isEqualTo(1);
    }

    @Test
    void 삼등_테스트() {
        Lotto lotto = new Lotto(Arrays.asList(
                Ball.of(1),
                Ball.of(2),
                Ball.of(3),
                Ball.of(4),
                Ball.of(5),
                Ball.of(16)
        ));

        Lottos lottos1 = new Lottos(List.of(lotto));
        Lottos lottos2 = new Lottos(new ArrayList<>());

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);
        assertThat(result.getScore(Ranking.THIRD)).isEqualTo(1);
    }

    @Test
    void 사등_테스트() {
        Lotto lotto = new Lotto(Arrays.asList(
                Ball.of(1),
                Ball.of(2),
                Ball.of(3),
                Ball.of(4),
                Ball.of(15),
                Ball.of(16)
        ));

        Lottos lottos1 = new Lottos(List.of(lotto));
        Lottos lottos2 = new Lottos(new ArrayList<>());

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);
        assertThat(result.getScore(Ranking.FOURTH)).isEqualTo(1);
    }

    @Test
    void 오등_테스트() {
        Lotto lotto = new Lotto(Arrays.asList(
                Ball.of(1),
                Ball.of(2),
                Ball.of(3),
                Ball.of(14),
                Ball.of(15),
                Ball.of(16)
        ));

        Lottos lottos1 = new Lottos(List.of(lotto));
        Lottos lottos2 = new Lottos(new ArrayList<>());

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);
        assertThat(result.getScore(Ranking.FIFTH)).isEqualTo(1);
    }

    @Test
    void 당첨금_계산_테스트() {
        Lotto lotto1 = new Lotto(Arrays.asList(
                Ball.of(1),
                Ball.of(2),
                Ball.of(3),
                Ball.of(4),
                Ball.of(5),
                Ball.of(6)
        ));
        Lotto lotto2 = new Lotto(Arrays.asList(
                Ball.of(1),
                Ball.of(2),
                Ball.of(3),
                Ball.of(4),
                Ball.of(5),
                Ball.of(7)
        ));

        Lottos lottos1 = new Lottos(List.of(lotto1, lotto2));
        Lottos lottos2 = new Lottos(new ArrayList<>());

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);

        long reward = result.calculateReward();
        assertThat(reward).isEqualTo(Ranking.FIRST.getReward()+Ranking.SECOND.getReward());
    }
}
