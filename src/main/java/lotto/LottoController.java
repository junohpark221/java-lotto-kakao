package lotto;

import lotto.domain.*;
import lotto.view.LottoView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoController {
    private final String MANUAL_LOTTO_KEY = "manualLottos";
    private final String RANDOM_LOTTO_KEY = "randomLottos";
    private LottoView lottoView;

    public LottoController(LottoView lottoView) {
        this.lottoView = lottoView;
    }

    public void run() {
        Money money = new Money(lottoView.inputMoney());
        long manualCount = lottoView.inputManualCount();
        money.checkManualLottoCount(manualCount);

        Map<String, Lottos> lottos = buyLottos(money, manualCount);
        WinningLotto winningLotto = getWinningLotto();

        calculateResult(money, lottos, winningLotto);
    }

    private Map<String, Lottos> buyLottos(Money money, long manualCount) {
        Map<String, Lottos> lottos = new HashMap<>();

        List<List<Integer>> manualLottoNumbers = lottoView.inputManualLottos(manualCount);

        lottos.put(MANUAL_LOTTO_KEY, Lottos.buyManualLottos(manualLottoNumbers));
        lottos.put(RANDOM_LOTTO_KEY, Lottos.buyRandomLottos(money, manualCount));

        lottoView.printLottos(lottos.get(MANUAL_LOTTO_KEY), lottos.get(RANDOM_LOTTO_KEY));

        return lottos;
    }

    private WinningLotto getWinningLotto() {
        List<Integer> answerNumbers = lottoView.inputAnswerNumber();
        int bonusNumber = lottoView.inputBonusNumber();
        return new WinningLotto(answerNumbers, bonusNumber);
    }

    private void calculateResult(Money money, Map<String, Lottos> lottos, WinningLotto winningLotto) {
        Result result = Result.scoreLottos(lottos.get(MANUAL_LOTTO_KEY), lottos.get(RANDOM_LOTTO_KEY), winningLotto);
        lottoView.printResult(result);

        Profit profit = Profit.calculateProfit(result, money);
        lottoView.printProfit(profit);
    }
}
