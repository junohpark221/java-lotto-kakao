package lotto;

import lotto.domain.*;
import lotto.view.LottoView;

import java.util.List;

public class LottoApplication {
    public static void main(String[] args) {
        LottoSystem lottoSystem = new LottoSystem();
        LottoView lottoView = new LottoView();

        Money money = new Money(lottoView.inputMoney());
        Lottos lottos = lottoSystem.buyLottos(money);
        lottoView.printLottos(lottos);

        List<Integer> answerAndBonusNumber = lottoView.inputAnswer();
        WinningLotto winningLotto = lottoSystem.convertToAnswer(answerAndBonusNumber);
        lottoSystem.scoreLottos(lottos, winningLotto);

        Result result = lottoSystem.getResult();
        lottoView.printResult(result);

        Profit profit = lottoSystem.calculateProfit();
        lottoView.printProfit(profit);
    }
}
