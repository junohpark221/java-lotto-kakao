package lotto;

import lotto.domain.*;
import lotto.enums.Ranking;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoSystemTest {
    @Test
    void 로또_시스템_생성자_테스트() {
        LottoSystem lottoSystem = new LottoSystem();

        assertThat(lottoSystem).isNotNull();
    }

    @Test
    void 돈_2500원으로_로또_구매_시_2장_구매_여부_테스트() {
        LottoSystem lottoSystem = new LottoSystem();
        Money money = new Money(2500);
        Lottos lottos = lottoSystem.buyLottos(money);

        assertThat(lottos.getSize()).isEqualTo(2);
    }

    @Test
    void 일등_이등_당첨_시_수익률_계산_테스트() {
        LottoSystem lottoSystem = new LottoSystem();
        Money money = new Money(2000);
        lottoSystem.buyLottos(money);

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
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(Arrays.asList(
                        Ball.of(1),
                        Ball.of(2),
                        Ball.of(3),
                        Ball.of(4),
                        Ball.of(5),
                        Ball.of(6)
                )), Ball.of(7));

        lottoSystem.scoreLotto(lotto1, winningLotto);
        lottoSystem.scoreLotto(lotto2, winningLotto);

        Profit profit = lottoSystem.calculateProfit();

        assertThat(profit.toString()).isEqualTo(String.format("%d.%d", (Ranking.FIRST.getReward() + Ranking.SECOND.getReward()) / 2000, 0));
    }
}
