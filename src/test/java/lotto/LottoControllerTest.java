package lotto;

import lotto.domain.*;
import lotto.enums.Ranking;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoControllerTest {
    @Test
    void 돈_2500원으로_로또_구매_시_2장_구매_여부_테스트() {
        Money money = new Money(2500);

        money.checkManualLottoCount(0);
        Lottos lottos = Lottos.buyRandomLottos(money, 0);

        assertThat(lottos.getSize()).isEqualTo(2);
    }

    @Test
    void 수동_로또_생성_테스트() {
        List<List<Integer>> lottoNumbers = new ArrayList<>();
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        lottoNumbers.add(numbers);
        Lottos lottos = Lottos.buyManualLottos(lottoNumbers);

        assertThat(lottos.get(0).contain(Ball.of(1))).isTrue();
        assertThat(lottos.get(0).contain(Ball.of(2))).isTrue();
        assertThat(lottos.get(0).contain(Ball.of(3))).isTrue();
        assertThat(lottos.get(0).contain(Ball.of(4))).isTrue();
        assertThat(lottos.get(0).contain(Ball.of(5))).isTrue();
        assertThat(lottos.get(0).contain(Ball.of(6))).isTrue();
    }

    @Test
    void 일등_이등_당첨_시_수익률_계산_테스트() {
        Money money = new Money(2000);

        money.checkManualLottoCount(0);

        List<Integer> lotto1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> lotto2 = Arrays.asList(1, 2, 3, 4, 5, 7);

        WinningLotto winningLotto = new WinningLotto(
                new Lotto(Arrays.asList(
                        Ball.of(1),
                        Ball.of(2),
                        Ball.of(3),
                        Ball.of(4),
                        Ball.of(5),
                        Ball.of(6)
                )), Ball.of(7));

        Lottos lottos1 = Lottos.buyManualLottos(List.of(lotto1));
        Lottos lottos2 = Lottos.buyManualLottos(List.of(lotto2));

        Result result = Result.scoreLottos(lottos1, lottos2, winningLotto);
        Profit profit = Profit.calculateProfit(result, money);

        assertThat(profit.toString()).isEqualTo(String.format("%d.%d", (Ranking.FIRST.getReward() + Ranking.SECOND.getReward()) / 2000, 0));
    }
}
