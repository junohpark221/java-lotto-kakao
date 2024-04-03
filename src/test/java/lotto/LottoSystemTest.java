package lotto;

import lotto.domain.*;
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
    void 돈_2500원으로_로또_구매_테스트() {
        LottoSystem lottoSystem = new LottoSystem();
        Money money = new Money(2500);
        Lottos lottos = lottoSystem.buyLottos(money);

        assertThat(lottos.getSize()).isEqualTo(2);
    }

    @Test
    void 수익률_계산_테스트() {
        LottoSystem lottoSystem = new LottoSystem();
        Money money = new Money(2000);
        lottoSystem.buyLottos(money);

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
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(Arrays.asList(
                        new Ball(1),
                        new Ball(2),
                        new Ball(3),
                        new Ball(4),
                        new Ball(5),
                        new Ball(6)
                )), new Ball(7));

        lottoSystem.scoreLotto(lotto1, winningLotto);
        lottoSystem.scoreLotto(lotto2, winningLotto);

        Profit profit = lottoSystem.calculateProfit();

        assertThat(profit.toString()).isEqualTo(String.format("%d.%d", (2000000000+30000000) / 2000, 0));
    }
}
