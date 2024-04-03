package lotto.generator;

import lotto.domain.Answer;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

public interface NumberGenerator {
    Lotto generateLotto();
    Lottos generateLottos(long lottoCount);
}
