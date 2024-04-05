package lotto;

import lotto.domain.*;
import lotto.view.LottoView;

import java.util.List;

public class LottoApplication {
    public static void main(String[] args) {
        LottoSystem lottoSystem = new LottoSystem();
        LottoView lottoView = new LottoView();

        Money money = new Money(lottoView.inputMoney());
        long manualCount = lottoView.inputManualCount();
        lottoSystem.checkLottoCount(money, manualCount);

        List<List<Integer>> manualLottoNumbers = lottoView.inputManualLottos(manualCount);
        Lottos userManualLottos = lottoSystem.buyManualLottos(manualLottoNumbers);
        Lottos userAutolottos = lottoSystem.buyAutoLottos();

        lottoView.printLottos(userManualLottos, userAutolottos);

        List<Integer> answerAndBonusNumber = lottoView.inputAnswer();
        WinningLotto winningLotto = lottoSystem.convertToAnswer(answerAndBonusNumber);
        lottoSystem.scoreLottos(userManualLottos, userAutolottos, winningLotto);

        Result result = lottoSystem.getResult();
        lottoView.printResult(result);

        Profit profit = lottoSystem.calculateProfit();
        lottoView.printProfit(profit);
    }
}
