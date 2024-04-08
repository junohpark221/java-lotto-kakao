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
        money.checkManualLottoCount(manualCount);

        List<List<Integer>> manualLottoNumbers = lottoView.inputManualLottos(manualCount);
        Lottos userManualLottos = lottoSystem.buyManualLottos(manualLottoNumbers);
        Lottos userAutolottos = lottoSystem.buyAutoLottos(money, manualCount);

        lottoView.printLottos(userManualLottos, userAutolottos);

        List<Integer> answerNumber = lottoView.inputAnswerNumber();
        int bonusNumber = lottoView.inputBonusNumber();
        WinningLotto winningLotto = lottoSystem.convertToAnswer(answerNumber, bonusNumber);
        lottoSystem.scoreLottos(userManualLottos, userAutolottos, winningLotto);

        Result result = lottoSystem.getResult();
        lottoView.printResult(result);

        Profit profit = lottoSystem.calculateProfit(money);
        lottoView.printProfit(profit);
    }
}
