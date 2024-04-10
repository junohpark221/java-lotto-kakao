package lotto.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    @Test
    void 수동_로또_생성_테스트() {
        List<Integer> lotto1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> lotto2 = Arrays.asList(11, 12, 13, 14, 15, 16);
        List<Integer> lotto3 = Arrays.asList(21, 22, 23, 24, 25, 26);

        Lottos lottos = Lottos.buyManualLottos(Arrays.asList(lotto1, lotto2, lotto3));
        assertThat(lottos).isNotNull();
    }

    @Test
    void 자동_로또_생성_테스트() {
        Money money = new Money(2500);
        Lottos lottos = Lottos.buyRandomLottos(money, 0);

        Assertions.assertAll(
                () -> assertThat(lottos).isNotNull(),
                () -> assertThat(lottos.getSize()).isEqualTo(2)
        );
    }
}
