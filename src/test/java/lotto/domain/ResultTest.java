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
        List<Integer> lotto = Arrays.asList(1, 2, 13, 14, 15, 16);

        Lottos lottos1 = Lottos.buyManualLottos(List.of(lotto));
        Lottos lottos2 = Lottos.buyManualLottos(new ArrayList<>());

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);
        assertThat(result.getScore(Ranking.FAIL)).isEqualTo(1);
    }

    @Test
    void 일등_테스트() {
        List<Integer> lotto = Arrays.asList(1, 2, 3, 4, 5, 6);

        Lottos lottos1 = Lottos.buyManualLottos(List.of(lotto));
        Lottos lottos2 = Lottos.buyManualLottos(new ArrayList<>());

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);
        assertThat(result.getScore(Ranking.FIRST)).isEqualTo(1);
    }

    @Test
    void 이등_테스트() {
        List<Integer> lotto = Arrays.asList(1, 2, 3, 4, 5, 7);

        Lottos lottos1 = Lottos.buyManualLottos(List.of(lotto));
        Lottos lottos2 = Lottos.buyManualLottos(new ArrayList<>());

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);
        assertThat(result.getScore(Ranking.SECOND)).isEqualTo(1);
    }

    @Test
    void 삼등_테스트() {
        List<Integer> lotto = Arrays.asList(1, 2, 3, 4, 5, 16);

        Lottos lottos1 = Lottos.buyManualLottos(List.of(lotto));
        Lottos lottos2 = Lottos.buyManualLottos(new ArrayList<>());

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);
        assertThat(result.getScore(Ranking.THIRD)).isEqualTo(1);
    }

    @Test
    void 사등_테스트() {
        List<Integer> lotto = Arrays.asList(1, 2, 3, 4, 15, 16);

        Lottos lottos1 = Lottos.buyManualLottos(List.of(lotto));
        Lottos lottos2 = Lottos.buyManualLottos(new ArrayList<>());

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);
        assertThat(result.getScore(Ranking.FOURTH)).isEqualTo(1);
    }

    @Test
    void 오등_테스트() {
        List<Integer> lotto = Arrays.asList(1, 2, 3, 14, 15, 16);

        Lottos lottos1 = Lottos.buyManualLottos(List.of(lotto));
        Lottos lottos2 = Lottos.buyManualLottos(new ArrayList<>());

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);
        assertThat(result.getScore(Ranking.FIFTH)).isEqualTo(1);
    }

    @Test
    void 당첨금_계산_테스트() {
        List<Integer> lotto1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> lotto2 = Arrays.asList(1, 2, 3, 4, 5, 7);

        Lottos lottos1 = Lottos.buyManualLottos(List.of(lotto1, lotto2));
        Lottos lottos2 = Lottos.buyManualLottos(new ArrayList<>());

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);

        long reward = result.calculateReward();
        assertThat(reward).isEqualTo(Ranking.FIRST.getReward()+Ranking.SECOND.getReward());
    }
}
