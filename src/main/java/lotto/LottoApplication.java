package lotto;

import lotto.view.LottoView;

public class LottoApplication {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoView());
        lottoController.run();
    }
}
